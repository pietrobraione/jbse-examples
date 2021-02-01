package smalldemos.invokedynamic_3;

import static org.objectweb.asm.Opcodes.*;

import java.lang.invoke.CallSite;
import java.lang.invoke.ConstantCallSite;
import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;
import java.lang.reflect.Method;

import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.Handle;
import org.objectweb.asm.MethodVisitor;

public class InvokeDynamicDemo {
    public static int add(int a, int b) {
        return a + b;
    }

    public static CallSite bootstrapDynamic(MethodHandles.Lookup caller, 
                                            String name, 
                                            MethodType type)
                                            throws IllegalAccessException, NoSuchMethodException {
        MethodHandles.Lookup lookup = MethodHandles.lookup();
        Class<?> thisClass = lookup.lookupClass();
        MethodHandle mh = lookup.findStatic(thisClass, "add", 
                                            MethodType.methodType(int.class, int.class, int.class));
        if (!type.equals(mh.type())) {
            mh = mh.asType(type);
        }

        final ConstantCallSite cs = new ConstantCallSite(mh);
        return cs;
    }

    public static byte[] dump() throws Exception {

        ClassWriter cw = new ClassWriter(ClassWriter.COMPUTE_FRAMES);
        MethodVisitor mv;

        cw.visit(V1_7,
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
                    "()I",
                    null,
                    null);
            mv.visitInsn(ICONST_1);
            mv.visitInsn(ICONST_2);
            mv.visitInvokeDynamicInsn(
                            "foo", 
                            "(II)I", 
                            new Handle(H_INVOKESTATIC, 
                                            "smalldemos/invokedynamic_3/InvokeDynamicDemo", 
                                            "bootstrapDynamic", 
                                            "(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/String;Ljava/lang/invoke/MethodType;)Ljava/lang/invoke/CallSite;", 
                                            false));
            mv.visitInsn(IRETURN);
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
