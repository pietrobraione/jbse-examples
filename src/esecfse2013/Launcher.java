package esecfse2013;

import static defs.Defs.*;

import java.io.FileNotFoundException;

import jbse.apps.run.RunParameters;
import jbse.apps.run.Run;
import jbse.apps.run.RunParameters.DecisionProcedureType;
import jbse.apps.run.RunParameters.StateFormatMode;
import jbse.apps.run.RunParameters.StepShowMode;
import jbse.apps.settings.ParseException;
import jbse.apps.settings.SettingsReader;

public class Launcher {
	public static void main(String[] args)	{
		RunParameters p = new RunParameters();
		fill(p);

		Run r = new Run(p);
		r.run();
	}
	
	private static void fill(RunParameters p) {
		try {
			new SettingsReader(examplesHome + "settings/esecfse2013_lics.jbse").fillRunParameters(p);
			//new SettingsReader(examplesHome + "settings/esecfse2013_nolics.jbse").fillRunParameters(p);
		} catch (FileNotFoundException e) {
			System.err.println("Error: settings file not found.");
			System.exit(1);
		} catch (ParseException e) {
			System.err.println("Error: settings file syntactically ill-formed.");
			System.exit(2);
		}
		
		p.addClasspath(classPath);
		p.addSourcePath(sourcePath);
		p.setExternalDecisionProcedurePath(sicstusPath);
		p.setDecisionProcedureType(DecisionProcedureType.SICSTUS);
		p.setDoEqualityAnalysis(true); 
		p.setDoSignAnalysis(true);
		p.setMethodSignature("esecfse2013/Target", "(Ljava/util/List;)I", "sum");
		p.setStepShowMode(StepShowMode.SOURCE);
		p.setStateFormatMode(StateFormatMode.FULLTEXT);
		p.setShowContradictory(false);
		p.setOutputFileName(examplesHome + "out/esecfse2013.txt");
	}
}
