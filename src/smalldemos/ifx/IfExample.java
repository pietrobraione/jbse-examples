package smalldemos.ifx;

public class IfExample {
    boolean a, b;
    public void m(int x) {
        if (x > 0) {
            a = true;
        } else {
            a = false;
        }
        //jbse.meta.Analysis.endGuidance();
        if (x > 0) {
            b = true;
        } else {
            b = false;
        }
        jbse.meta.Analysis.ass3rt(a == b);
        //two leaves, one with {ROOT}:x > 0 and both a and b true,
        //and one with {ROOT}:x <= 0 and both a and b false
    }
}
