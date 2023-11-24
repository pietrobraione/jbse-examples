package symbols.string;

public class StringDemo {

    public static class C1 {
        private final int n;
        private final char c;
        
        public C1(int n, char c) {
            this.n = n;
            this.c = c;
        }
        
        @Override
        public String toString() {
            String s = "";
            for (int i = 0; i < n; i++) {
                s += c;
            }
            return s;
        }
    }

    public static class C2 {
        private final String s;
        
        public C2(String s, String c) {
            this.s = c + s;
        }
        
        @Override
        public String toString() {
            return s;
        }
    }

    /** 
     * Four leaves: 
     * 1- s1 != null, s2 != null, s1.equals(s2): return 1
     * 2- s1 != null, s2 != null, !s1.equals(s2): return 0
     * 3- s1 != null, s2 == null: NPE
     * 4- s1 == null: NPE
     */
    public int entryPoint(C1 o1, C2 o2) {
        final String s1 = o1.toString();
        final String s2 = o2.toString();
        if (s1.equals(s2)) {
            return 1;
        } else {
            return 0;
        }
    }
    
    /**
     * Follows path 1, returns 1.
     */
    public void guidanceStart() {
        final C1 o1 = new C1(3, 'a');
        final C2 o2 = new C2("aa", "a");
        entryPoint(o1, o2);
    }
    
}