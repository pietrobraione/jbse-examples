package diverge_1;

import static defs.Defs.*;
import jbse.apps.run.RunParameters;
import jbse.apps.run.Run;
import jbse.apps.run.RunParameters.DecisionProcedureType;
import jbse.apps.run.RunParameters.StepShowMode;

public class RunDiverge1 {
	public static void main(String[] args)	{
		RunParameters p = new RunParameters();
		set(p);
		Run r = new Run(p);
		r.run();
	}

	private static final String methodClass     = "diverge_1/Diverge1"; 
	private static final String methodParamsSig = "(I)V"; 
	private static final String methodName      = "m"; 
	private static final String outFile         = examplesHome + "out/runDiverge1.txt";

	private static void set(RunParameters p) {
		p.addClasspath(classPath);
		p.addSourcePath(sourcePath);
		p.setMethodSignature(methodClass, methodParamsSig, methodName);
		p.setOutputFileName(outFile);
		p.setDecisionProcedureType(DecisionProcedureType.SICSTUS);
		p.setExternalDecisionProcedurePath(sicstusPath);
		p.setStepShowMode(StepShowMode.ALL);
	}
}
