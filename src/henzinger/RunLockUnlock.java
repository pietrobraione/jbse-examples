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

	private static final String methodClass      = "henzinger/LockUnlock"; 
	private static final String methodParamsSig  = "(I)V"; 
	private static final String methodName       = "example"; 
	private static final String outFile          = examplesHome + "out/runLockUnlock.txt";
	private static final String flagLock         = "_ERROR_LOCK";
	private static final String flagUnlock       = "_ERROR_UNLOCK";
	private static final ExecutionObserver observerLock = (Engine e) -> {
		r.out("############# ERROR_LOCK AT TRACE " + e.getCurrentState().getIdentifier());
		e.stopCurrentTrace();
	};
	private static final ExecutionObserver observerUnlock = (Engine e) -> { 
		r.out("############# ERROR_UNLOCK AT TRACE " + e.getCurrentState().getIdentifier()); 
		e.stopCurrentTrace(); 
	}; 
	
	private static void set(RunParameters p) {
		p.addClasspath(classPath);
		p.addSourcePath(sourcePath);
		p.setMethodSignature(methodClass, methodParamsSig, methodName);
		p.setOutputFileName(outFile);
		p.addExecutionObserver(methodClass, "Z", flagLock, observerLock);
		p.addExecutionObserver(methodClass, "Z", flagUnlock, observerUnlock);
		p.setDecisionProcedureType(DecisionProcedureType.SICSTUS);
		p.setExternalDecisionProcedurePath(sicstusPath);
		p.setStepShowMode(StepShowMode.NONE);
		p.getRunnerParameters().setDepthScope(20);
	}
}
