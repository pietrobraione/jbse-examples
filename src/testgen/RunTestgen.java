package testgen;

import static defs.Defs.*;

import jbse.apps.run.RunParameters;
import jbse.apps.run.Run;
import jbse.apps.run.RunParameters.DecisionProcedureType;
import jbse.apps.run.RunParameters.StateFormatMode;
import jbse.apps.run.RunParameters.StepShowMode;

public class RunTestgen {
	public static void main(String[] args) {
        final RunParameters p = new RunParameters();
        set(p);
        final Run r = new Run(p);
        r.run();
	}
	
	private static final String methodClass      = "testgen/Testgen"; 
	private static final String methodParamsSig  = "(Ltestgen/Testgen$Node;I)Ltestgen/Testgen$Node;"; 
	private static final String methodName       = "getNode";
	private static final String outFile          = examplesHome + "out/TestSuite.java";
	//private static final String outFile          = examplesHome + "out/runTestgen.txt";
	
    private static void set(RunParameters p) {
		p.addClasspath(classPath);
		p.addSourcePath(sourcePath);
		p.setMethodSignature(methodClass, methodParamsSig, methodName);
		p.setOutputFileName(outFile);
        p.setDecisionProcedureType(DecisionProcedureType.Z3);
        p.setExternalDecisionProcedurePath(z3Path);
        p.setStepShowMode(StepShowMode.LEAVES);
        p.setStateFormatMode(StateFormatMode.JUNIT_TEST);
        //p.setStateFormatMode(StateFormatMode.FULLTEXT);
        p.setShowWarnings(false);
    }
}
