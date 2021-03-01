package smalldemos.hash_5;

import static defs.Defs.*;

import java.nio.file.Path;

import jbse.apps.run.RunParameters;
import jbse.apps.run.Run;
import jbse.apps.run.RunParameters.DecisionProcedureType;
import jbse.apps.run.RunParameters.StateFormatMode;
import jbse.apps.run.RunParameters.StepShowMode;

public class RunHashDemo5 {
    public static void main(String[] args)	{
        final RunParameters p = new RunParameters();
        setData(p);
        final Run r = new Run(p);
        r.run();
    }

    private static final String METHOD_CLASS      = "smalldemos/hash_5/HashDemo5"; 
    private static final String METHOD_DESCRIPTOR = "()V"; 
    private static final String METHOD_NAME       = "entryPoint"; 
    private static final Path   OUT_FILE          = EXAMPLES_HOME.resolve("out/runHashDemo5.txt");

    private static void setData(RunParameters p) {
        p.setJBSELibPath(JBSE_CLASSPATH);
        p.addUserClasspath(CLASSPATH);
        p.addSourcePath(SOURCEPATH);
        p.setMethodSignature(METHOD_CLASS, METHOD_DESCRIPTOR, METHOD_NAME);
        p.setOutputFilePath(OUT_FILE);
        p.setDecisionProcedureType(DecisionProcedureType.Z3);
        p.setExternalDecisionProcedurePath(Z3_PATH);
        p.setStepShowMode(StepShowMode.NONE);
        p.setStateFormatMode(StateFormatMode.PATH);
        p.setShowWarnings(false);
        p.setUseHashMapModels(true);
    }
}
