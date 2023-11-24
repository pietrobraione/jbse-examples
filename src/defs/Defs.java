package defs;

import java.nio.file.Path;
import java.nio.file.Paths;

public class Defs {
    //Customize them
    public static final Path CVC4_PATH     = Paths.get("/opt", "local", "bin", "cvc4");
    public static final Path Z3_PATH       = Paths.get("/usr", "local", "bin", "z3");
    public static final Path JBSE_HOME     = Paths.get("/Users", "pietro", "git", "jbse");
    public static final Path EXAMPLES_HOME = Paths.get("/Users", "pietro", "git", "jbse-examples");

    //Leave them alone
    public static final Path JBSE_CLASSPATH        = JBSE_HOME.resolve("build/classes/java/main");
    public static final Path JBSE_SOURCEPATH       = JBSE_HOME.resolve("src/main/java");
    public static final Path TARGET_CLASSPATH      = EXAMPLES_HOME.resolve("bin");
    public static final Path TARGET_GOLO_CLASSPATH = EXAMPLES_HOME.resolve("golo-bin");
    public static final Path JAR_ASM_CLASSPATH     = EXAMPLES_HOME.resolve("lib/asm-5.1.jar");
    public static final Path JAR_GOLO_CLASSPATH    = EXAMPLES_HOME.resolve("lib/golo-3.3.0.jar");
    public static final Path TARGET_SOURCEPATH     = EXAMPLES_HOME.resolve("src");
    public static final Path JRE_SOURCEPATH        = Paths.get(System.getProperty("java.home", "") + "src.zip");

    //Leave them alone, or add more stuff
    public static final Path[] CLASSPATH  = { TARGET_CLASSPATH, TARGET_GOLO_CLASSPATH, JAR_ASM_CLASSPATH, JAR_GOLO_CLASSPATH };
    public static final Path[] SOURCEPATH = { JBSE_SOURCEPATH, TARGET_SOURCEPATH, JRE_SOURCEPATH };
}
