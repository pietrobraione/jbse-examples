package smalldemos.methodhandles_1;
import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;

//run with vm arg -Djava.lang.invoke.MethodHandle.DUMP_CLASS_FILES=true to dump classfiles of lambda forms

public class MHExample1 {
    int value;
    
    @SuppressWarnings("unused")
    private static int add(int a, int b) {
        return a + b;
    }

    public void entryPoint(int a, int b) throws Throwable {
        final MethodHandle mh = MethodHandles.lookup().findStatic(MHExample1.class, "add", MethodType.methodType(int.class, int.class, int.class));
        int res = (int) mh.invokeExact(a, b);
        this.value = res;
    }
    
    public static void main(String[] s) throws Throwable {
        final MHExample1 e = new MHExample1();
        e.entryPoint(2, 3);
        System.out.println(e.value);
    }
}
