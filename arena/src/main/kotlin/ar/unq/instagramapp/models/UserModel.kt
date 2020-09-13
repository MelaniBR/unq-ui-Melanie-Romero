package ar.unq.instagramapp.models

import org.unq.ui.model.InstagramSystem
import org.unq.ui.model.User
import org.uqbar.commons.model.annotations.Observable
import java.util.*

@Observable
class UserModel (
    val instagramSystem: InstagramSystem,
    var userId: String = "",
    var name: String = "",
    var email: String = "",
    var password:String = "",
    var rePassword: String = "",
    var imagen: String = ""
){
    val userModel : UserModel = this
    fun cargarInformacionDeUsuario(userId: String){
      val usuario = this.instagramSystem.getUser(userId)
        this.userId = userId
        this.name = usuario.name
        this.email = usuario.email
        this.password = usuario.password
        this.imagen = usuario.image
    }
    fun editarUsuario(userEdit : EditModel){

        this.name = userEdit.name
        this.password = userEdit.password
        this.imagen = userEdit.imagen
        instagramSystem.editProfile(userEdit.idUser,userEdit.name,userEdit.password,userEdit.imagen)

    }

}
@Observable
class UserEdit(
    var name : String,
    var password: String ,
    var imagen: String
)

