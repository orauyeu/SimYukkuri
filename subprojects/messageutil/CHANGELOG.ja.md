# 「messageutil」モジュールの変更履歴

このファイルは、このプロジェクトに関する重要な変更を全て記録したものです。

ファイル形式は [Keep a Changelog](http://keepachangelog.com/en/1.0.0/) に基いています。
またプロジェクトは [Semantic Versioning](http://semver.org/lang/ja/spec/v2.0.0.html) に準拠しています。


**Read this in other languages:**
- 日本語 (Japanese) : *this file*
- [English (英語)](CHANGELOG.md)


## [Unreleased]

### 追加
- `Statistics`クラスを作成した.
- `Commented`クラスを作成した.
- snakeyamlの設定をまとめた`YamlSetting.kt`を作成した.
- OSDN版のセリフファイルを新形式に変換する関数を作成した.
- セリフから`Messages`クラスのソースを大雑把に作成する関数を作成した.
- したらば版のセリフデータを新形式のデータに変換する関数を作成した.
- コメント付きセリフデータをYAML文字列に変換する関数を作成した.
- セリフ名を変更する関数を作成した.

### 変更
- OSDN版のセリフファイルをYAMLに変換
- OSDN版のセリフファイルのRudeHangryをRudeHungryに修正.
- OSDN版のセリフファイルのSadnessForYungerSisterをSadnessForYoungerSisterに修正.

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