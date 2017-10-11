# Changelog of "simyukkuri" Module

All notable changes to this project will be documented in this file.

The format is based on [Keep a Changelog](http://keepachangelog.com/en/1.0.0/)
and this project adheres to [Semantic Versioning](http://semver.org/spec/v2.0.0.html).


**Read this in other languages:**
- [日本語 (Japanese)](CHANGELOG.ja.md)
- English : *this file*


## [Unreleased]

### Added
- Added KDoc for all classes
- Created class `Ai`
- Renamed to `YukkuriStat` from interface `Yukkuri`, then created new interface `Yukkuri` having `YukkuriStat` and `Ai`
- Created class `IndividualEvent` and `Action`, partialy implemented `actions`
- Added resource `messages` to main resources
- Implemented `Message` and related portions
- Added class `MutableVectorXZ`
- Almost reimplemented classes in `part` directory except `Terrarium` and `Message`
- Added package `gameobject.yukkuri.factory`

### Changed
- Renamed `Field` to `Scene` for merging "field" function in future
- Changed to specify `Scene` size in the constructor. Untill now, initialized as `0.0` meaninglessly
- Changed to specify Yukkuri's prameter constnats, `Scene` size, and `scene` of `GameLoop` in constructor
- Decomposited `checkPartner` in `Terrarium`
- Decomposited main part of `Body` into classes of the `parts` package
- Changed GUI to JavaFX from Swing, implmented outline of GUI
- Changed language to Kotlin from Java
- Forked from original SimYukkuri at OSDN

### Removed
- Removed class `Furifuri` and `FurifuriImpl`
- Removed class `Vector3` and `Vector2`

### Fixed
- Fix typo at `geometry`

<!--
### Added
for new features.
### Changed
for changes in existing functionality.
### Deprecated
for soon-to-be removed features.
### Removed
for now removed features.
### Fixed
for any bug fixes.
### Security
in case of vulnerabilities.
-->