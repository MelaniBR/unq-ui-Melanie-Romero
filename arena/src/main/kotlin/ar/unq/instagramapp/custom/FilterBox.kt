package ar.unq.instagramapp.custom

import org.apache.commons.collections15.Transformer
import org.uqbar.arena.widgets.Control
import org.uqbar.arena.widgets.Panel
import org.uqbar.arena.widgets.TextBox
import org.uqbar.lacar.ui.model.ControlBuilder
import org.uqbar.lacar.ui.model.bindings.Binding

class FilterBox(container: Panel, val model: ModelWithSearch) : TextBox(container) {
    override fun <M, C: ControlBuilder>
    bindValueToProperty(propertyName: String): Binding<M, Control, C> {
        val binding = super.bindValueToProperty<M, C>(propertyName)
        binding.setViewToModel(FilterTransformer(model))
        return binding
    }
}

class FilterTransformer<B>(val model:ModelWithSearch) : Transformer<B, B>
{
    override fun transform(p0: B): B {
        model.search(p0!!.toString())
        return p0!!
    }
}

interface ModelWithSearch {
    fun search(t:String)
}