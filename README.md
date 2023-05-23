# Javaでsealedを素振りするリポジトリ
- Java 15から導入された`sealed`の素振り用のリポジトリ
  - Java 16から導入された`instanceof`の素振りもする
    ```java
    // もともと
    if (person instanceof Customer) {
        Customer customer = (Customer) person;
        customer.pay();
    }
    // Java 16以後
    if (person instanceof Customer customer) {
        customer.pay();
    }
    ```

## メモ
`sealed`を使うと、直和型を安全に書ける。
何かの結果を保持するクラスで、エラーと成功時の2パターンがある時を想定する。素直にクラスで書くと、①フィールドに成功したかどうかのフラグと②成功時の値と③失敗した時の値の3つを持つ必要がある。 
しかし、この場合は使う側が①の値をチェックして②と③のどちらかを使うか判断する必要があり、不正なアクセスができてしまう。また、片方の値しか入らないことが表現できていない。
この対応として、共通の interface を作って1つの型として扱えるようにできるが、`sealde`登場以前はどのクラスでもinterfaceを継承できてしまった。
`sealed`を使うとpermitsで許可していないクラス以外は実装できないようにできる。
Java 16 以降で使える instanceof のパターンマッチング機能を使って、各クラスごとに処理が書ける。Java 20で使えるようになるパターンマッチを使って、switch文で型ごとの分岐を書くことができる。

## 参考
- [Java 注目の機能：Sealed クラス](https://www.infoq.com/jp/articles/java-sealed-classes/)
- [代数的データ型をJavaで安全に使いこなす](https://style.biglobe.co.jp/entry/2022/06/29/090000)
- [Java 17の新機能でドメインモデリングの表現力を高めてみる](https://style.biglobe.co.jp/entry/2022/01/26/110000)
- [“Designing with types” に学ぶ Kotlin における型を利用したモデリング術](https://speakerdeck.com/omuomugin/designing_with_types_in_kotlin)
