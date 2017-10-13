package smalldemos.reflect;

import static jbse.meta.Analysis.ass3rt;

import java.util.LinkedList;

public class ReflectDemo {
    private static final String foo = "foo";
    public static boolean equal;
    public static boolean sameIntern;
    public static boolean sameClass;
    public static boolean nullIsInstance;
    public static boolean isInstance;
    public static boolean isInstance2;

    private static void strings() {
        final String foo2 = new String(foo);
        equal = (foo == foo2);
        ass3rt(!equal);
        sameIntern = (foo.intern() == foo2.intern());
        ass3rt(sameIntern);
    }

    private static void classes() {
        final LinkedList<?> l = new LinkedList<>();
        sameClass = (l.getClass() == LinkedList.class);
        ass3rt(sameClass);
        nullIsInstance = LinkedList.class.isInstance(null);
        ass3rt(!nullIsInstance);
        isInstance = LinkedList.class.isInstance(l);
        ass3rt(isInstance);
        isInstance2 = Number.class.isInstance(Integer.valueOf(1));
        ass3rt(isInstance2);

    }

    public static void entryPoint() {
        strings();
        classes();
    }
}
