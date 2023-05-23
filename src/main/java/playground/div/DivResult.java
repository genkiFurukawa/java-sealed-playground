package playground.div;

import java.util.function.Function;

// NOTE: https://style.biglobe.co.jp/entry/2022/06/29/090000 の写経
// NOTE: sealedを付けて、2つ以外の実装を許可しないようにする。
sealed public interface DivResult permits DivResult.Success, DivResult.Failure {
    <T, R> R mapEither(
            Function<Success, ? extends R> successMapper, // 状態が成功のときに呼ばれる関数
            Function<Failure, ? extends R> failureMapper  // 状態が失敗のときに呼ばれる関数
    );

    static DivResult success(int quotient) {
        return new Success(quotient);
    }
    static DivResult failure(DivError divError) {
        return new Failure(divError);
    }
    // NOTE: 内部クラスなので名前を短くする
    record Success(int quotient) implements DivResult {
        @Override
        public <T, R> R mapEither(
                Function<Success, ? extends R> successMapper,
                Function<Failure, ? extends R> failureMapper) {
            return successMapper.apply(this);
        }
    }

    record Failure(DivError divError) implements DivResult {
        @Override
        public <T, R> R mapEither(
                Function<Success, ? extends R> successMapper,
                Function<Failure, ? extends R> failureMapper) {
            return failureMapper.apply(this);
        }
    }
}
