package ar.unq.instagramapp.transformers

import org.uqbar.arena.bindings.ValueTransformer

class BooleanTransformer(private val default : Boolean) : ValueTransformer<Boolean, Boolean> {

    override fun getModelType() = Boolean::class.java
    override fun getViewType() = Boolean::class.java

    override fun modelToView(p0: Boolean?): Boolean {
        return if(p0 == null){
            default
        }else{
            !p0
        }
    }

    override fun viewToModel(p0: Boolean?): Boolean {
        return if(p0 == null){
            default
        }else{
            !p0
        }
    }

}