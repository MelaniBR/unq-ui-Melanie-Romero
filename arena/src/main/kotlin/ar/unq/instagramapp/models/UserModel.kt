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


    fun invalidPassword() : Boolean {
        return password == ""
    }

    fun invalidRePassword() : Boolean{
        return invalidPassword() || password != rePassword;
    }

    fun invalidNewPassword():Boolean{
        return newPassword == ""
    }

    fun invalidUserName() : Boolean{
        return name == ""
    }

    fun invalidEmail():Boolean{
        val regex = """^[\w-\.]+@([\w-]+\.)+[\w-]{2,4}${'$'}""".toRegex()
        return email.isEmpty() || !regex.matches(email)
    }

    fun validateRePassword(){
        if(!invalidRePassword()){
            error = true
            errorMessage = "La contraseña no se verifico correctamente"
        }
    }

    fun validatePassword(){
        if(password == ""){
            error = true
            errorMessage = "Debes ingresar una contraseña"
        }
    }

    fun validateNewPassword(){
        if(invalidNewPassword()){
            error = true
            errorMessage = "Debes que ingresar la nueva contraseña"
        }
    }

    fun validateUserName(){
        if(invalidUserName()){
            error = true
            errorMessage = "Debes ingresar tu nombre"
        }
    }

    fun validateEmail(){
        if(invalidEmail()) {
            error = true
            errorMessage = "Debes ingresar una direcciona de Email valida"
        }
    }
}


