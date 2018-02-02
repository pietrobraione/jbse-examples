package smalldemos.methodhandles;
import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;

public class MHExample {
    int value;
    
    @SuppressWarnings("unused")
    private static int add(int a, int b) {
        return a + b;
    }

    public void entryPoint(int a, int b) throws Throwable {
        final MethodHandle mh = MethodHandles.lookup().findStatic(MHExample.class, "add", MethodType.methodType(int.class, int.class, int.class));
        int res = (int) mh.invokeExact(2, 3);
        this.value = res;
    }
}
