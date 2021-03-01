package smalldemos.hash_2;

import static defs.Defs.*;

import java.nio.file.Path;

import jbse.apps.run.RunParameters;
import jbse.apps.run.Run;
import jbse.apps.run.RunParameters.DecisionProcedureType;
import jbse.apps.run.RunParameters.GuidanceType;
import jbse.apps.run.RunParameters.StateFormatMode;
import jbse.apps.run.RunParameters.StepShowMode;


public class RunHashDemo2 {
    public static void main(String[] args)	{
        final RunParameters p = new RunParameters();
        setData(p);
        final Run r = new Run(p);
        r.run();
    }

    private static final String METHOD_CLASS         = "smalldemos/hash_2/HashDemo2"; 
    private static final String METHOD_DESCRIPTOR    = "(Ljava/util/HashMap;)V"; 
    private static final String METHOD_NAME          = "entryPoint"; 
    private static final String GUIDANCE_METHOD_NAME = "test"; 
    private static final Path   OUT_FILE             = EXAMPLES_HOME.resolve("out/runHashDemo2.txt");

    private static void setData(RunParameters p) {
        p.setJBSELibPath(JBSE_CLASSPATH);
        p.addUserClasspath(CLASSPATH);
        p.addSourcePath(SOURCEPATH);
        p.setMethodSignature(METHOD_CLASS, METHOD_DESCRIPTOR, METHOD_NAME);
        p.setOutputFilePath(OUT_FILE);
        p.setDecisionProcedureType(DecisionProcedureType.Z3);
        p.setExternalDecisionProcedurePath(Z3_PATH);
        p.setStepShowMode(StepShowMode.LEAVES);
        p.setStateFormatMode(StateFormatMode.TEXT);
        p.setShowContradictory(false);
        p.setUseHashMapModels(true);
        p.setGuidanceType(GuidanceType.JDI);
        p.setGuided(METHOD_CLASS, GUIDANCE_METHOD_NAME);
    }
}
