package esecfse2013;

import static defs.Defs.*;

import java.io.FileNotFoundException;
import java.io.IOException;

import jbse.apps.run.RunParameters;
import jbse.apps.run.Run;
import jbse.apps.run.RunParameters.DecisionProcedureType;
import jbse.apps.run.RunParameters.StateFormatMode;
import jbse.apps.run.RunParameters.StepShowMode;
import jbse.apps.settings.ParseException;
import jbse.apps.settings.SettingsReader;

public class Launcher {
    public static void main(String[] args)	{
        final RunParameters p = new RunParameters();
        fill(p);

        final Run r = new Run(p);
        r.run();
    }

    private static void fill(RunParameters p) {
        try {
            new SettingsReader(EXAMPLES_HOME + "settings/esecfse2013_lics.jbse").fillRunParameters(p);
            //new SettingsReader(examplesHome + "settings/esecfse2013_nolics.jbse").fillRunParameters(p);
        } catch (FileNotFoundException e) {
            System.err.println("Error: settings file not found.");
            System.exit(1);
        } catch (ParseException e) {
            System.err.println("Error: settings file syntactically ill-formed.");
            System.exit(2);
        } catch (IOException e) {
            System.err.println("Error while closing settings file.");
            System.exit(2);
        }

        p.addUserClasspath(CLASSPATH);
        p.addSourcePath(SOURCEPATH);
        p.setExternalDecisionProcedurePath(Z3_PATH);
        p.setDecisionProcedureType(DecisionProcedureType.Z3);
        p.setDoEqualityAnalysis(true); 
        p.setDoSignAnalysis(true);
        p.setMethodSignature("esecfse2013/Target", "(Ljava/util/List;)I", "sum");
        //p.setGuided("esecfse2013/Target", "()V", "g");
        p.setStepShowMode(StepShowMode.LEAVES);
        p.setStateFormatMode(StateFormatMode.FULLTEXT);
        p.setShowContradictory(false);
        p.setOutputFileName(EXAMPLES_HOME + "out/esecfse2013.txt");
    }
}
