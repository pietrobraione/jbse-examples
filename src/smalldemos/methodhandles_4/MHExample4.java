package smalldemos.methodhandles_4;
import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;

class C {
    int x;
    int y;
}

public class MHExample4 {
    int value;
    
    public void entryPoint() throws Throwable {
        C c = new C();
        MethodHandle mh = MethodHandles.lookup().findSetter(C.class, "x", int.class);
        mh.invokeExact(c, 10);
        mh = MethodHandles.lookup().findGetter(C.class, "x", int.class);
        int x = (int) mh.invokeExact(c);
        System.out.println(x);
    }
    
    public static void main(String[] s) throws Throwable {
        final MHExample4 e = new MHExample4();
        e.entryPoint();
    }
}
