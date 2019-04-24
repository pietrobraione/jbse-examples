package diverge_2;

import static defs.Defs.*;
import jbse.apps.run.RunParameters;
import jbse.apps.run.Run;
import jbse.apps.run.RunParameters.DecisionProcedureType;
import jbse.apps.run.RunParameters.StateFormatMode;
import jbse.apps.run.RunParameters.StepShowMode;

public class RunDiverge2 {
    public static void main(String[] args)	{
        final RunParameters p = new RunParameters();
        set(p);
        final Run r = new Run(p);
        r.run();
    }

    private static final String METHOD_CLASS      = "diverge_2/Diverge2"; 
    private static final String METHOD_DESCRIPTOR = "(I)V"; 
    private static final String METHOD_NAME       = "find"; 
    private static final String OUT_FILE          = EXAMPLES_HOME + "out/runDiverge2.txt";

    private static void set(RunParameters p) {
        p.addUserClasspath(CLASSPATH);
        p.addSourcePath(SOURCEPATH);
        p.setMethodSignature(METHOD_CLASS, METHOD_DESCRIPTOR, METHOD_NAME);
        p.setOutputFileName(OUT_FILE);
        p.setDecisionProcedureType(DecisionProcedureType.Z3);
        p.setExternalDecisionProcedurePath(Z3_PATH);
        p.setStateFormatMode(StateFormatMode.TRACE);
        p.setStepShowMode(StepShowMode.ALL);
        p.setHeapScope("diverge_2/Diverge2$Entry", 10);
        p.setDepthScope(10);
        p.setCountScope(1000);
    }
}
