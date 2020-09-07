package ar.unq.instagramapp.viewModels

import ar.unq.instagramapp.models.RegisterModel
import ar.unq.instagramapp.transformers.ErrorBackgroundLoginTransformer
import org.uqbar.arena.kotlin.extensions.bindBackgroundTo
import org.uqbar.arena.kotlin.extensions.bindTo
import org.uqbar.arena.kotlin.extensions.bindVisibleTo
import org.uqbar.arena.widgets.Panel
import org.uqbar.arena.windows.Window
import org.uqbar.arena.windows.WindowOwner

class RegisterWindow(owner: WindowOwner, model: RegisterModel) : Window<RegisterModel>(owner, model) {
    override fun createContents(p0: Panel?) {
        TODO("Not yet implemented")
    }


}