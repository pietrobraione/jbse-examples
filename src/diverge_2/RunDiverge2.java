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

    private static final String METHOD_CLASS      = "diverge_2/Diverge2"; 
    private static final String METHOD_DESCRIPTOR = "(I)V"; 
    private static final String METHOD_NAME       = "find"; 
    private static final String OUT_FILE          = EXAMPLES_HOME + "out/runDiverge2.txt";

    private static void set(RunParameters p) {
        p.setBootPath(JRE_PATH);
        p.addUserClasspath(CLASSPATH);
        p.addSourcePath(SOURCEPATH);
        p.setMethodSignature(METHOD_CLASS, METHOD_DESCRIPTOR, METHOD_NAME);
        p.setOutputFileName(OUT_FILE);
        p.setDecisionProcedureType(DecisionProcedureType.ALL_SAT);
        //p.setExternalDecisionProcedurePath(sicstusPath);
        p.setStepShowMode(StepShowMode.SOURCE);
        p.setStateFormatMode(StateFormatMode.TRACE);
        p.setHeapScope("diverge_2/Diverge2$Entry", 10);
        p.setDepthScope(10);
        p.setCountScope(1000);
    }
}
