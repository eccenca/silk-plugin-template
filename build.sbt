import sbt.Keys.libraryDependencies

lazy val root = project.in(file("."))
  .settings(
    organization := "com.eccenca",
    name := "Silk Plugin Template",
    version := "1.0",
    scalaVersion := "2.11.11",
    libraryDependencies += "org.scalatest" %% "scalatest" % "2.2.6" % "test"
  )

//////////////////////////////////////////////////////////////////////////////
// Silk Modules
//////////////////////////////////////////////////////////////////////////////

val silkUri = uri("https://github.com/silk-framework/silk.git")
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
