package playground.div;

public class Divider {
    public static DivResult div(int dividend, int divisor) {
        if (divisor == 0) {
            return DivResult.failure(DivError.DIVISION_BY_ZERO);
        }
        if (dividend < 0 || divisor < 0) {
            return DivResult.failure(DivError.NEGATIVE_DIVISOR_OR_DIVIDEND);
        }
        if (dividend % divisor != 0) {
            return DivResult.failure(DivError.NOT_DIVISIBLE);
        }
        return DivResult.success(dividend / divisor);
    }
}
