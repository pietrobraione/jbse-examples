package smalldemos.methodhandles_2;
import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;

class MHExampleSuper {
    public int add(int a, int b) {
        return a + b;
    }
}

public class MHExample2 extends MHExampleSuper {
    int value;
    
    public void entryPoint(int a, int b) throws Throwable {
        final MethodHandle mh = MethodHandles.lookup().findVirtual(MHExample2.class, "add", MethodType.methodType(int.class, int.class, int.class));
        int res = (int) mh.invokeExact(new MHExample2(), a, b);
        this.value = res;
    }
    
    public static void main(String[] s) throws Throwable {
        final MHExample2 e = new MHExample2();
        e.entryPoint(2, 3);
        System.out.println(e.value);
    }
}
