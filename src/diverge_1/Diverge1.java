package diverge_1;

public class Diverge1 {
    void m(int x) {
        //x = 3; //enable this statement to force concrete execution
        while (x > 0) {
            x--;
        }
    }
}
