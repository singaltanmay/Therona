package com.tanmay.workboards.ui.user.signup

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.tanmay.therona.R
import com.tanmay.therona.TheronaApplication
import kotlinx.android.synthetic.main.fragment_sign_up.*

class SignUpFragment : Fragment() {

    private lateinit var viewModel: SignUpViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_sign_up, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(SignUpViewModel::class.java)

        viewModel.firstName.observe(viewLifecycleOwner, Observer {
            firstNameTextInputEditText.text = it
        })

        viewModel.lastName.observe(viewLifecycleOwner, Observer {
            lastNameTextInputEditText.text = it
        })

        viewModel.email.observe(viewLifecycleOwner, Observer {
            emailIdTextInputEditText.text = it
        })

        viewModel.password.observe(viewLifecycleOwner, Observer {
            passwordTextInputEditText.text = it
        })

        viewModel.confirmPassword.observe(viewLifecycleOwner, Observer {
            confirmPasswordTextInputEditText.text = it
        })


        frag_user_creation_done_button.setOnClickListener {
            onDonePressed()
        }
    }

    private fun onDonePressed() {

        updateViewModel()

        if (viewModel.email.value.isNullOrBlank()) {
            emailIdTextInputLayout.error = "Email cannot be empty!"
            return
        }

        if (!viewModel.password.value.toString()
                .equals(viewModel.confirmPassword.value.toString())
        ) {
            confirmPasswordTextInputLayout.error = "Password doesn't match!"
            return
        }

        val application = context?.applicationContext as TheronaApplication
        application.user = TheronaApplication.User(
            viewModel.firstName.value.toString(),
            viewModel.lastName.value.toString(),
            viewModel.email.value.toString(),
            viewModel.password.value.toString()
        )

        application.userLoggedIn = true
        Toast.makeText(
            context,
            "Welcome ${viewModel.firstName.value.toString()}!", Toast.LENGTH_SHORT
        ).show()
        activity?.finish()
    }

    private fun updateViewModel() {
        val fname = firstNameTextInputEditText.text
        if (fname != null) {
            viewModel.updateFirstName(fname)
        }

        val lname = lastNameTextInputEditText.text
        if (lname != null) {
            viewModel.updateLastName(lname)
        }

        val email = emailIdTextInputEditText.text
        if (email != null) {
            viewModel.updateEmail(email)
        }

        val password = passwordTextInputEditText.text
        if (password != null) {
            viewModel.updatePassword(password)
        }

        val confPassword = confirmPasswordTextInputEditText.text
        if (confPassword != null) {
            viewModel.updateConfirmPassword(confPassword)
        }
    }

}
