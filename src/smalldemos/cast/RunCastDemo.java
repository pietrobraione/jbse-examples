package smalldemos.cast;

import static defs.Defs.*;
import jbse.apps.run.RunParameters;
import jbse.apps.run.Run;
import jbse.apps.run.RunParameters.DecisionProcedureType;
import jbse.apps.run.RunParameters.StepShowMode;

public class RunCastDemo {
    public static void main(String[] args)	{
        RunParameters p = new RunParameters();
        setData(p);
        Run r = new Run(p);
        r.run();
    }

    private static final String METHOD_CLASS      = "smalldemos/cast/CastDemo"; 
    private static final String METHOD_DESCRIPTOR = "()V"; 
    private static final String METHOD_NAME       = "entryPoint"; 
    private static final String OUT_FILE          = EXAMPLES_HOME + "out/runCastDemo.txt";

    private static void setData(RunParameters p) {
        p.addUserClasspath(CLASSPATH);
        p.addSourcePath(SOURCEPATH);
        p.setMethodSignature(METHOD_CLASS, METHOD_DESCRIPTOR, METHOD_NAME);
        p.setOutputFileName(OUT_FILE);
        p.setDecisionProcedureType(DecisionProcedureType.Z3);
        p.setExternalDecisionProcedurePath(Z3_PATH);
        p.setStepShowMode(StepShowMode.LEAVES);
    }
}
