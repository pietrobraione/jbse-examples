package smalldemos.inheritance;

import static jbse.meta.Analysis.ass3rt;

class A {
    public int baz = 1;
    int foo() { return baz; }
}

class B extends A {
    public int baz = 2;
    @Override
    int foo() { return baz + super.foo(); }
}

class C extends B {
    public int baz = 3;
    @Override
    int foo() { return baz + super.foo(); }
}

public class InheritanceDemo {
    public void entryPoint() {
        C c = new C();
        ass3rt(c.foo() == 6);
    }
    //result: one safe trace
}
