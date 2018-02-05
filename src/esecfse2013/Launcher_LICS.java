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

public class Launcher_LICS {
	public static void main(String[] args)	{
		final RunParameters p = new RunParameters();
		fill(p);

		final Run r = new Run(p);
		r.run();
	}
	
	private static void fill(RunParameters p) {
		try {
			new SettingsReader(examplesHome + "settings/esecfse2013_lics.jbse").fillRunParameters(p);
		} catch (FileNotFoundException e) {
			System.err.println("Error: settings file not found.");
			System.exit(1);
		} catch (ParseException e) {
			System.err.println("Error: settings file syntactically ill-formed: " + e.getMessage());
			System.exit(2);
		} catch (IOException e) {
			System.err.println("Error while closing settings file.");
			System.exit(2);
		}
		
		p.addClasspath(classPath);
		p.addSourcePath(sourcePath);
		p.setExternalDecisionProcedurePath(z3Path);
		p.setDecisionProcedureType(DecisionProcedureType.Z3);
		p.setDoEqualityAnalysis(true); 
		p.setDoSignAnalysis(true);
		p.setMethodSignature("esecfse2013/Target_LICS", "(Ljava/util/List;)I", "sum");
		p.setStepShowMode(StepShowMode.LEAVES);
		p.setStateFormatMode(StateFormatMode.FULLTEXT);
		p.setShowContradictory(false);
		p.setOutputFileName(examplesHome + "out/esecfse2013.txt");
	}
}
