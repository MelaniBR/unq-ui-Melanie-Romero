package ar.unq.instagramapp.models

import org.uqbar.commons.model.annotations.Observable
import java.awt.Color
import java.util.*

@Observable
abstract class BasicValidation<T> (
    override val errorMessage: String = "",
    override var hasError: Boolean = false
    ): IValidation<T>{

}

@Observable
abstract class ArrayValidation<T>(
    val validations: List<IValidation<T>>,
    override var errorMessage: String = "",
    override var hasError: Boolean = false
): IValidation<T>{
    override fun validate(value: T){
        // do something with the rest of elements
        for (validation in validations) {
            validation.validate(value)
            if(validation.hasError){
                hasError = true
                errorMessage = validation.errorMessage
                break;
            }else{
                hasError = false
                errorMessage = ""
            }
        }
    }
}


interface IValidation<T>{
    val errorMessage: String
    var hasError: Boolean
    fun validate(value: T)
}

class RequiredValidation(
    override val errorMessage: String
) : BasicValidation<String>(errorMessage, false) {
    override fun validate(value: String) {
        hasError = value.isEmpty();
    }
}

class EmailValidation(
    override val errorMessage: String
) : BasicValidation<String>(errorMessage, false) {
    override fun validate(value: String) {
        hasError = !isValidEmail(value);
    }
    fun isValidEmail(value: String): Boolean {
        val regex = """^[\w-\.]+@([\w-]+\.)+[\w-]{2,4}${'$'}""".toRegex()
        return regex.matches(value)
    }
}
