package smalldemos.xswitch;

import static defs.Defs.*;
import jbse.apps.run.RunParameters;
import jbse.apps.run.Run;
import jbse.apps.run.RunParameters.DecisionProcedureType;
import jbse.apps.run.RunParameters.StepShowMode;

public class RunSwitchDemo {
    public static void main(String[] args)	{
        RunParameters p = new RunParameters();
        set(p);
        Run r = new Run(p);
        r.run();
    }

    private static final String METHOD_CLASS      = "smalldemos/xswitch/SwitchDemo"; 
    private static final String METHOD_DESCRIPTOR = "(I)I"; 
    private static final String METHOD_NAME       = "entryPoint"; 
    private static final String OUT_FILE          = EXAMPLES_HOME + "out/runSwitchDemo.txt";

    private static void set(RunParameters p) {
        p.setBootPath(JRE_PATH);
        p.addUserClasspath(CLASSPATH);
        p.addSourcePath(SOURCEPATH);
        p.setMethodSignature(METHOD_CLASS, METHOD_DESCRIPTOR, METHOD_NAME);
        p.setOutputFileName(OUT_FILE);
        p.setDecisionProcedureType(DecisionProcedureType.Z3);
        p.setExternalDecisionProcedurePath(Z3_PATH);
        p.setShowDecisionProcedureInteraction(false);
        p.setStepShowMode(StepShowMode.LEAVES);
    }
}
