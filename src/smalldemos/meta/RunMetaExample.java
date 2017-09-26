package smalldemos.meta;

import static defs.Defs.*;
import jbse.apps.run.RunParameters;
import jbse.apps.run.Run;
import jbse.apps.run.RunParameters.DecisionProcedureType;
import jbse.apps.run.RunParameters.StepShowMode;

public class RunMetaExample {
	public static void main(String[] args)	{
		final RunParameters p = new RunParameters();
		set(p);
		final Run r = new Run(p);
		r.run();
	}

	private static final String methodClass     = "smalldemos/meta/MetaExample"; 
	private static final String methodParamsSig = "(I)I"; 
	private static final String methodName      = "f"; 
	private static final String outFile         = examplesHome + "out/runMetaExample.txt";

	private static void set(RunParameters p) {
		p.addClasspath(classPath);
		p.addSourcePath(sourcePath);
		p.setMethodSignature(methodClass, methodParamsSig, methodName);
		p.setOutputFileName(outFile);
		p.setDecisionProcedureType(DecisionProcedureType.Z3);
		p.setExternalDecisionProcedurePath(z3Path);
		p.setStepShowMode(StepShowMode.LEAVES);
	}
}
