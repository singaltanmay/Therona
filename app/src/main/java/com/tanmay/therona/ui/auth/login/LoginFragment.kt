import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.tanmay.therona.R
import com.tanmay.therona.TheronaApplication
import com.tanmay.therona.ui.auth.AuthActivity
import com.tanmay.workboards.ui.user.login.LoginViewModel
import com.tanmay.workboards.ui.user.signup.SignUpFragment
import kotlinx.android.synthetic.main.fragment_login.*


class LoginFragment : Fragment() {

    private lateinit var viewModel: LoginViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_login, container, false)
    }

    override fun onStart() {
        super.onStart()
        (activity as AppCompatActivity).supportActionBar?.hide()
        Thread { setupNavigationListeners() }.start()
    }

    private fun setupNavigationListeners() {
        frag_user_login_cancel_button.setOnClickListener {
            activity?.finish()
        }
        frag_user_login_sing_up_button.setOnClickListener {
            (activity as AuthActivity).navigateTo(SignUpFragment())
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(LoginViewModel::class.java)

        viewModel.email.observe(viewLifecycleOwner, Observer {
            frag_user_login_email_id_edit_text.text = it
        })

        viewModel.password.observe(viewLifecycleOwner, Observer {
            frag_user_login_password_edit_text.text = it
        })

        frag_user_login_next_button.setOnClickListener {
            onLoginPressed()
        }
    }

    private fun onLoginPressed() {
        updateViewModel()

        val emailString = viewModel.email.value.toString()
        val passwordString = viewModel.password.value.toString()
        val application = context?.applicationContext as TheronaApplication
        val user = application.user
        if (user.email.equals(emailString) && user.password.equals(passwordString)) {
            application.userLoggedIn = true
            Toast.makeText(context, "Welcome ${user.firstName != null ?: ""}!", Toast.LENGTH_SHORT)
                .show()
            activity?.finish()
        } else Toast.makeText(context, "Invalid credentials", Toast.LENGTH_SHORT).show()
    }

    override fun onPause() {
        super.onPause()
        (activity as AppCompatActivity).supportActionBar?.show()
    }

    private fun updateViewModel() {
        val email = frag_user_login_email_id_edit_text.text
        if (email != null) {
            viewModel.updateEmail(email)
        }

        val password = frag_user_login_password_edit_text.text
        if (password != null) {
            viewModel.updatePassword(password)
        }
    }

}
