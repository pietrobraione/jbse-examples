package smalldemos.invokedynamic_2;

import static org.objectweb.asm.Opcodes.*;

import java.lang.invoke.CallSite;
import java.lang.invoke.ConstantCallSite;
import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;
import java.lang.invoke.MutableCallSite;
import java.lang.reflect.Method;

import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.Handle;
import org.objectweb.asm.Label;
import org.objectweb.asm.MethodVisitor;

public class InvokeDynamicDemo {
    private static MutableCallSite cs;
    private static MethodHandle hw, bw;

    @SuppressWarnings("unused")
    private static void hw() {
            System.out.println("Hello, World!");
            cs.setTarget(bw);
    }

    @SuppressWarnings("unused")
    private static void bw() {
            System.out.println("Bye, World...");
            cs.setTarget(hw);
    }

    public static CallSite bootstrapDynamic(MethodHandles.Lookup caller, 
                                            String name, 
                                            MethodType type)
                                            throws IllegalAccessException, NoSuchMethodException {
        MethodHandles.Lookup lookup = MethodHandles.lookup();
        Class<?> thisClass = lookup.lookupClass();
        hw = lookup.findStatic(thisClass, "hw", 
                               MethodType.methodType(void.class));
        if (!type.equals(hw.type())) {
            hw = hw.asType(type);
        }
        bw = lookup.findStatic(thisClass, "bw", 
                               MethodType.methodType(void.class));
        if (!type.equals(bw.type())) {
            bw = bw.asType(type);
        }

        cs = new MutableCallSite(hw);
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
            Label loopEntry = new Label(); 
            Label loopRepeat = new Label();
            mv = cw.visitMethod(ACC_PUBLIC + ACC_STATIC,
                    "doDynamicPrint",
                    "()V",
                    null,
                    null);
            mv.visitInsn(ICONST_0);
            mv.visitVarInsn(ISTORE, 1);
            mv.visitJumpInsn(GOTO, loopEntry);
            mv.visitLabel(loopRepeat);
            mv.visitIincInsn(1, 1);
            mv.visitLabel(loopEntry);
            mv.visitInvokeDynamicInsn(
                            "foo", 
                            "()V", 
                            new Handle(H_INVOKESTATIC, 
                                            "smalldemos/invokedynamic_2/InvokeDynamicDemo", 
                                            "bootstrapDynamic", 
                                            "(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/String;Ljava/lang/invoke/MethodType;)Ljava/lang/invoke/CallSite;", 
                                            false));
            mv.visitVarInsn(ILOAD, 1);
            mv.visitIntInsn(BIPUSH, 10);
            mv.visitJumpInsn(IF_ICMPLT, loopRepeat);
            mv.visitInsn(RETURN);
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
        final Method m = c.getDeclaredMethod("doDynamicPrint");
        System.out.println(m.invoke(null)); 
    }
}
