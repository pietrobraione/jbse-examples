package smalldemos.lambda_1;

import java.util.function.IntUnaryOperator;

public class LambdaDemo {
    static IntUnaryOperator f(int x) {
        return (int a) -> { return a + x; };
    }
    
    public static void main(String[] s) {
        System.out.println(LambdaDemo.f(3).applyAsInt(10));
    }
}
