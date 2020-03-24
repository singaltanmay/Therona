package com.tanmay.workboards.ui.user.signup

import android.text.Editable
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SignUpViewModel : ViewModel() {

    private val _firstName = MutableLiveData<Editable>()
    private val _lastName = MutableLiveData<Editable>()
    private val _email = MutableLiveData<Editable>()
    private val _password = MutableLiveData<Editable>()
    private val _confirmPassword = MutableLiveData<Editable>()

    val firstName: LiveData<Editable> = _firstName
    val lastName: LiveData<Editable> = _lastName
    val email: LiveData<Editable> = _email
    val password: LiveData<Editable> = _password
    val confirmPassword: LiveData<Editable> = _confirmPassword

    fun updateFirstName(value: Editable) = run { _firstName.value = value }
    fun updateLastName(value: Editable) = run { _lastName.value = value }
    fun updateEmail(value: Editable) = run { _email.value = value }
    fun updatePassword(value: Editable) = run { _password.value = value }
    fun updateConfirmPassword(value: Editable) = run { _confirmPassword.value = value }
}
