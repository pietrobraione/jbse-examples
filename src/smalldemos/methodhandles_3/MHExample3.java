package smalldemos.methodhandles_3;
import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;

class C {
    public void m1() {
        System.out.println("Inside m1");
    }
    
    @SuppressWarnings("unused")
    private void m2() {
        System.out.println("Inside m2");
    }
}

public class MHExample3 {
    int value;
    
    public void entryPoint() throws Throwable {
        MethodHandle mh = MethodHandles.lookup().findVirtual(C.class, "m1", MethodType.methodType(void.class));
        mh.invokeExact(new C());
        mh = MethodHandles.lookup().findVirtual(C.class, "m2", MethodType.methodType(void.class)); //error!
    }
    
    public static void main(String[] s) throws Throwable {
        final MHExample3 e = new MHExample3();
        e.entryPoint();
    }
}
