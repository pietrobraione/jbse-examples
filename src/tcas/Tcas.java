package tcas;

import static jbse.meta.Analysis.assumeClassNotInitialized;
import static jbse.meta.Analysis.force;


/** Tcas.java
 * Original C version:
 * Revision 1.2  1993/03/12  19:29:50  foster
 */

public class Tcas {
//BEGIN INSTRUMENTATION
@SuppressWarnings("unused")
private static boolean _ATASTEN;
@SuppressWarnings("unused")
private static boolean _ATASTUPRA;	
@SuppressWarnings("unused")
private static boolean _ATASTDOWNRA;	
@SuppressWarnings("unused")
private static boolean _UPSEPADEQUATE;
@SuppressWarnings("unused")
private static boolean _DOWNSEPADEQUATE;
@SuppressWarnings("unused")
private static boolean _UPSEPBEST;
@SuppressWarnings("unused")
private static boolean _DOWNSEPBEST;
@SuppressWarnings("unused")
private static boolean _OWNOVER;
@SuppressWarnings("unused")
private static boolean _OTHEROVER;
//END INSTRUMENTATION
	
	private static class Constants {
		public static final int UNRESOLVED  = 0;
		public static final int UPWARD_RA   = 1;
		public static final int DOWNWARD_RA = 2;

		public static final int NO_INTENT      = 0;
		@SuppressWarnings("unused")
		public static final int DO_NOT_CLIMB   = 1;
		@SuppressWarnings("unused")
		public static final int DO_NOT_DESCEND = 2;

		public static final int TCAS_TA = 1;
		@SuppressWarnings("unused")
		public static final int OTHER   = 2;

		private static final int OLEV       = 600; //in feets/minute
		private static final int MAXALTDIFF = 600; //max altitude difference in feet
		private static final int MINSEP     = 300; //min separation in feet
		private static final int NOZCROSS   = 100; //in feet

		private static final int[] Positive_RA_Alt_Thresh = { 400, 500, 640, 740 };
	}

	public int Cur_Vertical_Sep;
	public boolean High_Confidence;
	public boolean Two_of_Three_Reports_Valid;

	public int Own_Tracked_Alt;
	public int Own_Tracked_Alt_Rate;
	public int Other_Tracked_Alt;

	public int Alt_Layer_Value; //0, 1, 2, 3

	public int Up_Separation;
	public int Down_Separation;

	public int Other_RAC; //NO_INTENT, DO_NOT_CLIMB, DO_NOT_DESCEND

	public int Other_Capability; //TCAS_TA, OTHER

	public boolean Climb_Inhibit;

	public Tcas() {	}

	private int ALIM()
	{
		return Constants.Positive_RA_Alt_Thresh[Alt_Layer_Value];
		
		//modified from above to avoid symbolic referencing of the array
		/*
		if(Alt_Layer_Value == 0) {
			return Constants.Positive_RA_Alt_Thresh[0];
		} else if(Alt_Layer_Value == 1)  {
			return Constants.Positive_RA_Alt_Thresh[1];
		} else if(Alt_Layer_Value == 2)  {
			return Constants.Positive_RA_Alt_Thresh[2];
		} else {
			return Constants.Positive_RA_Alt_Thresh[3];
		}
		*/
	}

	@SuppressWarnings("unused")
	private int Inhibit_Biased_Climb()
	{
		if(Climb_Inhibit) {
			return Up_Separation + Constants.NOZCROSS;
		} else {
			return Up_Separation;
		}
	}

	private boolean Non_Crossing_Biased_Climb()
	{
		boolean upward_preferred;
		boolean result;

		if(Up_Separation > Down_Separation) {
		//if(Inhibit_Biased_Climb() > Down_Separation) {
			upward_preferred = true;
		} else { 
			upward_preferred = false;
		}
		if (upward_preferred)
		{
			if(!(Own_Below_Threat()) || ((Own_Below_Threat()) && (!(Down_Separation >= ALIM())))) {
				result = true;
			} else {
				result = false;
			}
		} else {	
			if(Own_Above_Threat() && (Cur_Vertical_Sep >= Constants.MINSEP) && (Up_Separation >= ALIM())) {
				result = true;
			} else { 
				result = false;
			}
		}
		return result;
	}

	private boolean Non_Crossing_Biased_Descend()
	{
		boolean upward_preferred;
		boolean result;

		if(Up_Separation > Down_Separation) {
		//if(Inhibit_Biased_Climb() > Down_Separation) {
			upward_preferred = true;
		} else {
			upward_preferred = false;
		}

		if (upward_preferred) {
			if(Own_Below_Threat() && (Cur_Vertical_Sep >= Constants.MINSEP) && (Down_Separation >= ALIM())) {
				result = true;
			} else { 
				result = false;
			}
		} else {
			if(!(Own_Above_Threat()) || ((Own_Above_Threat()) && (Up_Separation >= ALIM())))
				result = true;
			else result = false;
		}
		return result;
	}

