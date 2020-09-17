package ar.unq.instagramapp.models

import org.unq.ui.model.InstagramSystem
import org.unq.ui.model.User
import org.uqbar.commons.model.annotations.Observable

@Observable
class UserModel (
    private val instagramSystem: InstagramSystem,
    private val user: User? = null,
    var name: String = "",
    var email: String = "",
    var password:String = "",
    var image: String = "",
    var error : Boolean = false,
    var errorMessage: String = "",
    var rePassword: String = "",
    var newPassword: String = ""
){
    fun userId(): String{
        return user!!.id
    }

    fun updateUser(){
        user!!.name = name
        user!!.password = password
        user!!.image = image
        instagramSystem.editProfile(user!!.id, user!!.name, user!!.password, user!!.image)
    }

    fun editUser(userEditUser : UserModel){
        this.name = userEditUser.name
        this.password = userEditUser.password
        this.image = userEditUser.image
        updateUser()
    }

    fun editPasswordUser(userEditUser : UserModel){
        this.name = userEditUser.name
        this.password = userEditUser.newPassword
        this.image = userEditUser.image
        updateUser()
    }

    fun copy () : UserModel{
        return UserModel(instagramSystem, user, name, email, password, image)
    }

    fun resetValidation(){
        error = false
        errorMessage = ""
    }

    fun validatePassword(){
        if(password != rePassword){
            error = true
            errorMessage = "Contraseña incorrecta."
        }
    }

    fun validateNewPassword(){
        if(newPassword == ""){
            error = true
            errorMessage = "Debes que ingresar la nueva contraseña"
        }
    }
    fun validateUserName(){
        if(name == ""){
            error = true
            errorMessage = "Debes ingresar tu nombre"
        }
    }
}


