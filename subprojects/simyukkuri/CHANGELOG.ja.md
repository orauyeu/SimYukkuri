# 「simyukkuri」モジュールの変更履歴

このファイルは、このプロジェクトに関する重要な変更を全て記録したものです。

ファイル形式は [Keep a Changelog](http://keepachangelog.com/en/1.0.0/) に基いています。
またプロジェクトは [Semantic Versioning](http://semver.org/lang/ja/spec/v2.0.0.html) に準拠しています。


**Read this in other languages:**
- 日本語 (Japanese) : *this file*
- [English (英語)](CHANGELOG.md)


## [Unreleased]

### 追加
- KDocを全てのクラスにつけた
- `Ai`クラス作成
- `Yukkuri`インターフェースを`YukkuriStat`に改名し, 新たに`YukkuriStat`と`Ai`を持つ`Yukkuri`インターフェースを作った
- `IndividualEvent`クラス, `Action`クラスを作成. `actions`の中身を一部を実装
- mainリソースに`messages`リソースを追加
- `Message`とそれにまつわる部分を実装
- クラス`MutableVectorXZ`を追加
- `parts`内のクラスを`Terrarium`と`Message`以外だいたい実装
- `gameobject.yukkuri.factory`パッケージを作成

### 変更
- 将来的に畑機能をマージするために`Field`を`Scene`に改名
- `Scene`内の大きさを無意味に`0.0`で初期化してからセットするようにしていたが, コンストラクタで指定するようにした
- ゆっくりのパラメータの定数や`Scene`の大きさや`GameLoop`の`scene`をコンストラクタで指定するようにした
- `Terrarium`の`checkPartner`を分解
- `Body`の主要な部分を`parts`パッケージ内のクラスに分解
- GUIをSwingからJavaFXに移行し、GUIの大枠を実装
- プログラミング言語をKotlinに移行
- OSDNシムゆっくりから分岐

### 削除
- `Furifuri`および`FurifuriImpl`を削除
- クラス`Vector3`と`Vector2`を削除

### 修正
- `geometry`の間違った英語を訂正

<!--
### 追加
新機能の追加
### 変更
既存の機能の変更
### 廃止
近日中に削除される予定の機能
### 削除
削除された機能
### 修正
意図しない動作の修正
### セキュリティ
脆弱性の修正
-->