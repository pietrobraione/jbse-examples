package defs;

public class Defs {
	//Customize them.
	public static final String sicstusPath       = "/usr/local/bin/";
	public static final String cvc3Path          = "/Users/pietro/cvc3-2.4.1/bin/";
	public static final String z3Path            = "/Users/pietro/bin/";

	//Leave them alone.
	public static final String examplesHome      = "./";
	public static final String jbseHome          = "../jbse/";
	public static final String targetClassPath   = examplesHome + "bin/";
	public static final String jreClassPath      = examplesHome + "data/jre/rt.jar";
	public static final String jbseClassPath     = jbseHome + "bin/";
	public static final String targetSourcePath  = examplesHome + "src/";
	public static final String jreSourcePath     = examplesHome + "data/jre/src.zip";
	public static final String jbseSourcePath    = jbseHome + "src/";

	//Leave them alone, or add more stuff.
	public static final String[] classPath        = { jbseClassPath, jreClassPath, targetClassPath };
	public static final String[] sourcePath       = { jbseSourcePath, jreSourcePath, targetSourcePath };
}
