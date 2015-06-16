package smalldemos.ifx;

import static defs.Defs.*;
import jbse.apps.run.RunParameters;
import jbse.apps.run.Run;
import jbse.apps.run.RunParameters.DecisionProcedureType;
import jbse.apps.run.RunParameters.StateFormatMode;
import jbse.apps.run.RunParameters.StepShowMode;

public class RunIf {
	public static void main(String[] args)	{
		final RunParameters p = new RunParameters();
		set(p);
		final Run r = new Run(p);
		r.run();
	}

	private static final String methodClass     = "smalldemos/ifx/IfExample"; 
	private static final String methodParamsSig = "(I)V"; 
	private static final String methodName      = "m"; 
	private static final String outFile         = examplesHome + "out/runIf_z3.txt";

	private static void set(RunParameters p) {
		p.addClasspath(classPath);
		p.addSourcePath(sourcePath);
		p.setMethodSignature(methodClass, methodParamsSig, methodName);
		p.setOutputFileName(outFile);
		//p.setDecisionProcedure(DecisionProcedureType.SICSTUS);
		//p.setExternalDecisionProcedurePath(sicstusPath);
		p.setDecisionProcedureType(DecisionProcedureType.Z3);
		p.setExternalDecisionProcedurePath(z3Path);
		p.setShowDecisionProcedureInteraction(false);
		p.setStepShowMode(StepShowMode.ALL);
		p.setStateFormatMode(StateFormatMode.FULLTEXT);
		p.setShowWarnings(false);
	}
}
