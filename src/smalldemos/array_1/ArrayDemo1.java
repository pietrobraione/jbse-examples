package smalldemos.array_1;

import static jbse.meta.Analysis.any;

/** 
 * Demo code for array analysis, essentially focused on concrete 
 * arrays with concrete and symbolic length, and their access 
 * through concrete and symbolic indices.
 */ 
public class ArrayDemo1 {
	//arrays created during the demo
	public int[] a;
	public Object[] b;
	public int[][] c;
	public Object[][][] d;
	public int[] e;
	public int[][] f;
	public int[] g;
	public int[][][] h;

	//outcomes of read operations
	public int retA;
	public Object retB;
	public int retC;
	public Object retD;	
	public int retE_1;
	public RuntimeException retE_2;
	public int retF_1;
	RuntimeException retF_2;
	public int retG_1;
	public RuntimeException retG_2;
	public int retH_1;
	public RuntimeException retH_2;

	private int aM() {
		this.a = new int[3];
		this.a[2] = 55;
		int i = this.a[2];
		return i;
	}

	private Object bM() {
		this.b = new Object[1];
		this.b[0] = new Object();
		Object o = this.b[0];
		return o;
	}

	private int cM() {
		this.c = new int[1][2];
		this.c[0][1] = 42;
		int i = this.c[0][1];
		return i;
	}

	private Object dM() {
		this.d = new Object[1][2][3];
		this.d[0][1][2] = new Object();
		Object o = this.d[0][1][2];
		return o;
	}

	private int eM() {
		this.e = new int[1];
		int i = this.e[4];
		return i;
	}

	private int fM(int x) {
		this.f = new int[3][x];
		return this.f[0][2];
	}

	private int gM(int x) {
		this.g = new int[x];
		return this.g[x - 1];
	}
	
	private int hM(int x) {
		this.h = new int[2][x][3];
		this.h[1][3][1] = 2;
		return this.h[1][3][1];
	}

