package golo.fibonacci;

import static defs.Defs.*;
import jbse.apps.run.RunParameters;
import jbse.apps.run.Run;
import static jbse.apps.run.RunParameters.DecisionProcedureType;

import java.nio.file.Path;

import jbse.apps.run.RunParameters.StateFormatMode;
import jbse.apps.run.RunParameters.StepShowMode;

public class RunGoloFibonacci {
    public static void main(String[] args)	{
        final RunParameters p = new RunParameters();
        set(p);
        final Run r = new Run(p);
        r.run();
    }

    private static final String METHOD_CLASS      = "samples/Fibonacci"; 
    private static final String METHOD_DESCRIPTOR = "([Ljava/lang/String;)V"; 
    private static final String METHOD_NAME       = "main"; 
    private static final Path   OUT_FILE          = EXAMPLES_HOME.resolve("out/runGoloFibonacci.txt");

    private static void set(RunParameters p) {
        p.setJBSELibPath(JBSE_CLASSPATH);
        p.addUserClasspath(CLASSPATH);
        p.addSourcePath(SOURCEPATH);
        p.setMethodSignature(METHOD_CLASS, METHOD_DESCRIPTOR, METHOD_NAME);
        p.setOutputFilePath(OUT_FILE);
        p.setDecisionProcedureType(DecisionProcedureType.Z3);
        p.setExternalDecisionProcedurePath(Z3_PATH);
        p.setStateFormatMode(StateFormatMode.TEXT);
        p.setStepShowMode(StepShowMode.LEAVES);
        p.addClassInvariantAfterInitializationPattern("org/eclipse/golo/.*");
        p.addClassInvariantAfterInitializationPattern("gololang/.*");
        //p.setShowSystemClassesInitialization(true);
    }
}
