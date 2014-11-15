package diverge_2;

import static defs.Defs.*;
import jbse.apps.run.RunParameters;
import jbse.apps.run.Run;
import jbse.apps.run.RunParameters.DecisionProcedureType;
import jbse.apps.run.RunParameters.StateFormatMode;
import jbse.apps.run.RunParameters.StepShowMode;

public class RunDiverge2 {
	public static void main(String[] args)	{
		RunParameters p = new RunParameters();
		set(p);
		Run r = new Run(p);
		r.run();
	}

	private static final String methodClass     = "diverge_2/Diverge2"; 
	private static final String methodParamsSig = "(I)V"; 
	private static final String methodName      = "find"; 
	private static final String outFile         = examplesHome + "out/runDiverge2.txt";

	private static void set(RunParameters p) {
		p.addClasspath(classPath);
		p.addSourcePath(sourcePath);
		p.setMethodSignature(methodClass, methodParamsSig, methodName);
		p.setOutputFileName(outFile);
		p.setDecisionProcedureType(DecisionProcedureType.ALL_SAT);
		//p.setExternalDecisionProcedurePath(sicstusPath);
		p.setStepShowMode(StepShowMode.SOURCE);
		p.setStateFormatMode(StateFormatMode.TRACE);
		//p.getRunnerParameters().setHeapScope("diverge_2/Diverge2$Entry", 10);
		//p.getRunnerParameters().setDepthScope(10);
		//p.getRunnerParameters().setCountScope(1000);
	}
}
