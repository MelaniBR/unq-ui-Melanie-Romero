package ar.unq.instagramapp.transformers

import org.uqbar.arena.bindings.ValueTransformer
import java.awt.Color


class ErrorBackgroundLoginTransformer : ValueTransformer<Boolean, Color> {

    override fun getModelType() = Boolean::class.java
    override fun getViewType() = Color::class.java

    override fun modelToView(p0: Boolean?): Color {
        return if ( p0 == null || p0.equals(Color.GREEN)) Color.GREEN else Color.RED;
    }

    override fun viewToModel(p0: Color?): Boolean {
        return p0 != null && p0 == Color.GREEN;
    }

}