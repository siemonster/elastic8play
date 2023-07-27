import sbt.url

version := "1.14.0"

name := "elastic8play"

organization := "org.siemonster"

organizationName := "SIEMonster"

organizationHomepage := Some(url("https://siemonster.com/"))

homepage := Some(url("https://github.com/TheHive-Project/elastic4play"))

licenses += "AGPL-V3" -> url("https://www.gnu.org/licenses/agpl-3.0.html")

description := "Library to use Elasticsearch with Play framework"

lazy val elastic4play = (project in file("."))
  .enablePlugins(PlayScala, PlayAkkaHttp2Support)
// Add Http2 support to be able to ask client certificate
// cf. https://github.com/playframework/playframework/issues/8143

scalaVersion := "2.12.17"

resolvers += "elasticsearch-releases" at "https://artifacts.elastic.co/maven"

scmInfo := Some(
  ScmInfo(
    url("https://github.com/TheHive-Project/sbt-github-changelog"),
    "scm:git@github.com:TheHive-Project/sbt-github-changelog.git"
  )
)

developers := List(
  Developer(
    id = "siemonster",
    name = "Ikuturso",
    email = "jim@siemonster.com",
    url = url("https://github.com/siemonster")
  )
)

licenses += ("AGPL-3", url("https://www.gnu.org/licenses/agpl-3.0.html"))

val elastic4sVersion = "8.8.1"
libraryDependencies ++= Seq(
  cacheApi,
  "com.sksamuel.elastic4s" %% "elastic4s-core"          % elastic4sVersion,
  "com.sksamuel.elastic4s" %% "elastic4s-http-streams"  % elastic4sVersion,
  "com.sksamuel.elastic4s" %% "elastic4s-client-esjava" % elastic4sVersion,
  "com.typesafe.akka"      %% "akka-stream-testkit"     % play.core.PlayVersion.akkaVersion % Test,
  "org.scalactic"          %% "scalactic"               % "3.2.12",
  "org.bouncycastle"        % "bcprov-jdk15on"          % "1.61",
  specs2                    % Test
)
dependencyOverrides ++= Seq(
  "com.typesafe.akka" %% "akka-actor" % play.core.PlayVersion.akkaVersion
)

PlayKeys.externalizeResources := false

pomIncludeRepository := { _ => false }

publishTo := sonatypePublishToBundle.value

pgpSigningKey := Some("06336BB38E57B56BF6E9A72EDA056AEAEFA4FB1D")

publishMavenStyle := true

scalacOptions in ThisBuild ++= Seq(
  "-encoding",
  "UTF-8",
  "-deprecation",         // warning and location for usages of deprecated APIs
  "-feature",             // warning and location for usages of features that should be imported explicitly
  "-unchecked",           // additional warnings where generated code depends on assumptions
  "-Xlint",               // recommended additional warnings
  "-Ywarn-adapted-args",  // Warn if an argument list is modified to match the receiver
  "-Ywarn-value-discard", // Warn when non-Unit expression results are unused
  "-Ywarn-inaccessible",
  "-Ywarn-dead-code"
)
