## How to use

* `sbt package` to build a jar
* copy the jar to `lib` under your project
* update `build.sbt` with:
```scala
wartremoverClasspaths += "file://" + baseDirectory.value + "/../lib/cs320warts_2.13-1.0.0.jar",
wartremoverErrors += Wart.custom("cs320warts.MutableDataStructures")
```
