package smalldemos.invokedynamic_1;

import static org.objectweb.asm.Opcodes.*;

import java.lang.invoke.CallSite;
import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;
import java.lang.invoke.MutableCallSite;
import java.lang.reflect.Method;

import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.Handle;
import org.objectweb.asm.MethodVisitor;

public class InvokeDynamicDemo {
    @SuppressWarnings("unused")
    private static Object add(Integer a, Integer b) {
        return Integer.valueOf(a.intValue() + b.intValue());
    }
    
    @SuppressWarnings("unused")
    private static Object add(Integer a, Double b) {
        return Double.valueOf(a.doubleValue() + b.doubleValue());
    }
    
    @SuppressWarnings("unused")
    private static Object add(Double a, Integer b) {
        return Double.valueOf(a.doubleValue() + b.doubleValue());
    }
    
    @SuppressWarnings("unused")
    private static Object add(Double a, Double b) {
        return Double.valueOf(a.doubleValue() + b.doubleValue());
    }
    
    private static Object raise(Object a, Object b) throws Exception {
        throw new Exception("Cannot add these values!");
    }

    @SuppressWarnings("unused")
    private static boolean guard(Class<?> ca, Class<?> cb, Object a, Object b) {
        return (a.getClass() == ca) && (b.getClass() == cb);
    }

    @SuppressWarnings("unused")
    private static Object fallback(MutableCallSite monomorphicInlineCache, Object a, Object b) throws Throwable {
        final Class<?> ca = a.getClass();
        final Class<?> cb = b.getClass();
       
        final MethodHandle target;
        try {
            final MethodHandle newAdd = LOOKUP.findStatic(InvokeDynamicDemo.class, "add", MethodType.methodType(Object.class, ca, cb));
            target = MethodHandles.guardWithTest(GUARD.bindTo(ca).bindTo(cb), newAdd.asType(MethodType.methodType(Object.class, Object.class, Object.class)), FALLBACK.bindTo(monomorphicInlineCache));
        } catch (IllegalAccessException | NoSuchMethodException e) {
            return raise(a, b);
        }
        monomorphicInlineCache.setTarget(target);
        return target.invoke(a, b);
    }
    
    private static final MethodHandles.Lookup LOOKUP = MethodHandles.lookup();
    private static final MethodHandle GUARD;
    private static final MethodHandle FALLBACK;

    static {
        try {
            GUARD    = LOOKUP.findStatic(InvokeDynamicDemo.class, "guard", MethodType.methodType(boolean.class, Class.class, Class.class, Object.class, Object.class));
            FALLBACK = LOOKUP.findStatic(InvokeDynamicDemo.class, "fallback", MethodType.methodType(Object.class, MutableCallSite.class, Object.class, Object.class));
        } catch (IllegalAccessException | NoSuchMethodException e) {
            throw new Error(e);
        }
    }
    
    public static CallSite bootstrapDynamic(MethodHandles.Lookup caller, 
                                            String name, 
                                            MethodType type)
                                            throws IllegalAccessException, NoSuchMethodException {
        final MutableCallSite retVal = new MutableCallSite(type);
        final MethodHandle behavior = FALLBACK.bindTo(retVal).asType(type);
        retVal.setTarget(behavior);
        return retVal;
    }

    public static byte[] dump() throws Exception {

        ClassWriter cw = new ClassWriter(ClassWriter.COMPUTE_FRAMES);
        MethodVisitor mv;

        cw.visit(V1_8,
                ACC_PUBLIC + ACC_SUPER,
                "ID",
                null,
                "java/lang/Object",
                null);

        cw.visitSource("ID.java", null);

        {
            mv = cw.visitMethod(ACC_PUBLIC, "<init>", "()V", null, null);
            mv.visitVarInsn(ALOAD, 0);
            mv.visitMethodInsn(INVOKESPECIAL,
                    "java/lang/Object",
                    "<init>",
                    "()V", 
                    false);
            mv.visitInsn(RETURN);
            mv.visitMaxs(1, 1);
            mv.visitEnd();
        }
        {
            mv = cw.visitMethod(ACC_PUBLIC + ACC_STATIC,
                    "doDynamicAdd",
                    "()Ljava/lang/Object;",
                    null,
                    null);
//            mv.visitInsn(DCONST_1);
//            mv.visitMethodInsn(INVOKESTATIC, "java/lang/Double", "valueOf", "(D)Ljava/lang/Double;");
//            mv.visitInsn(DCONST_1);
//            mv.visitMethodInsn(INVOKESTATIC, "java/lang/Double", "valueOf", "(D)Ljava/lang/Double;");
            mv.visitInsn(ICONST_1);
            mv.visitMethodInsn(INVOKESTATIC, "java/lang/Integer", "valueOf", "(I)Ljava/lang/Integer;");
            mv.visitInsn(ICONST_1);
            mv.visitMethodInsn(INVOKESTATIC, "java/lang/Integer", "valueOf", "(I)Ljava/lang/Integer;");
            mv.visitInvokeDynamicInsn(
                            "+", 
                            "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;", 
                            new Handle(H_INVOKESTATIC, 
                                            "smalldemos/invokedynamic_1/InvokeDynamicDemo", 
                                            "bootstrapDynamic", 
                                            "(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/String;Ljava/lang/invoke/MethodType;)Ljava/lang/invoke/CallSite;", 
                                            false));
            mv.visitInsn(ARETURN);
            mv.visitMaxs(2, 2);
            mv.visitEnd();
        }
        cw.visitEnd();

        return cw.toByteArray();
     }
    
    private static class DynClassLoader extends ClassLoader {
        private final byte[] extraClass;
        public DynClassLoader(byte[] extraClass) {
            super();
            this.extraClass = extraClass.clone();
        }
        
        @Override
        protected Class<?> findClass(final String name) throws ClassNotFoundException {
            if ("ID".equals(name)) {
                return defineClass(name, this.extraClass, 0, this.extraClass.length); 
            }
            return super.findClass(name);
        }
    }
    
    public static void main(String[] args) throws Exception {
        final Class<?> c = new DynClassLoader(dump()).loadClass("ID");
        final Method m = c.getDeclaredMethod("doDynamicAdd");
        System.out.println(m.invoke(null));
    }
}
