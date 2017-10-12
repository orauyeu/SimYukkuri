プロジェクトに参加するには
====

**Read this in other languages:**
- 日本語 (Japanese) : *this file*
- [English (英語)](CONTRIBUTING.md)

ディレクトリ構成
---

各サブディレクトリはサブプロジェクトを含んでいます。

* __simyukkuri__  : しむゆっくり本体の最新のソース
* __messageutil__ : OSDNやしたらば形式のセリフを現行形式のセリフに変換するモジュール

各プロジェクトにあるmemo.txtは開発者向けのメモ.

開発の流れ
---

「[開発の流れについての簡単な説明](docs/ja/development_flow.md)」を参照して下さい。

課題 (issue) の報告
---

課題 (issue) を報告する際には、

1. 日本語もしくは明確に理解できる英語で書くこと

(TBD) Issueの書き方など

参考文献
---

* Javaを学習するのに利用したサイト
	* https://www.javadrive.jp/start/
* Javaを学習するのに読んだ書籍
	* スッキリわかるJava入門 第2版
* Kotlinを学習するのに利用したサイト
	* http://qiita.com/koher/items/bcc58c01c6ff2ece658f
	* https://dogwood008.github.io/kotlin-web-site-ja/docs/reference/
* JavaFXを学習するのに利用したサイト
	* http://libro.tuyano.com/index2?id=8356003
	* https://gamedevelopment.tutsplus.com/ja/tutorials/introduction-to-javafx-for-game-development--cms-23835
* Gradleを利用する際参考にしたサイト
	* http://qiita.com/WK6_8B/items/77f3b49fc0b7d4c4ff27

開発Tips
--------

### 英語
* 英語を調べる際は「ポケモン "調べたい単語" 英語」で検索するのが便利

### シムゆっくり内で使われているKotlin特有の機能で名前を知らないと検索しにくいもの
- クラスデリゲーション（例）Damage by damage
- ラムダ（例）scene.yukkuriNearestTo(self) { self.isFather(it) }
- ヌル安全性（例）変数の末尾につく"?"や"!!"
- エルヴィス演算子（例）?: return

### IntelliJ IDEAを使うときに詰まったこと

* ソースがほとんどハイライトされないときはソースディレクトリを右クリックして`Mark Directory as`で`Sources Root`に指定する
* するとソースファイルを開いたときにJDKやKotlinの設定を尋ねられて, そこでいい感じに設定するとハイライトされるようになる.
* それでもダメなら `File/Project Structure/Project SDK`からJDKを設定する.

### IntelliJ IDEAでCtrl+Qを入力したときなどに表示されるdocとして日本語ファイルを指定する方法
* https://stackoverflow.com/questions/8587522/how-to-view-jdk-external-documentation-in-intellij-idea#comment52747938_8589892
* 上記のURLの手順に従って, "https://docs.oracle.com/javase/jp/8/docs/api/", "https://docs.oracle.com/javase/jp/8/javafx/api/" を指定する.

### マークダウンを表示する方法
* https://qiita.com/SUZUKI_Masaya/items/6476dbbcb3e369640c78
* chromeの場合はデフォルトテーマをYetAnotherGithubにするのがきれいなのでおすすめ.

### しむゆっくり開発の上でのIntelliJ IDEAのおすすめプラグイン
- Git
- GitHub
- Markdown Navigator

### 注意
* GradleのJVMにJava9を設定するとdokkaがうまく動かない（プロジェクトのSDKはJava9でも問題ない）