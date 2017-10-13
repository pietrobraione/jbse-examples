package smalldemos.cast;

import static jbse.meta.Analysis.any;

class ClassSuper { }
class ClassSub extends ClassSuper { }

@SuppressWarnings("unused")
public class CastDemo {
    private ClassSub sub;
    private ClassSuper sup;
    private boolean r1;
    private boolean r2;
    private boolean r3;

    //throws ClassCastException
    private void m1() {
        sub = (ClassSub) (new Object());
    }

    private void m2() {
        //sup == Object[1], which is an instance of ClassSub
        Object x = new ClassSub();
        sup = (ClassSub) x;

        //sub == null
        x = null;
        sub = (ClassSub) x;

        //r1 == true
        this.r1 = ((new ClassSub()) instanceof ClassSuper);

        //r2 == false
        this.r2 = (null instanceof Object);

        //r3 == (sad but) true 
        x = new ClassSub[5];
        this.r3 = (x instanceof ClassSuper[]);
    }

    public void entryPoint() {
        if (any()) {
            m1();
        } else {
            m2();
        }
    }
}
