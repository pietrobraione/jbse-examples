package guid.hash_2;

import static defs.Defs.*;

import java.nio.file.Path;

import jbse.apps.run.RunParameters;
import jbse.apps.run.Run;
import jbse.apps.run.RunParameters.DecisionProcedureType;
import jbse.apps.run.RunParameters.GuidanceType;
import jbse.apps.run.RunParameters.StateFormatMode;
import jbse.apps.run.RunParameters.StepShowMode;

public class RunGuidDemoHash2 {
    public static void main(String[] args)	{
        final RunParameters p = new RunParameters();
        set(p);
        final Run r = new Run(p);
        r.run();
    }

    private static final String METHOD_CLASS      = "guid/hash_2/GuidDemoHash2"; 
    private static final String METHOD_DESCRIPTOR = "(ZLjava/util/HashMap;)I"; 
    private static final String METHOD_NAME       = "entryPoint"; 
    private static final Path OUT_FILE            = EXAMPLES_HOME.resolve("out/runGuidDemoHash2.txt");

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
        //p.addUninterpreted("java/lang/String", "(Ljava/lang/Object;)Z", "equals"); //necessary to avoid misalignment with JBSE guidance, not necessary with JDI guidance
        p.setGuided(METHOD_CLASS, "guidanceStart");
        p.setGuidanceType(GuidanceType.JBSE);
    }
}
