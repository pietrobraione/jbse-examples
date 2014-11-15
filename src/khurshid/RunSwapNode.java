package khurshid;

import static defs.Defs.*;
import jbse.apps.run.RunParameters;
import jbse.apps.run.Run;
import jbse.apps.run.RunParameters.DecisionProcedureType;
import jbse.apps.run.RunParameters.StateFormatMode;
import jbse.apps.run.RunParameters.StepShowMode;

public class RunSwapNode {
	public static void main(String[] args)	{
		RunParameters p = new RunParameters();
		set(p);
		Run r = new Run(p);
		r.run();
	}

	private static final String methodClass     = "khurshid/Node"; 
	private static final String methodParamsSig = "()Lkhurshid/Node;"; 
	private static final String methodName      = "swapNode"; 
	private static final String outFile         = examplesHome + "out/runSwapNode.txt";

	private static void set(RunParameters p) {
		p.addClasspath(classPath);
		p.addSourcePath(sourcePath);
		p.setMethodSignature(methodClass, methodParamsSig, methodName);
		p.setOutputFileName(outFile);
		p.setDecisionProcedureType(DecisionProcedureType.SICSTUS);
		p.setExternalDecisionProcedurePath(sicstusPath);
		p.setStepShowMode(StepShowMode.ALL);
		p.setStateFormatMode(StateFormatMode.FULLTEXT);
	}
}
