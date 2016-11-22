package smalldemos.array_2;

import static defs.Defs.*;
import jbse.apps.run.RunParameters;
import jbse.apps.run.Run;
import jbse.apps.run.RunParameters.DecisionProcedureType;
import jbse.apps.run.RunParameters.StepShowMode;

public class RunArrayDemo2 {
	public static void main(String[] args)	{
		final RunParameters p = new RunParameters();
		set(p);
		final Run r = new Run(p);
		r.run();
	}

	private static final String methodClass     = "smalldemos/array_2/ArrayDemo2"; 
	private static final String methodParamsSig = "([I[Lsmalldemos/array_2/ArrayDemo2$Foo;)V"; 
	private static final String methodName      = "entryPoint"; 
	private static final String outFile         = examplesHome + "out/runArrayDemo2.txt";

	private static void set(RunParameters p) {
		p.addClasspath(classPath);
		p.addSourcePath(sourcePath);
		p.setMethodSignature(methodClass, methodParamsSig, methodName);
		p.setOutputFileName(outFile);
		p.setDecisionProcedureType(DecisionProcedureType.Z3);
		p.setExternalDecisionProcedurePath(sicstusPath);
		p.setStepShowMode(StepShowMode.LEAVES);
	}
}
