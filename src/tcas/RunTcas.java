package tcas;

import static defs.Defs.*;

import jbse.apps.run.RunParameters;
import jbse.apps.run.Run;
import jbse.apps.run.RunParameters.DecisionProcedureType;
import jbse.apps.run.RunParameters.StateFormatMode;
import jbse.apps.run.RunParameters.StepShowMode;

public class RunTcas {
    public static void main(String[] args) {
        final RunParameters p = new RunParameters();
        set(p);
        final Run r = new Run(p);
        r.run();
    }

    private static final String METHOD_CLASS      = "tcas/Tcas"; 
    private static final String METHOD_DESCRIPTOR = "()I"; 
    private static final String METHOD_NAME       = "alt_sep_test"; 
    private static final String OUT_FILE          = EXAMPLES_HOME + "out/runTcas.txt";

    private static void set(RunParameters p) {
        p.addUserClasspath(CLASSPATH);
        p.addSourcePath(SOURCEPATH);
        p.setMethodSignature(METHOD_CLASS, METHOD_DESCRIPTOR, METHOD_NAME);
        p.setOutputFileName(OUT_FILE);
        p.setDecisionProcedureType(DecisionProcedureType.Z3);
        p.setExternalDecisionProcedurePath(Z3_PATH);
        p.setStateFormatMode(StateFormatMode.TRACE);    
        p.setStepShowMode(StepShowMode.LEAVES);
    }
}
