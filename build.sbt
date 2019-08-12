
import sbtbuildinfo.BuildInfoPlugin.autoImport.buildInfoKeys

val buildInfoSettings = Seq[BuildInfoKey](name, version, scalaVersion, sbtVersion, git.gitHeadCommit)

lazy val root = project.in(file("."))
  .enablePlugins(BuildInfoPlugin)
  .dependsOn(
    workbenchWorkspace % "compile->compile;test->test",
    pluginsXml % "test->test",
    workbenchWorkflow % "compile->compile;test->test",
    workbenchRules,
    plugins,
    core % "compile->compile;test->test",
    workspace % "test->test"
  )
  .aggregate(core, plugins, workbenchWorkspace, workbenchWorkflow, workbenchRules, plugins, pluginsXml, workspace)
  .settings(
    organization := "com.eccenca",
    name := "Silk Plugin Template",
    buildInfoKeys := buildInfoSettings,
    version := "1.0",
    scalaVersion := "2.11.11",
      // Testing
    libraryDependencies += "org.scalatest" %% "scalatest" % "3.0.7" % "test",
    libraryDependencies += "net.codingwell" %% "scala-guice" % "4.0.0" % "test",
    libraryDependencies += "ch.qos.logback" % "logback-classic" % "1.1.11",
    libraryDependencies += "org.mockito" % "mockito-all" % "1.9.5" % Test,
    libraryDependencies += "com.google.inject" % "guice" % "4.0" % "test",
    libraryDependencies += "javax.inject" % "javax.inject" % "1",
    // add here your dependencies

    dependencyOverrides ++= Set(
      "com.google.guava" % "guava" % "18.0",
      "com.google.inject" % "guice" % "4.0",
      "io.netty" % "netty" % "3.10.5.Final",
      "io.netty" % "netty-all" % "4.0.43.Final",
      "org.apache.thrift" % "libthrift" % "0.9.3",
      "com.fasterxml.jackson.core" % "jackson-databind" % "2.6.5",
      "commons-net" % "commons-net" % "3.1",
      "com.google.code.findbugs" % "jsr305" % "3.0.0",
      "javax.servlet" % "javax.servlet-api" % "3.1.0" // FIXME: Needs to be re-evaluated when changing the Fuseki version (currently 3.7.0), comes from jetty-servlets 9.4.7.v20170914
    )
  )

//////////////////////////////////////////////////////////////////////////////
// Silk Modules
//////////////////////////////////////////////////////////////////////////////

val silkUri = file("silk")
// Core modules
lazy val core = ProjectRef(silkUri, "core")
lazy val rules = ProjectRef(silkUri, "rules")
lazy val learning = ProjectRef(silkUri, "learning")
lazy val workspace = ProjectRef(silkUri, "workspace")

// Plugins
lazy val pluginsRdf = ProjectRef(silkUri, "pluginsRdf")
lazy val pluginsCsv = ProjectRef(silkUri, "pluginsCsv")
lazy val pluginsXml = ProjectRef(silkUri, "pluginsXml")
lazy val pluginsJson = ProjectRef(silkUri, "pluginsJson")
lazy val pluginsSpatialTemporal = ProjectRef(silkUri, "pluginsSpatialTemporal")
lazy val pluginsAsian = ProjectRef(silkUri, "pluginsAsian")
lazy val serializationJson = ProjectRef(silkUri, "serializationJson")
lazy val plugins = ProjectRef(silkUri, "plugins")

// Workbench
lazy val reactComponents = ProjectRef(silkUri, "reactComponents")
lazy val workbenchCore = ProjectRef(silkUri, "workbenchCore")
lazy val workbenchWorkspace = ProjectRef(silkUri, "workbenchWorkspace")
lazy val workbenchRules = ProjectRef(silkUri, "workbenchRules")
lazy val workbenchWorkflow = ProjectRef(silkUri, "workbenchWorkflow")
