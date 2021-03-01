package symbols.string;

import static defs.Defs.*;

import java.io.IOException;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;

import jbse.apps.run.RunParameters;
import jbse.apps.run.Run;
import jbse.apps.run.RunParameters.DecisionProcedureType;
//import jbse.apps.run.RunParameters.GuidanceType;
import jbse.apps.run.RunParameters.StateFormatMode;
import jbse.apps.run.RunParameters.StepShowMode;
import jbse.apps.settings.ParseException;
import jbse.apps.settings.SettingsReader;

public class RunStringDemo {
    public static void main(String[] args)	{
        final RunParameters p = new RunParameters();
        set(p);
        final Run r = new Run(p);
        r.run();
    }

    private static final String METHOD_CLASS      = "symbols/string/StringDemo"; 
    private static final String METHOD_DESCRIPTOR = "(Lsymbols/string/StringDemo$C1;Lsymbols/string/StringDemo$C2;)I"; 
    private static final String METHOD_NAME       = "entryPoint"; 
    private static final Path OUT_FILE            = EXAMPLES_HOME.resolve("out/runSymbolsStringDemo.txt");
    private static final Path SETTINGS_FILE       = EXAMPLES_HOME.resolve("settings/strings.jbse");

    private static void set(RunParameters p) {
        try {
            new SettingsReader(SETTINGS_FILE).fillRunParameters(p);
        } catch (NoSuchFileException e) {
            System.err.println("Error: settings file not found.");
            System.exit(1);
        } catch (ParseException e) {
            System.err.println("Error: settings file syntactically ill-formed.");
            System.err.println(e);
            System.exit(2);
        } catch (IOException e) {
            System.err.println("Error while closing settings file.");
            System.exit(2);
        }
        
        p.setJBSELibPath(JBSE_CLASSPATH);
        p.addUserClasspath(CLASSPATH);
        p.addSourcePath(SOURCEPATH);
        p.setMethodSignature(METHOD_CLASS, METHOD_DESCRIPTOR, METHOD_NAME);
        p.setOutputFilePath(OUT_FILE);
        p.setDecisionProcedureType(DecisionProcedureType.Z3);
        p.setExternalDecisionProcedurePath(Z3_PATH);
        p.setStateFormatMode(StateFormatMode.TEXT);
        p.setStepShowMode(StepShowMode.LEAVES);
        p.addUninterpreted("symbols/string/StringDemo$C1", "()Ljava/lang/String;", "toString");
        p.addUninterpreted("symbols/string/StringDemo$C2", "()Ljava/lang/String;", "toString");
        p.addUninterpreted("java/lang/String", "(Ljava/lang/Object;)Z", "equals");
        //p.setGuided("symbols/string/StringDemo", "guidanceStart");
        //p.setGuidanceType(GuidanceType.JDI);
    }
}
