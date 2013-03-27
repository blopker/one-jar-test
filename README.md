One Jar Test
============

How to compile a Play! Framework app into one Jar archive.

Requirements
---
* [Play! Framework 2.1](http://www.playframework.com/)
* [sbt-onejar](https://github.com/sbt/sbt-onejar)

Getting Started
---
1. First add the one-jar plugin to your plugins.sbt file:
  ```scala
  resolvers += Resolver.url(
    "sbt-plugin-releases",
    new URL("http://scalasbt.artifactoryonline.com/scalasbt/sbt-plugin-releases/")
  )(Resolver.ivyStylePatterns)

  addSbtPlugin("com.github.retronym" % "sbt-onejar" % "0.8")

  libraryDependencies += "commons-lang" % "commons-lang" % "2.6"
  ```
  Just add a blank line after at the end of the file then paste the new resolver, add plugin directive and the commons-lang dependency.

2. Next activate the plugin in your Build.scala folder:
  ```scala
  import com.github.retronym.SbtOneJar // Import one-jar
  import sbt._
  import Keys._
  import play.Project._

  object ApplicationBuild extends Build {

    val appName         = "one-jar-test"
    val appVersion      = "1.0-SNAPSHOT"

    val appDependencies = Seq(
      javaCore,
      javaJdbc
    )

    // Make standard settings and add them to Play
    def standardSettings = Seq(
      exportJars := true
    ) ++ Defaults.defaultSettings

    val main = play.Project(appName, appVersion, appDependencies, settings = standardSettings ++ SbtOneJar.oneJarSettings).settings(

    )
  }
  ```
3. Disable ehcache in your application.conf folder:
  ```
  # Disable cahche plugin to work around one-jar error.
  ehcacheplugin=disabled
  ```
4. Run `play one-jar`. This should compile your app to a jar at ./target/scala-2.10/$PROJECTNAME_2.10-1.0-SNAPSHOT-one-jar.jar

5. Start your app with `java -jar $PROJECTNAME_2.10-1.0-SNAPSHOT-one-jar.jar`

Caveats
---
I couldn't get one-jar to work with either ehcache or using ebeans. Ehcache isn't a big deal, but no ebeans will make any sort of database painful. Let me know if you find a work around!
