package symbols.basic;

import java.util.LinkedList;

public class Symbols {
    public String m(String s, double b1) {
        if (b1 >= 0.0) {
            return "foo";
        }
        if (s.equals("baz")) {
            return "baz";
        }
        return null;
    }

    public String m(LinkedList<String> l, double b1) {
        if (b1 >= 0.0) {
            return "foo";
        }
        if (l.toString().equals("baz")) {
            return "baz";
        }
        return null;
    }
}