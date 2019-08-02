package com.eccenca.template.transformer

import org.silkframework.rule.input.Transformer
import org.silkframework.runtime.plugin.Plugin

@Plugin(
  id = "template-transformer",          // the (internal) id (see [[Identifier]] for syntax restrictions
  categories = Array("TemplatePlugin"), // optional array of transformer categories this transformer should be appended to
  label = "Template Transformer",       // the human readable label
  description = "some description... ") // a description
class TemplateTransformer extends Transformer {
  /**
    * Transforms a sequence of values
    *
    * @param values A sequence which contains as many elements as there are input operators for this transformation.
    *               For each input operator it contains a sequence of values.
    * @return The transformed sequence of values.
    */
  override def apply(values: Seq[Seq[String]]): Seq[String] = {
    // make sure your input are all there and have the expected format
    assert(values.size == 2, "There have to be exactly two inputs for this transformer")
    assert(values.forall(v => v.headOption.forall(_.startsWith("{"))), "The expected input format was not found ...")

    // do some transformation
    values.map{ valSeq =>
      val unWrappedJson = valSeq.map(value => value.replaceAll( "(\\{|\\})", "")).mkString(",")
      "{" + unWrappedJson + "}"
    }
    // note: this is scala, no return statement necessary, in this case the result of the last map() is returned
  }
}
