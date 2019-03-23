package defs;

public class Defs {
    //Customize them
    public static final String CVC4_PATH         = "/opt/local/bin/cvc4";
    public static final String Z3_PATH           = "/opt/local/bin/z3";
    public static final String JBSE_HOME         = "/Users/pietro/git/tardis/jbse/";
    public static final String EXAMPLES_HOME     = "/Users/pietro/git/jbse-examples/";

    //Leave them alone
    public static final String JBSE_CLASSPATH    = JBSE_HOME + "build/classes/java/main";
    public static final String JBSE_SOURCEPATH   = JBSE_HOME + "src/main/java/";
    public static final String TARGET_CLASSPATH  = EXAMPLES_HOME + "bin/";
    public static final String TARGET_SOURCEPATH = EXAMPLES_HOME + "src/";

    //Leave them alone, or add more stuff
    public static final String[] CLASSPATH       = { JBSE_CLASSPATH, TARGET_CLASSPATH };
    public static final String[] SOURCEPATH      = { JBSE_SOURCEPATH, TARGET_SOURCEPATH };
}
