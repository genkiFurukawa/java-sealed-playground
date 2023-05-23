package playground.div;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DividerTest {
    @Nested
    class divメソッドのテスト {
        @Test
        void _10を5で割った時に2の値をもつDivResultのインスタンスが返されること() {
            DivResult result = Divider.div(10, 5);
            assertEquals(DivResult.success(2), result);
        }

        @Test
        void 分子が負の時に時にNOT_DIVISIBLEの値をもつDivResultのインスタンスが返されること() {
            DivResult result = Divider.div(-10, 3);
            assertEquals(DivResult.failure(DivError.NEGATIVE_DIVISOR_OR_DIVIDEND), result);
        }

        @Test
        void 分母が負の時に時にNOT_DIVISIBLEの値をもつDivResultのインスタンスが返されること() {
            DivResult result = Divider.div(10, -5);
            assertEquals(DivResult.failure(DivError.NEGATIVE_DIVISOR_OR_DIVIDEND), result);
        }

        @Test
        void 割り切れない除算をしようとした時にNOT_DIVISIBLEの値をもつDivResultのインスタンスが返されること() {
            DivResult result = Divider.div(10, 3);
            assertEquals(DivResult.failure(DivError.NOT_DIVISIBLE), result);
        }

        @Test
        void _0で除算しようとした時にDIVISION_BY_ZEROの値をもつDivResultのインスタンスが返されること() {
            DivResult result = Divider.div(10, 0);
            assertEquals(DivResult.failure(DivError.DIVISION_BY_ZERO), result);
        }
    }

    @Nested
    class mapEitherメソッドのテスト {
        String testDiv(int dividend, int divisor) {
            DivResult result = Divider.div(dividend, divisor);

            return result.mapEither(
                    success -> "答え: %d".formatted(success.quotient()),
                    failure -> switch (failure.divError()){
                        case NEGATIVE_DIVISOR_OR_DIVIDEND -> "除数または被除数が負";
                        case NOT_DIVISIBLE -> "割り切れない";
                        case DIVISION_BY_ZERO -> "0による除算";
                    }
            );
        }

        @Test
        void mapEitherのテスト() {
            assertEquals("答え: 2", testDiv(2, 1));
            assertEquals("除数または被除数が負", testDiv(-2, 1));
            assertEquals("除数または被除数が負", testDiv(2, -1));
            assertEquals("割り切れない", testDiv(2, 3));
            assertEquals("0による除算", testDiv(2, 0));
        }
    }
}