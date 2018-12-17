lazy val `spotify-gdpr` = (project in file("."))
  .settings(
    organization := "com.ikempf",
    name := "spotify-gdpr",
    scalaVersion := "2.12.7",
    scalacOptions += "-Ypartial-unification",
    libraryDependencies ++= List(
      "org.typelevel"         %% "cats-core" % "1.4.0",
      "com.softwaremill.sttp" %% "core"      % "1.5.1",
      "org.scalatest"         %% "scalatest" % "3.0.5" % Test
    ),
    addCompilerPlugin("org.spire-math" %% "kind-projector" % "0.9.8"),
    scalacOptions ++= List(
      "-target:jvm-1.8",
      "-feature",
      "-encoding",
      "UTF-8",
      "-unchecked",
      "-deprecation",
      "-language:higherKinds",
      "-Xlint",
      "-Ypartial-unification",
      "-Ywarn-dead-code",
      "-Ywarn-infer-any",
      "-Ywarn-nullary-override",
      "-Ywarn-unused:implicits",
      "-Ywarn-unused:imports",
      "-Ywarn-unused:locals",
      "-Ywarn-unused:params",
      "-Ywarn-unused:patvars",
      "-Ywarn-unused:privates",
      "-Ywarn-value-discard"
    ),
  )
