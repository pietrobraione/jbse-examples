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

    private static final String METHOD_CLASS      = "testgen/Testgen"; 
    private static final String METHOD_DESCRIPTOR = "(Ltestgen/Testgen$Node;I)Ltestgen/Testgen$Node;"; 
    private static final String METHOD_NAME       = "getNode";
    private static final String OUT_FILE          = EXAMPLES_HOME + "out/TestSuite.java";

    private static void set(RunParameters p) {
        p.setBootPath(JRE_PATH);
        p.addUserClasspath(CLASSPATH);
        p.addSourcePath(SOURCEPATH);
        p.setMethodSignature(METHOD_CLASS, METHOD_DESCRIPTOR, METHOD_NAME);
        p.setOutputFileName(OUT_FILE);
        p.setDecisionProcedureType(DecisionProcedureType.Z3);
        p.setExternalDecisionProcedurePath(Z3_PATH);
        p.setStepShowMode(StepShowMode.LEAVES);
        p.setStateFormatMode(StateFormatMode.JUNIT_TEST);
        //p.setStateFormatMode(StateFormatMode.FULLTEXT);
        p.setShowWarnings(false);
    }
}