	public void entryPoint(int x) {
		//initialization
		a = null;
		b = null;
		c = null;
		d = null;
		e = null;
		f = null;
		g = null;
		h = null;
		retA = 0;
		retB = null;
		retC = 0;
		retD = null;
		retE_1 = -77;
		retE_2 = null;
		retF_1 = -1;
		retF_2 = null;
		retG_1 = -1;
		retG_2 = null;
		retH_1 = -1;
		retH_2 = null;

		retA = this.aM();
		retB = this.bM();
		retC = this.cM();
		retD = this.dM();

		try {
			retE_1 = this.eM();
		} catch (RuntimeException ex) {
			retE_2 = ex;
		}

		//we use nondeterminism to reduce the number of path combinations
		//and thus the number of leaf states
		if (any()) {
			try {
				retF_1 = this.fM(x);
			} catch (RuntimeException ex) {
				retF_2 = ex;
			}
		} else if (any()){
			try {
				retG_1 = this.gM(x);
			} catch (RuntimeException ex) {
				retG_2 = ex;
			}
		} else {
			try {
				retH_1 = this.hM(x);
			} catch (RuntimeException ex) {
				retH_2 = ex;
			}
		}
		
		//At the end of the symbolic execution in all the leaf states Object[0], the root object, has:
		//
		//  a == reference to Object[1] (which is {0, 0, 55}) 
		//  retA == 55
		//  b == reference to Object[2] (which is  {Object[3]}, where Object[3] has class Object) 
		//  retB == reference to Object[3] (with class Object)
		//  c == reference to Object[4] (which is {Object[5]}, where Object[5] is {0, 42})
		//  retC == 42
		//  d == reference to Object[6] (which is {Object[7]}, where Object[7] is {Object[8], Object[9]}, 
		//     Object[8] is {null, null, null} and Object[9] is {null, null, Object[10]}, and Object[10] 
		//     has class Object)
		//  retD == reference to Object[10] 
		//  e == reference to Object[11] (which is {0})
		//  retE_1 == -77
		//  retE_2 == reference to Object[12] (with class java.lang.ArrayIndexOutOfBoundsException)
		//
		//Moreover the leaf states should be 9 with the following features:
		//
		//1- the path condition is equivalent to
		//
		//      x < 0,
		//
		//   f == null, retF_1 == -1, retF_2 == Object[13], Object[13] has class java.lang.NegativeArraySizeException, 
		//   g/h, retG_1/H_1, retG_2/H_2 have their initial values (null, -1, null respectively)
		//
		//2- the path condition is equivalent to 
		//
		//      0 <= x < 3,
		//
		//   f == Object[13] fresh, Object[13] == {Object[14], Object[15], Object[16]}, 
		//   all {0 <= INDEX < x -> 0}, retF_1 == -1, retF_2 == reference to Object[17] 
		//   (with class java.lang.ArrayIndexOutOfBoundsException), , g/h, retG_1/H_1 
		//   and retG_2/H_2 have their initial values (null, -1, null respectively)
		//3- the path condition is equivalent to 
		//
		//       x >= 3, 
		//
		//   f == reference to Object[13] (which is 
		//   as in leaf state 3), retF_1 == 0, retF_2 == null, , g/h, retG_1/H_1 
		//   and retG_2/H_2 have their initial values (null, -1, null respectively)
		//4- the path condition is equivalent to
		//
		//       x < 0, 
		//
		//   g == null, retG_1 == -1, retG_2 == reference 
		//   to Object[13] (with class java.lang.NegativeArraySizeException), f/h, retF_1/H_1 
		//   and retF_2/H_2 have their initial values (null, -1, null respectively)
		//5- the path condition is equivalent to 
		//
		//      x == 0, 
		//
		//   g == reference to Object[13] (which is {0 <= INDEX < x -> 0}), 
		//   retG_1 == -1, retG_2 == reference to Object[14] (with class 
		//   java.lang.ArrayIndexOutOfBoundsException), f/h, retF_1/H_1 
		//   and retF_2/H_2 have their initial values (null, -1, null respectively)
		//6- the path condition is equivalent to 
		//
		//      x > 0, 
		//
		//   g == reference to Object[13] (which is 
		//   as in the previous case), retG_1 == 0, retG_2 == null, f/h, retF_1/H_1 
		//   and retF_2/H_2 have their initial values (null, -1, null respectively)
		//7- the path condition is equivalent to 
		//
		//      x < 0, 
		//
		//   h == null, retH_1 == -1, retH_2 == 
		//   reference to Object[13] (with class java.lang.NegativeArraySizeException), 
		//   f/g, retF_1/G_1 and retF_2/G_2 have their initial values (null, -1, null respectively)
		//8- the path condition is equivalent to 
		//
		//      0 <= x < 4, 
		//
		//   h == Object[13] (which is 
		//   {Object[14], Object[15]}, which in turn are both {0 <= INDEX < x -> {R<3>}}), 
		//   retH_1 == -1, retH_2 == reference to Object[] (with class 
		//   java.langArrayIndexOutOfBoundsException), f/g, retF_1/G_1 and retF_2/G_2 have 
		//   their initial values (null, -1, null respectively)
		//9- 
		//
		//      x >= 4 -> 
		//
		//   h == Object[13], retH_1 == 2, retH_2 == null
		//
		//Note that only 1-2-3, 4-5-6 and 7-8-9 are mutually exclusive, because
		//of the use of Any.
		//
		//By convenience, we report the invariant part of the heap:
		//
		//Object[1]: {
		//	Type: [I
		//	Length: 3
		//	Items: {0, 0, 55}
		//}
		//Object[2]: {
		//	Type: [Ljava/lang/Object;
		//	Length: 1
		//	Items: {Object[3]}
		//}
		//Object[3]: {
		//	Class: java/lang/Object
		//}
		//Object[4]: {
		//	Type: [[I
		//	Length: 1
		//	Items: {Object[5]}
		//}
		//Object[5]: {
		//	Type: [I
		//	Length: 2
		//	Items: {0, 42}
		//}
		//Object[6]: {
		//	Type: [[[Ljava/lang/Object;
		//	Length: 1
		//	Items: {Object[7]}
		//}
		//Object[7]: {
		//	Type: [[Ljava/lang/Object;
		//	Length: 2
		//	Items: {Object[8], Object[9]}
		//}
		//Object[8]: {
		//	Type: [Ljava/lang/Object;
		//	Length: 3
		//	Items: {null, null, null}
		//}
		//Object[9]: {
		//	Type: [Ljava/lang/Object;
		//	Length: 3
		//	Items: {null, null, Object[10]}
		//}
		//Object[10]: {
		//	Class: java/lang/Object
		//}
		//Object[11]: {
		//	Type: [I
		//	Length: 1
		//	Items: {0}
		//}
		//Object[12]: {
		//	Class: java/lang/ArrayIndexOutOfBoundsException
		//	Field[0]: Name: detailMessage, Type: Ljava/lang/String;, Value: null
		//	Field[1]: Name: backtrace, Type: Ljava/lang/Object;, Value: null
		//	Field[2]: Name: cause, Type: Ljava/lang/Throwable;, Value: null
		//	Field[3]: Name: stackTrace, Type: [Ljava/lang/StackTraceElement;, Value: null
		//}
	}
}
