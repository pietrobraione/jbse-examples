package symbols.basic;

import static defs.Defs.*;

import jbse.apps.run.RunParameters;
import jbse.apps.run.Run;
import jbse.apps.run.RunParameters.DecisionProcedureType;
import jbse.apps.run.RunParameters.StateFormatMode;
import jbse.apps.run.RunParameters.StepShowMode;

public class RunSymbols {
    public static void main(String[] args) {
        final RunParameters p = new RunParameters();
        set(p);
        final Run r = new Run(p);
        r.run();
    }

    private static final String METHOD_CLASS      = "symbols/Symbols"; 
    private static final String METHOD_DESCRIPTOR = "(Ljava/util/LinkedList;D)Ljava/lang/String;"; 
    private static final String METHOD_NAME       = "m"; 
    private static final String OUT_FILE          = EXAMPLES_HOME + "out/runSymbols.txt";

    private static void set(RunParameters p) {
        p.addUserClasspath(CLASSPATH);
        p.addSourcePath(SOURCEPATH);
        p.setMethodSignature(METHOD_CLASS, METHOD_DESCRIPTOR, METHOD_NAME);
        p.setOutputFileName(OUT_FILE);
        p.setDecisionProcedureType(DecisionProcedureType.Z3);
        p.setExternalDecisionProcedurePath(Z3_PATH);
        p.setShowDecisionProcedureInteraction(false);
        p.setStateFormatMode(StateFormatMode.TEXT);
        p.setStepShowMode(StepShowMode.LEAVES);
        p.addUninterpreted("java/util/AbstractCollection", "()Ljava/lang/String;", "toString");
        p.addUninterpreted("java/util/AbstractList", "(Ljava/lang/Object;)Z", "equals");
    }
}
