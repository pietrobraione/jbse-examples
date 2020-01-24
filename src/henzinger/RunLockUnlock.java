package henzinger;

import static defs.Defs.*;
import jbse.apps.run.RunParameters;
import jbse.apps.run.Run;
import jbse.apps.run.RunParameters.DecisionProcedureType;
import jbse.apps.run.RunParameters.StepShowMode;
import jbse.jvm.Engine;
import jbse.jvm.ExecutionObserver;

public class RunLockUnlock {
    private static Run r;

    public static void main(String[] args)	{
        final RunParameters p = new RunParameters();
        set(p);
        r = new Run(p);
        r.run();
    }

    private static final String METHOD_CLASS      = "henzinger/LockUnlock"; 
    private static final String METHOD_DESCRIPTOR = "(I)V"; 
    private static final String METHOD_NAME       = "example"; 
    private static final String OUT_FILE          = EXAMPLES_HOME + "out/runLockUnlock.txt";
    private static final String FLAG_ERROR_LOCK   = "_ERROR_LOCK";
    private static final String FLAG_ERROR_UNLOCK = "_ERROR_UNLOCK";
    private static final ExecutionObserver OBSERVER_LOCK = (Engine engine) -> {
        r.out("############# ERROR_LOCK AT TRACE " + engine.getCurrentState().getBranchIdentifier());
        engine.stopCurrentTrace();
    };
    private static final ExecutionObserver OBSERVER_UNLOCK = (Engine engine) -> { 
        r.out("############# ERROR_UNLOCK AT TRACE " + engine.getCurrentState().getBranchIdentifier()); 
        engine.stopCurrentTrace(); 
    }; 

    private static void set(RunParameters p) {
        p.addUserClasspath(CLASSPATH);
        p.addSourcePath(SOURCEPATH);
        p.setMethodSignature(METHOD_CLASS, METHOD_DESCRIPTOR, METHOD_NAME);
        p.setOutputFileName(OUT_FILE);
        p.addExecutionObserver(METHOD_CLASS, "Z", FLAG_ERROR_LOCK, OBSERVER_LOCK);
        p.addExecutionObserver(METHOD_CLASS, "Z", FLAG_ERROR_UNLOCK, OBSERVER_UNLOCK);
        p.setDecisionProcedureType(DecisionProcedureType.Z3);
        p.setExternalDecisionProcedurePath(Z3_PATH);
        p.setStepShowMode(StepShowMode.NONE);
        p.setDepthScope(20);
    }
}
