package smalldemos.reflect;

import java.util.LinkedList;

public class ReflectDemo {
	private static final String foo = "foo";
	public static boolean equal;
	public static boolean sameIntern;
	public static boolean sameClass;
	
	private static void strings() {
    	String foo2 = new String(foo);
    	equal = (foo == foo2); //should be 0
    	sameIntern = (foo.intern() == foo2.intern()); //should be 1
	}
	
    private static void classes() {
        LinkedList<?> l = new LinkedList<>();
        sameClass = (l.getClass() == LinkedList.class); //should be 1
    }
    
    public static void entryPoint() {
    	strings();
    	classes();
    }
}
