package smalldemos.array_1;

import static defs.Defs.*;

import jbse.apps.run.RunParameters;
import jbse.apps.run.Run;
import jbse.apps.run.RunParameters.DecisionProcedureType;
import jbse.apps.run.RunParameters.InteractionMode;
import jbse.apps.run.RunParameters.StepShowMode;

public class RunArrayDemo1 {
	public static void main(String[] args)	{
		final RunParameters p = new RunParameters();
		set(p);
		final Run r = new Run(p);
		r.run();
	}

	private static final String methodClass      = "smalldemos/array_1/ArrayDemo1"; 
	private static final String methodParamsSig  = "(I)V"; 
	private static final String methodName       = "entryPoint"; 
	private static final String outFile          = examplesHome + "out/runArrayDemo1.txt";

	private static void set(RunParameters p) {
		p.addClasspath(classPath);
		p.addSourcePath(sourcePath);
		p.setMethodSignature(methodClass, methodParamsSig, methodName);
		p.setOutputFileName(outFile);
		p.setDecisionProcedureType(DecisionProcedureType.SICSTUS);
		p.setExternalDecisionProcedurePath(sicstusPath);
		p.setDoSignAnalysis(true);
		p.setStepShowMode(StepShowMode.ALL);
		p.setInteractionMode(InteractionMode.ONLY_BRANCH_DECISION);
	}
}
