package esecfse2013;

import static defs.Defs.*;

import java.io.FileNotFoundException;
import java.io.IOException;

import jbse.apps.run.RunParameters;
import jbse.apps.run.Run;
import jbse.apps.run.RunParameters.DecisionProcedureType;
import jbse.apps.run.RunParameters.GuidanceType;
import jbse.apps.run.RunParameters.StateFormatMode;
import jbse.apps.run.RunParameters.StepShowMode;
import jbse.apps.settings.ParseException;
import jbse.apps.settings.SettingsReader;

public class Launcher {
    public static void main(String[] args)	{
        final RunParameters p = new RunParameters();
        set(p);
        final Run r = new Run(p);
        r.run();
    }

    private static final String METHOD_CLASS             = "esecfse2013/Target"; 
    private static final String METHOD_DESCRIPTOR        = "(Ljava/util/List;)I"; 
    private static final String METHOD_NAME              = "sum";
    private static final String METHOD_NAME_GUIDE_LICS   = "guide_LICS";
    private static final String METHOD_NAME_GUIDE_NOLICS = "guide_noLICS";
    private static final String OUT_FILE                 = EXAMPLES_HOME + "out/esecfse2013.txt";
    private static final String SETTINGS_FILE_LICS       = EXAMPLES_HOME + "settings/esecfse2013_lics.jbse";
    private static final String SETTINGS_FILE_NOLICS     = EXAMPLES_HOME + "settings/esecfse2013_nolics.jbse";

    private static void set(RunParameters p) {
        try {
            new SettingsReader(SETTINGS_FILE_LICS).fillRunParameters(p);
            //new SettingsReader(SETTINGS_FILE_NOLICS).fillRunParameters(p);
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
        p.setMethodSignature(METHOD_CLASS, METHOD_DESCRIPTOR, METHOD_NAME);
        //p.setGuided(METHOD_CLASS, METHOD_NAME_GUIDE_LICS);
        //p.setGuided(METHOD_CLASS, METHOD_NAME_GUIDE_NOLICS);
        p.setGuidanceType(GuidanceType.JBSE);
        p.setStateFormatMode(StateFormatMode.TEXT);
        p.setStepShowMode(StepShowMode.LEAVES);
        p.setShowContradictory(false);
        p.setOutputFileName(OUT_FILE);
    }
}
