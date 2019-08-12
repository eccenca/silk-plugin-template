# Silk Plugin Template
A template for [silk](https://github.com/silk-framework/silk) plugins containing a transformer class plugin example.
These plugins are either usable by silk directly (pls. follow the instructions for silk usage) or by eccenca Dataintegration.

# Building the plugin

The libraries of DataIntegration are needed to build the plugin (also during developing the plugin). Checkout the targeted version of DataIntegratio or Silk and run `./sbt publishLocal`
Execute `sbt package`. 

# Installing the plugin

The resulting jar in `target/scala-2.11/` needs to be put into the configured plugin directory.

Silk: use the sbt parameter --sbt-dir to point to the plugin directory

Dataintegration: The plugin directory is configured in the DataIntegration configuration with the property `pluginRegistry.pluginFolder = ${elds.home}"/etc/dataintegration/plugins/"
`

# Important Notes

- __A plugin is always build against a certain version of Dataintegration / Silk__
  - Rebuilding the pluging Jar is necessary if dependencies of Dataintegration change, as i might reference different library versions (dependency changes can happen in patch level verion increases)
- __Depending on what the plugin does, code chages are needed if internal APIs of Dataintegration change__
  - If the plugin defines a task and implements a task executor, it might be the case that changes are needed, except when high level APIs (e.g. the [???]Task interfaces, [???]Entity result type changes) - it is rare, though
  - If the plugin uses other plugins or a large number of existing operators/datasets, changes between 2 release are more likely

# Upgrade Notes

## General considerationswhen the version of Dataintegration or Silk changes

- Always rebuild the plugin, even when only the patch version is increased:

```
cd dataintegration-repo
git checkout vX.X.X
./sbt publishLocal
# sometimes a clean is needed
# rm -rf ~/.ivy2/* makes sure that no old jars remain

cd pluginRepo
./sbt package
```

- Check the log output of the whole process
  - Deprecation warnings can be ignored
  - Eviction wanings should be taken serious - if they name a dependency that is used directly by the plugin, they should be adressed
  - Errors have to be addressed in some way
  - Warning diretly related to code in the plugin, especially when not thrown in the last version, should also be adressed

- Plugins should be versioned: Since they might be branches of this repository the version should be  ```[plugin id]-v[dataintegration version]-[plugin version]```

-Is the plugin an operator, dataset or rule implemetation? __Make it serializable__ (with Serializable trait, lazy transient vals, etc. ask Christian Wartner)

- Always check if for configuration values or their default values, esp. when the plugin uses them directly
