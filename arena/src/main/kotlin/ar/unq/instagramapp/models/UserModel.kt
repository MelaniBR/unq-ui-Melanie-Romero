package ar.unq.instagramapp.models

import org.unq.ui.model.InstagramSystem
import org.unq.ui.model.User
import org.uqbar.commons.model.annotations.Observable
import java.util.*

@Observable
class UserModel (
    private val instagramSystem: InstagramSystem,
    private val user: User? = null,
    var name: String = "",
    var email: String = "",
    var password:String = "",
    var image: String = "",
    var rePassword: String = "",
    var currentPassword: String = "",
    var newPassword: String = ""
) {
    fun userId(): String {
        return user!!.id
    }

    fun updateUser() {
        user!!.name = name
        user!!.password = password
        user!!.image = image
        instagramSystem.editProfile(user!!.id, user!!.name, user!!.password, user!!.image)
    }

    fun editUser(userEditUser: UserModel) {
        this.name = userEditUser.name
        this.password = userEditUser.password
        this.image = userEditUser.image
        updateUser()
    }

    fun changePassword(userEditUser: UserModel) {
        this.name = userEditUser.name
        this.password = userEditUser.newPassword
        this.image = userEditUser.image
        updateUser()
    }

    fun copy(): UserModel {
        return UserModel(instagramSystem, user, name, email, password, image)
    }

    fun validateRegisterForm(){
        validationUserName.validate(name)
        validationEmail.validate(email)
        validationPassword.validate(password)
        validationRePassword.validate(rePassword)
    }

    val isValidRegisterForm: Boolean
        get() = !validationUserName.hasError && !validationEmail.hasError && !validationPassword.hasError && !validationRePassword.hasError


    fun validateEditUserForm(){
        validationUserName.validate(name)
    }

    val isValidEditUserForm: Boolean
        get() = !validationUserName.hasError



    val validationUserName: RequiredValidation = RequiredValidation("Debes ingresar tu nombre")

    val validationPassword:  RequiredValidation = RequiredValidation("Debes ingresar una contraseña")

    val validationNewPassword:  RequiredValidation = RequiredValidation("Debes ingresar una contraseña")

    val validationRePassword:  ArrayValidation<String> = object : ArrayValidation<String> (
        listOf(
            RequiredValidation("Debes verificar la contraseña"),
            object : BasicValidation<String>("La contraseña no se verifico correctamente" ) {
                override fun validate(value: String) {
                    hasError =  password != rePassword;
                }
            }
        )
    ){}

    val validationReNewPassword:  ArrayValidation<String> = object : ArrayValidation<String> (
        listOf(
            RequiredValidation("Debes verificar la contraseña"),
            object : BasicValidation<String>("La contraseña no se verifico correctamente" ) {
                override fun validate(value: String) {
                    hasError =  newPassword != rePassword;
                }
            }
        )
    ){}

    val validationCurrentPassword: ArrayValidation<String> = object : ArrayValidation<String> (
        listOf(
            RequiredValidation("Debes ingresar la contraseña actual"),
            object : BasicValidation<String>("El valor ingresar no coincide con la contraseña actual" ) {
                override fun validate(value: String) {
                    hasError = value != password
                }
            }
        )
    ){}

    val validationEmail: ArrayValidation<String> = object : ArrayValidation<String> (
        listOf(
            RequiredValidation("Debes ingresar una direccion de email"),
            EmailValidation("Debes ingresar una direccion de email valida")
        )
    ){}

    fun validateChangePasswordForm(){
        validationCurrentPassword.validate(currentPassword)
        validationNewPassword.validate(newPassword)
        validationReNewPassword.validate(rePassword)
    }

    val isValidChangePasswordForm: Boolean
        get() = !validationCurrentPassword.hasError && !validationNewPassword.hasError && !validationReNewPassword.hasError

}


