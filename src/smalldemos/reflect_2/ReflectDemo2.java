package smalldemos.reflect_2;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class ReflectDemo2 {
    public static class Foo { 
        public Foo(long l, Object f) { }
    }
    
    public static class Baz { }
    
    @SuppressWarnings("unused")
    private static Object o;

    private static void constructor() throws NoSuchMethodException, SecurityException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        @SuppressWarnings("rawtypes")
        final Constructor fooConstr = Foo.class.getConstructor(long.class, Object.class);
        o = fooConstr.newInstance(2, new Baz());
    }

    public static void entryPoint() throws NoSuchMethodException, SecurityException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        constructor();
    }
}
