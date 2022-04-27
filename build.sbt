scalaVersion := "2.13.8"

version := "1.0.0"

scalacOptions += "-feature"
scalacOptions += "-deprecation"
scalacOptions += "-Xlint:unused"

libraryDependencies += "org.wartremover" %% "wartremover" % "3.0.2"
libraryDependencies += "org.scalatest" %% "scalatest" % "3.2.11" % "test"
