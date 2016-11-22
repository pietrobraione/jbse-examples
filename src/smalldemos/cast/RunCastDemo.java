package smalldemos.cast;

import static defs.Defs.*;
import jbse.apps.run.RunParameters;
import jbse.apps.run.Run;
import jbse.apps.run.RunParameters.DecisionProcedureType;
import jbse.apps.run.RunParameters.StepShowMode;


public class RunCastDemo {
	public static void main(String[] args)	{
		RunParameters p = new RunParameters();
		setData(p);
		Run r = new Run(p);
		r.run();
	}

	private static final String methodClass     = "smalldemos/cast/CastDemo"; 
	private static final String methodParamsSig = "()V"; 
	private static final String methodName      = "entryPoint"; 
	private static final String outFile         = examplesHome + "out/runCastDemo.txt";

	private static void setData(RunParameters p) {
		p.addClasspath(classPath);
		p.addSourcePath(sourcePath);
		p.setMethodSignature(methodClass, methodParamsSig, methodName);
		p.setOutputFileName(outFile);
		p.setDecisionProcedureType(DecisionProcedureType.Z3);
		p.setExternalDecisionProcedurePath(sicstusPath);
		p.setStepShowMode(StepShowMode.ALL);
	}
}
