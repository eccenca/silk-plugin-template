package com.eccenca

import com.eccenca.template.transformer.TemplateTransformer
import org.silkframework.runtime.plugin.PluginModule

/**
  * The plugin management class is a must and must contain a list of every plugin class so the compiler can be directed to the plugin classes introduced.
  */
class TemplatePluginModule extends PluginModule {

  override def pluginClasses: Seq[Class[_]] = Seq(
    classOf[TemplateTransformer]
  )

}
