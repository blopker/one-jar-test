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
