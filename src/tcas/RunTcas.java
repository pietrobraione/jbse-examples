package tcas;

import static defs.Defs.*;

import java.nio.file.Path;

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
    private static final Path   OUT_FILE          = EXAMPLES_HOME.resolve("out/runTcas.txt");

    private static void set(RunParameters p) {
        p.setJBSELibPath(JBSE_CLASSPATH);
        p.addUserClasspath(CLASSPATH);
        p.addSourcePath(SOURCEPATH);
        p.setMethodSignature(METHOD_CLASS, METHOD_DESCRIPTOR, METHOD_NAME);
        p.setOutputFilePath(OUT_FILE);
        p.setDecisionProcedureType(DecisionProcedureType.Z3);
        p.setExternalDecisionProcedurePath(Z3_PATH);
        p.setStateFormatMode(StateFormatMode.PATH); 
        p.setStepShowMode(StepShowMode.LEAVES);
        p.setShowDetailedDecisionProcedureStats(true);
    }
}
