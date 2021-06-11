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
import edu.itesm.tiendaperroingles.R
import edu.itesm.tiendaperroingles.databinding.FragmentRegisterBinding
import kotlinx.android.synthetic.main.fragment_login.*
import kotlinx.android.synthetic.main.fragment_register.*

/**
 * A simple [Fragment] subclass.
 * Use the [RegisterFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class RegisterFragment : Fragment() {
    private var _binding: FragmentRegisterBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentRegisterBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
//        loginText.setOnClickListener {
//            fragmentManager?.popBackStackImmediate()
//        }
        loginText.setOnClickListener(
            Navigation.createNavigateOnClickListener(R.id.action_registerFragment_to_loginFragment)
        )
        setupRegister()
    }

    private fun setupRegister() {
        registerButton.setOnClickListener {
            if (registerEmailText.text.isNotEmpty() && registerPwdText.text.isNotEmpty()) {
                if (registerPwdText.text.toString() == registerPwdText2.text.toString()) {
                    if (TCCheckbox.isChecked) {
                        FirebaseAuth.getInstance().createUserWithEmailAndPassword(
                            registerEmailText.text.toString(),
                            registerPwdText.text.toString()
                        ).addOnCompleteListener {
                            if (it.isSuccessful) {
                                usuarioCreado()
                                registerEmailText.text.clear()
                                registerPwdText.text.clear()
                                registerPwdText2.text.clear()
                            }
                        }.addOnFailureListener {
                            Toast.makeText(context, it.toString(), Toast.LENGTH_LONG).show()
                        }
                    }
                else{
                        Toast.makeText(context, "You need to agree to the Terms and Conditions.", Toast.LENGTH_LONG).show()
                    }
                }
                else{
                    Toast.makeText(context,
                        "Passwords don't match.", Toast.LENGTH_LONG).show()
                }
            }
            else{
                Toast.makeText(context,"Mail and password cannot be empty.", Toast.LENGTH_LONG).show()
            }
        }
    }

    private fun usuarioCreado(){
        val builder = AlertDialog.Builder(requireContext())
        builder.setMessage("¡Nuevo usuario creado con éxito!")
            .setCancelable(false)
            .setPositiveButton("Ok", DialogInterface.OnClickListener{
                dialog, id -> finish()
            })
        val alert = builder.create()
        alert.setTitle("Registro exitoso")
        alert.show()
    }

    private fun finish(){
        view?.findNavController()?.navigate(R.id.action_registerFragment_to_loginFragment)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}