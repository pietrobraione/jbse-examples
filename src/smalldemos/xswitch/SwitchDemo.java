package smalldemos.xswitch;

import static jbse.meta.Analysis.ass3rt;

public class SwitchDemo {
	int entryPoint(int i) {
		//i = 5000; //enable to force a concrete execution
		int retVal;

		//this should force compilation to a lookup switch
		switch (i) {
		case 1:
			retVal = 0;
			break;
		case 5000:
			retVal = 100;
			break;
		default:
			retVal = 200;
		}
		
		//this should force compilation to a table switch
		switch (i) {
		case 1:
			retVal += 1;
			break;
		case 2:
			retVal += 2;
			break;
		case 3:
			retVal += 3;
			break;
		case 4:
			retVal += 4;
			break;
		case 5:
			retVal += 5;
			break;
		case 6:
			retVal += 6;
			break;
		case 7:
			retVal += 7;
			break;
		case 8:
			retVal += 8;
			break;
		case 9:
			retVal += 9;
			break;
		case 10:
			retVal += 10;
			break;
		case 11:
			retVal += 11;
			break;
		case 12:
			retVal += 12;
			break;
		case 13:
			retVal += 13;
			break;
		case 14:
			retVal += 14;
			break;
		case 15:
			retVal += 15;
			break;
		default:
			retVal += 99;
		}
		
		ass3rt(	retVal == 1 || retVal == 199 || retVal == 202 || retVal == 203 || retVal == 204 || 
				retVal == 205 || retVal == 206 || retVal == 207 || retVal == 208 || retVal == 209 || 
				retVal == 210 || retVal == 211 || retVal == 212 || retVal == 213 || retVal == 214 || 
				retVal == 215 || retVal == 299); 
		return retVal;
		//17 traces, all safe; It should return all the 17 values 1, 199, 202, 203, 204, 
		//205, 206, 207, 208, 209, 210, 211, 212, 213, 214, 215, 299 (in this order
		//in the current implementation). 
	}
}