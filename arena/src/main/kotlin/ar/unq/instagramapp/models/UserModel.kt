package ar.unq.instagramapp.models

import org.unq.ui.model.InstagramSystem
import org.unq.ui.model.User
import org.uqbar.commons.model.annotations.Observable

@Observable
class UserModel (
    val instagramSystem: InstagramSystem,
    var userId: String = "",
    var name: String = "",
    var email: String = "",
    var password:String = "",
    var rePassword: String = "",
    var imagen: String = "" ,
    var newPassword : String = "",
    var error: Boolean = false,
    var errorMessage : String = ""
    
){

    fun uploadUserInformation(usuario: User){

        this.userId = usuario.id
        this.name = usuario.name
        this.email = usuario.email
        this.password = usuario.password
        this.imagen = usuario.image
    }
    fun editUser(userModel : UserModel){

        this.name = userModel.name
        this.password = userModel.password
        this.imagen = userModel.imagen
        instagramSystem.editProfile(userModel.userId,userModel.name,userModel.password,userModel.imagen)

    }
    fun validatePassword() {
        if (!(password == rePassword)) {
            throw Exception()}
        else {
            password = newPassword
        }

    }
}


