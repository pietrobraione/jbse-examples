package smalldemos.invokedynamic_2;

import static defs.Defs.*;
import jbse.apps.run.RunParameters;
import jbse.apps.run.Run;
import jbse.apps.run.RunParameters.DecisionProcedureType;
import jbse.apps.run.RunParameters.StateFormatMode;
import jbse.apps.run.RunParameters.StepShowMode;

public class RunInvokeDynamicDemo {
    public static void main(String[] args)	{
        final RunParameters p = new RunParameters();
        set(p);
        final Run r = new Run(p);
        r.run();
    }

    private static final String METHOD_CLASS      = "smalldemos/invokedynamic_2/InvokeDynamicDemo"; 
    private static final String METHOD_DESCRIPTOR = "([Ljava/lang/String;)V"; 
    private static final String METHOD_NAME       = "main"; 
    private static final String OUT_FILE          = EXAMPLES_HOME + "out/runInvokeDynamicDemo2.txt";

    private static void set(RunParameters p) {
        p.setJBSELibPath(JBSE_CLASSPATH);
        p.addUserClasspath(CLASSPATH);
        p.addUserClasspath(EXAMPLES_HOME + "asm-5.1.jar");
        p.addSourcePath(SOURCEPATH);
        p.setMethodSignature(METHOD_CLASS, METHOD_DESCRIPTOR, METHOD_NAME);
        p.setOutputFileName(OUT_FILE);
        p.setDecisionProcedureType(DecisionProcedureType.Z3);
        p.setExternalDecisionProcedurePath(Z3_PATH);
        p.setStateFormatMode(StateFormatMode.TEXT);
        p.setStepShowMode(StepShowMode.NONE);
        p.setBypassStandardLoading(false);
    }
}
