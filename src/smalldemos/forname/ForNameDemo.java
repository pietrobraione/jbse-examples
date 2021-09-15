package smalldemos.forname;

public class ForNameDemo {
	@SuppressWarnings("unused")
	private Class<?> c;
	
    @SuppressWarnings("unused")
	private void m() throws ClassNotFoundException {
    	this.c = Class.forName("java.util.HashMap");
    }

}
