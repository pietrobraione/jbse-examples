package defs;

public class Defs {
	//Customize them.
	public static final String cvc4Path          = "/opt/local/bin/cvc4";
	public static final String z3Path            = "/opt/local/bin/z3";

	//Leave them alone.
	public static final String jbseHome          = "/Users/pietro/git/jbse/";
	public static final String examplesHome      = "./";
	public static final String jbseClassPath     = jbseHome + "target/classes/";
	public static final String jbseSourcePath    = jbseHome + "src/";
	public static final String jreClassPath      = jbseHome + "data/jre1.8.0/rt.jar";
	public static final String jreSourcePath     = jbseHome + "data/jre1.8.0/src.zip";
	public static final String targetClassPath   = examplesHome + "bin/";
	public static final String targetSourcePath  = examplesHome + "src/";

	//Leave them alone, or add more stuff.
	public static final String[] classPath        = { jbseClassPath, jreClassPath, targetClassPath };
	public static final String[] sourcePath       = { jbseSourcePath, jreSourcePath, targetSourcePath };
}
