package guid.uif_1;

import static defs.Defs.*;

import java.nio.file.Path;

import jbse.apps.run.RunParameters;
import jbse.apps.run.Run;
import jbse.apps.run.RunParameters.DecisionProcedureType;
import jbse.apps.run.RunParameters.GuidanceType;
import jbse.apps.run.RunParameters.StateFormatMode;
import jbse.apps.run.RunParameters.StepShowMode;

public class RunGuidDemoUIF1 {
    public static void main(String[] args)	{
        final RunParameters p = new RunParameters();
        set(p);
        final Run r = new Run(p);
        r.run();
    }

    private static final String METHOD_CLASS      = "guid/uif_1/GuidDemoUIF1"; 
    private static final String METHOD_DESCRIPTOR = "(D)I"; 
    private static final String METHOD_NAME       = "entryPoint"; 
    private static final Path OUT_FILE            = EXAMPLES_HOME.resolve("out/runGuidDemoUIF1.txt");

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
        p.addUninterpreted("guid/uif_1/GuidDemoUIF1", "(D)D", "foo");
        p.addUninterpreted("java/lang/Math", "(D)D", "sin");
        p.setGuided(METHOD_CLASS, "guidanceStart");
        p.setGuidanceType(GuidanceType.JBSE);
    }
}
