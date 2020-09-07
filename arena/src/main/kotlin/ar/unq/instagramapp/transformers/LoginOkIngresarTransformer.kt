package ar.unq.instagramapp.transformers

import org.uqbar.arena.bindings.ValueTransformer

class LoginOkIngresarTransformer : ValueTransformer<Boolean, Boolean> {

    override fun getModelType() = Boolean::class.java
    override fun getViewType() = Boolean::class.java

    override fun modelToView(p0: Boolean?): Boolean {
        return p0 == null || !p0;
    }

    override fun viewToModel(p0: Boolean?): Boolean {
        return false;
    }

}