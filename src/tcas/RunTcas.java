package tcas;

import static defs.Defs.*;

import jbse.apps.run.RunParameters;
import jbse.apps.run.Run;
import jbse.apps.run.RunParameters.DecisionProcedureType;
import jbse.apps.run.RunParameters.StateFormatMode;
import jbse.apps.run.RunParameters.StepShowMode;

public class RunTcas {
	public static void main(String[] args)	{
		//prepares the parameters
		final RunParameters p = new RunParameters();
		set(p);

		//executes the method
		final Run r = new Run(p);
		r.run();
	}

	private static final String methodClass     = "tcas/Tcas"; 
	private static final String methodParamsSig = "()I"; 
	private static final String methodName      = "alt_sep_test"; 
	private static final String outFile         = examplesHome + "out/runTcas.txt";

	private static void set(RunParameters p) {
		p.addClasspath(classPath);
		p.addSourcePath(sourcePath);
		p.setMethodSignature(methodClass, methodParamsSig, methodName);
		p.setOutputFileName(outFile);
		//p.setDecisionProcedureType(DecisionProcedureType.SICSTUS);
		//p.setExternalDecisionProcedurePath(sicstusPath);
		p.setDecisionProcedureType(DecisionProcedureType.Z3);
		p.setExternalDecisionProcedurePath(z3Path);
		//p.setDecisionProcedureType(DecisionProcedureType.CVC4);
		//p.setExternalDecisionProcedurePath(cvc4Path);
		p.setStepShowMode(StepShowMode.ALL);
		p.setStateFormatMode(StateFormatMode.FULLTEXT);		
		//p.setShowDecisionProcedureInteraction(true);
		//p.setShowWarnings(false);
	}
}