	private boolean Own_Below_Threat()
	{
		if(Own_Tracked_Alt < Other_Tracked_Alt) {
			return true;
		} else {
			return false;
		}
	}

	private boolean Own_Above_Threat()
	{
		if(Other_Tracked_Alt < Own_Tracked_Alt)
			return true;
		else return false;
	}

	public int alt_sep_test()
	{ 
		assumeClassNotInitialized("tcas/Tcas$Constants");
_ATASTEN = false; 
_ATASTUPRA = false; 
_ATASTDOWNRA = false; 
//_UPSEPADEQUATE = force(Up_Separation >= Constants.Positive_RA_Alt_Thresh[Alt_Layer_Value]); 
//_DOWNSEPADEQUATE = force(Down_Separation >= Constants.Positive_RA_Alt_Thresh[Alt_Layer_Value]); 
_UPSEPBEST = force(Up_Separation > Down_Separation); 
_DOWNSEPBEST = force(Down_Separation > Up_Separation); 
//_OWNOVER = force(Own_Tracked_Alt > Other_Tracked_Alt); 
//_OTHEROVER = force(Other_Tracked_Alt > Own_Tracked_Alt);

		boolean enabled, tcas_equipped, intent_not_known;
		boolean need_upward_RA, need_downward_RA;
		int alt_sep;
		if(High_Confidence && (Own_Tracked_Alt_Rate <= Constants.OLEV) && (Cur_Vertical_Sep > Constants.MAXALTDIFF))
			enabled = true;
		else enabled = false;
		if(Other_Capability == Constants.TCAS_TA)
			tcas_equipped = true;
		else tcas_equipped = false;
		if(Two_of_Three_Reports_Valid && Other_RAC == Constants.NO_INTENT)
			intent_not_known = true;
		else intent_not_known = false;

		alt_sep = Constants.UNRESOLVED;

		if (enabled && ((tcas_equipped && intent_not_known) || !tcas_equipped))
		{
_ATASTEN = true;
			if(Non_Crossing_Biased_Climb() && Own_Below_Threat()) {
				need_upward_RA = true;
			} else { 
				need_upward_RA = false;
			}
_ATASTEN = false;
			if (Non_Crossing_Biased_Descend() && Own_Above_Threat()) {
				need_downward_RA = true;
			} else {
				need_downward_RA = false;
			}
			if (need_upward_RA && need_downward_RA) {
			 /* unreachable: requires Own_Below_Threat and Own_Above_Threat
           to both be true - that requires Own_Tracked_Alt < Other_Tracked_Alt
           and Other_Tracked_Alt < Own_Tracked_Alt, which isn't possible */
          		alt_sep = Constants.UNRESOLVED;
			} else if (need_upward_RA) {
_ATASTUPRA = true;
				alt_sep = Constants.UPWARD_RA;
_ATASTUPRA = false;
			} else if (need_downward_RA) {
_ATASTDOWNRA = true;
				alt_sep = Constants.DOWNWARD_RA;
_ATASTDOWNRA = false;
			} else {
				alt_sep = Constants.UNRESOLVED;
			}
		}

		return alt_sep;
	}

	public static void main(String[] args)
	{
		if(args.length < 12)
		{
			System.out.println("Error: Command line arguments are");
			System.out.println("Cur_Vertical_Sep, High_Confidence, Two_of_Three_Reports_Valid");
			System.out.println("Own_Tracked_Alt, Own_Tracked_Alt_Rate, Other_Tracked_Alt");
			System.out.println("Alt_Layer_Value, Up_Separation, Down_Separation");
			System.out.println("Other_RAC, Other_Capability, Climb_Inhibit");
			System.exit(1);
		}
		Tcas t = new Tcas();
		t.Cur_Vertical_Sep = Integer.parseInt(args[0]);
		t.High_Confidence = Boolean.parseBoolean(args[1]);
		t.Two_of_Three_Reports_Valid = Boolean.parseBoolean(args[2]);
		t.Own_Tracked_Alt = Integer.parseInt(args[3]);
		t.Own_Tracked_Alt_Rate = Integer.parseInt(args[4]);
		t.Other_Tracked_Alt = Integer.parseInt(args[5]);
		t.Alt_Layer_Value = Integer.parseInt(args[6]);
		t.Up_Separation = Integer.parseInt(args[7]);
		t.Down_Separation = Integer.parseInt(args[8]);
		t.Other_RAC = Integer.parseInt(args[9]);
		t.Other_Capability = Integer.parseInt(args[10]);
		t.Climb_Inhibit = Boolean.parseBoolean(args[11]);

		int test = t.alt_sep_test();
		if (test == Constants.UNRESOLVED) {
			System.out.println(test + " (Unresolved).");
		} else if (test == Constants.UPWARD_RA) {
			System.out.println(test + " (Upward RA).");
		} else if (test == Constants.DOWNWARD_RA) {
			System.out.println(test + " (Downward RA).");
		} else {
			System.out.println("ERROR: unexpected answer.");
		}
		System.exit(0);
	}
}