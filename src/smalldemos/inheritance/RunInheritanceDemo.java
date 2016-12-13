package smalldemos.inheritance;

import static defs.Defs.*;
import jbse.apps.run.RunParameters;
import jbse.apps.run.Run;
import jbse.apps.run.RunParameters.DecisionProcedureType;
import jbse.apps.run.RunParameters.StepShowMode;

public class RunInheritanceDemo {
	public static void main(String[] args)	{
		final RunParameters p = new RunParameters();
		set(p);
		final Run r = new Run(p);
		r.run();
	}

	private static final String methodClass      = "smalldemos/inheritance/InheritanceDemo"; 
	private static final String methodParamsSig  = "()V"; 
	private static final String methodName       = "entryPoint"; 
	private static final String outFile          = examplesHome + "out/runInheritanceDemo.txt";

	private static void set(RunParameters p) {
		p.addClasspath(classPath);
		p.addSourcePath(sourcePath);
		p.setMethodSignature(methodClass, methodParamsSig, methodName);
		p.setOutputFileName(outFile);
		p.setDecisionProcedureType(DecisionProcedureType.Z3);
		p.setExternalDecisionProcedurePath(z3Path);
		p.setStepShowMode(StepShowMode.ALL);
	}
}
