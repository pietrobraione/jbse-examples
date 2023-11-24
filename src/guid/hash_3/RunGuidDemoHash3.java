package guid.hash_3;

import static defs.Defs.*;

import java.nio.file.Path;

import jbse.apps.run.RunParameters;
import jbse.apps.run.Run;
import jbse.apps.run.RunParameters.DecisionProcedureType;
import jbse.apps.run.RunParameters.GuidanceType;
import jbse.apps.run.RunParameters.StateFormatMode;
import jbse.apps.run.RunParameters.StepShowMode;

public class RunGuidDemoHash3 {
    public static void main(String[] args)	{
        final RunParameters p = new RunParameters();
        set(p);
        final Run r = new Run(p);
        r.run();
    }

    private static final String METHOD_CLASS      = "guid/hash_3/GuidDemoHash3"; 
    private static final String METHOD_DESCRIPTOR = "(Lguid/hash_3/Foo;Ljava/util/HashMap;)I"; 
    private static final String METHOD_NAME       = "entryPoint"; 
    private static final Path OUT_FILE            = EXAMPLES_HOME.resolve("out/runGuidDemoHash3.txt");

    private static void set(RunParameters p) {
        p.setJBSELibPath(JBSE_CLASSPATH);
        p.addUserClasspath(CLASSPATH);
        p.addSourcePath(SOURCEPATH);
        p.setMethodSignature(METHOD_CLASS, METHOD_DESCRIPTOR, METHOD_NAME);
        p.setOutputFilePath(OUT_FILE);
        p.setDecisionProcedureType(DecisionProcedureType.Z3);
        p.setExternalDecisionProcedurePath(Z3_PATH);
        p.setStateFormatMode(StateFormatMode.TEXT);
        p.setStepShowMode(StepShowMode.ALL);
        p.setUseHashMapModels(true);
        p.addUninterpreted("guid/hash_3/Foo", "()Ljava/lang/String;", "toString");
        p.setGuided(METHOD_CLASS, "guidanceStart");
        p.setGuidanceType(GuidanceType.JBSE);
    }
}
