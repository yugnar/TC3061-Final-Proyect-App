package edu.itesm.tiendaperroingles.view

import android.content.DialogInterface
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import edu.itesm.tiendaperroingles.R
import edu.itesm.tiendaperroingles.databinding.FragmentLoginBinding
import kotlinx.android.synthetic.main.fragment_login.*


/**
 * A simple [Fragment] subclass.
 * Use the [LoginFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class LoginFragment : Fragment() {
    private var _binding : FragmentLoginBinding? = null
    private val binding get() = _binding!!

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        auth = Firebase.auth
        //setLoginRegister()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        //loginButton.setOnClickListener(Navigation.createNavigateOnClickListener(R.id.action_loginFragment_to_mainMenu1Fragment))
        /*loginButton.setOnClickListener {
            Log.i("importantInfo", "Hello World!")
        }*/
        setLoginRegister()
    }

    private fun setLoginRegister(){
        loginButton.setOnClickListener {
            if(editTextTextEmailAddress.text.isNotEmpty() && editTextTextPassword.text.isNotEmpty()){
                FirebaseAuth.getInstance().signInWithEmailAndPassword(
                    editTextTextEmailAddress.text.toString(),
                    editTextTextPassword.text.toString()
                ).addOnCompleteListener{
                    if(it.isSuccessful){
                        loginButton.findNavController().navigate(R.id.action_loginFragment_to_mainMenuFragment)
                        //Navigation.createNavigateOnClickListener(R.id.action_loginFragment_to_mainMenu1Fragment)
                    }else{
                        Toast.makeText(context,"User/password incorrect.", Toast.LENGTH_LONG).show()
                    }
                }
            }
            else{
                //Either email or password is empty
                Toast.makeText(context,"Please enter an email and password.", Toast.LENGTH_LONG).show()
            }
        }

        registerText.setOnClickListener(
            Navigation.createNavigateOnClickListener(R.id.action_loginFragment_to_registerFragment)
        )

        forgotPasswordText.setOnClickListener {

            if(editTextTextEmailAddress.text.isNotEmpty()){
                Firebase.auth.sendPasswordResetEmail(editTextTextEmailAddress.text.toString())

                val builder = AlertDialog.Builder(requireContext())
                builder.setMessage("Se ha enviado un correo para reestablecer tu contraseña al correo ingresado, si existe.")
                    .setCancelable(false)
                    .setPositiveButton("Ok", DialogInterface.OnClickListener{
                            dialog, id -> finish()
                    })
                val alert = builder.create()
                alert.setTitle("Correo enviado.")
                alert.show()
            }
            else{
                val builder = AlertDialog.Builder(requireContext())
                builder.setMessage("Por favor ingresa un correo al cuál reestablecer la contraseña.")
                    .setCancelable(false)
                    .setPositiveButton("Ok", DialogInterface.OnClickListener{
                            dialog, id -> finish()
                    })
                val alert = builder.create()
                alert.setTitle("Error. Sin información de correo.")
                alert.show()
            }

        }

    }

    private fun finish(){
        editTextTextEmailAddress.text.clear()
        editTextTextPassword.text.clear()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
