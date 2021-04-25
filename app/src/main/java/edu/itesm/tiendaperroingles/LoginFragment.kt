package edu.itesm.tiendaperroingles

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.Navigation
import androidx.navigation.Navigation.findNavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment.findNavController
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.fragment_login.*


/**
 * A simple [Fragment] subclass.
 * Use the [LoginFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class LoginFragment : Fragment() {

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
        return inflater.inflate(R.layout.fragment_login, container, false)
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
                        loginButton.findNavController().navigate(R.id.action_loginFragment_to_mainMenu1Fragment)
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

    }
}
