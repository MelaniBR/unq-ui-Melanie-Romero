package ar.unq.instagramapp.custom

import org.uqbar.arena.filters.TextFilter
import org.uqbar.arena.widgets.Control
import org.uqbar.arena.widgets.Panel
import org.uqbar.arena.widgets.TextBox
import org.uqbar.arena.widgets.TextInputEvent
import org.uqbar.lacar.ui.model.ControlBuilder
import org.uqbar.lacar.ui.model.bindings.Binding

class FilterBox(container: Panel, val model: ModelWithSearch) : TextBox(container) {
    override fun <M, C: ControlBuilder>
    bindValueToProperty(propertyName: String): Binding<M, Control, C> {
        withFilter(FilterBoxFilter(model))
        return super.bindValueToProperty<M, C>(propertyName)
    }
}

class FilterBoxFilter(val model:ModelWithSearch) : TextFilter {
    override fun accept(event: TextInputEvent): Boolean {
        model.search(event.potentialTextResult)
        return true;
    }
}

interface ModelWithSearch {
    fun search(t:String)
}