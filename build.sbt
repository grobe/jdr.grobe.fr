name := """jdr"""
organization := "fr.grobe"

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayJava)
EclipseKeys.preTasks := Seq(compile in Compile, compile in Test)

scalaVersion := "2.12.4"

libraryDependencies += guice
