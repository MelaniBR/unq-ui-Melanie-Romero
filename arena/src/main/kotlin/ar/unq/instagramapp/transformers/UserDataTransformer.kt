package ar.unq.instagramapp.transformers

import org.uqbar.arena.bindings.ValueTransformer


class UserDataTransformer(private val prefix: String = "") : ValueTransformer<String, String> {

    override fun getModelType() = String::class.java
    override fun getViewType() = String::class.java

    override fun modelToView(p0: String?): String {
        return prefix + p0;
    }

    override fun viewToModel(p0: String?): String {
        return "";
    }

}