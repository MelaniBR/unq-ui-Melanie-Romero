package ar.unq.instagramapp.models

import org.unq.ui.model.InstagramSystem
import org.uqbar.commons.model.annotations.Observable

@Observable
class UserModel (
    val instagramSystem: InstagramSystem,
    var userId: String = "",
    var name: String = "",
    var email: String = "",
    var password:String = "",
    var image: String = "",
    var error : Boolean = false,
    var errorMessage: String = "",
    var rePassword: String = "",
    var newPassword: String = ""
){

    fun editUser(userEditUser : UserModel){
        this.name = userEditUser.name
        this.password = userEditUser.password
        this.image = userEditUser.image
        instagramSystem.editProfile(userEditUser.userId,userEditUser.name,userEditUser.password,userEditUser.image)
    }

    fun editPasswordUser(userEditUser : UserModel){
        this.name = userEditUser.name
        this.password = userEditUser.newPassword
        this.image = userEditUser.image
        instagramSystem.editProfile(userEditUser.userId,userEditUser.name,userEditUser.password,userEditUser.image)
    }

    fun copy () : UserModel{
        return UserModel(instagramSystem, userId, name, email, password, image)
    }

    fun resetValidation(){
        error = false
        errorMessage = ""
    }

    fun validatePassword(){
        if(password != rePassword){
            error = true
            errorMessage = "Password validation error! Change re password and try again"
        }
    }


    fun validateNewPassword(){
        if(newPassword == ""){
            error = true
            errorMessage = "New password is required"
        }
    }
    fun validateUserName(){
        if(name == ""){
            error = true
            errorMessage = "Name is required!"
        }
    }
}


