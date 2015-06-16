package tcas;

import static defs.Defs.*;

import java.io.FileNotFoundException;

import jbse.apps.run.RunParameters;
import jbse.apps.run.Run;
import jbse.apps.run.RunParameters.DecisionProcedureType;
import jbse.apps.run.RunParameters.StateFormatMode;
import jbse.apps.run.RunParameters.StepShowMode;
import jbse.apps.settings.ParseException;
import jbse.apps.settings.SettingsReader;

public class RunTcas {
	private static final String settingsFile = examplesHome + "settings/tcas.jbse";

	public static void main(String[] args)	{
		//prepares the parameters
		final RunParameters p = new RunParameters();
		try {
			new SettingsReader(settingsFile).fillRunParameters(p);
		} catch (FileNotFoundException e) {
			System.err.println("ERROR: settings file not found.");
			return;
		} catch (ParseException e) {
			System.err.println("ERROR: settings file ill-formed.");
			return;
		}
		set(p);

		//executes the method
		final Run r = new Run(p);
		r.run();
	}

	private static final String methodClass     = "tcas/Tcas"; 
	private static final String methodParamsSig = "()I"; 
	private static final String methodName      = "alt_sep_test"; 
	private static final String outFile         = examplesHome + "out/runTcas.txt";

	private static void set(RunParameters p) {
		p.addClasspath(classPath);
		p.addSourcePath(sourcePath);
		p.setMethodSignature(methodClass, methodParamsSig, methodName);
		p.setOutputFileName(outFile);
		p.setDecisionProcedureType(DecisionProcedureType.SICSTUS);
		p.setExternalDecisionProcedurePath(sicstusPath);
		p.setStepShowMode(StepShowMode.ALL);
		p.setStateFormatMode(StateFormatMode.TRACE);		
		//p.setShowDecisionProcedureInteraction(true);
		//p.setShowWarnings(false);
	}
}
