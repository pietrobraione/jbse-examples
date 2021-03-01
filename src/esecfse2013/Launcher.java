package esecfse2013;

import static defs.Defs.*;

import java.io.IOException;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;

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

    private static final boolean LICS = true; //choose LICS or noLICS
    private static final boolean GUIDE = false; //choose guided execution or not
    
    private static final String METHOD_CLASS_LICS    = "esecfse2013/Target_LICS"; 
    private static final String METHOD_CLASS_NOLICS  = "esecfse2013/Target_noLICS"; 
    private static final String METHOD_CLASS         = LICS ? METHOD_CLASS_LICS : METHOD_CLASS_NOLICS;
    private static final String METHOD_DESCRIPTOR    = "(Ljava/util/List;)I"; 
    private static final String METHOD_NAME          = "sum";
    private static final String METHOD_NAME_GUIDE    = "guide";
    private static final Path   OUT_FILE             = EXAMPLES_HOME.resolve("out/esecfse2013.txt");
    private static final Path   SETTINGS_FILE_LICS   = EXAMPLES_HOME.resolve("settings/esecfse2013_lics.jbse");
    private static final Path   SETTINGS_FILE_NOLICS = EXAMPLES_HOME.resolve("settings/esecfse2013_nolics.jbse");
    private static final Path   SETTINGS_FILE        = LICS ? SETTINGS_FILE_LICS : SETTINGS_FILE_NOLICS;

    private static void set(RunParameters p) {
        try {
            new SettingsReader(SETTINGS_FILE).fillRunParameters(p);
        } catch (NoSuchFileException e) {
            System.err.println("Error: settings file not found.");
            System.exit(1);
        } catch (ParseException e) {
            System.err.println("Error: settings file syntactically ill-formed.");
            System.exit(2);
        } catch (IOException e) {
            System.err.println("Error while closing settings file.");
            System.exit(2);
        }

        p.setJBSELibPath(JBSE_CLASSPATH);
        p.addUserClasspath(CLASSPATH);
        p.addSourcePath(SOURCEPATH);
        p.setExternalDecisionProcedurePath(Z3_PATH);
        p.setDecisionProcedureType(DecisionProcedureType.Z3);
        p.setDoEqualityAnalysis(true); 
        p.setDoSignAnalysis(true);
        p.setMethodSignature(METHOD_CLASS, METHOD_DESCRIPTOR, METHOD_NAME);
        p.setStateFormatMode(StateFormatMode.PATH);
        p.setStepShowMode(StepShowMode.LEAVES);
        p.setShowContradictory(false);
        p.setOutputFilePath(OUT_FILE);
        
        if (GUIDE) {
        	p.setGuided(METHOD_CLASS, METHOD_NAME_GUIDE);
        	p.setGuidanceType(GuidanceType.JDI);
        }
    }
}
