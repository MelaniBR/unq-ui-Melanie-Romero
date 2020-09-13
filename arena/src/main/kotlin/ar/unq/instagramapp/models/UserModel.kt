package ar.unq.instagramapp.models

import ar.unq.instagramapp.model.EditUserModel
import org.unq.ui.model.InstagramSystem
import org.uqbar.commons.model.annotations.Observable

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

    fun uploadUserInformation(userId: String){
      val usuario = this.instagramSystem.getUser(userId)
        this.userId = userId
        this.name = usuario.name
        this.email = usuario.email
        this.password = usuario.password
        this.imagen = usuario.image
    }
    fun editUser(userEditUser : EditUserModel){

        this.name = userEditUser.name
        this.password = userEditUser.password
        this.imagen = userEditUser.imagen
        instagramSystem.editProfile(userEditUser.userId,userEditUser.name,userEditUser.password,userEditUser.imagen)

    }

}


