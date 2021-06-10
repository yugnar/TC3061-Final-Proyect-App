package edu.itesm.tiendaperroingles.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import edu.itesm.tiendaperroingles.R
import edu.itesm.tiendaperroingles.databinding.FragmentMainMenu1Binding
import kotlinx.android.synthetic.main.fragment_login.*
import kotlinx.android.synthetic.main.fragment_main_menu1.*


/**
 * A simple [Fragment] subclass.
 * Use the [MainMenu1Fragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class MainMenu1Fragment : Fragment() {
    private var _binding: FragmentMainMenu1Binding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentMainMenu1Binding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        catalogueButton.setOnClickListener (
            Navigation.createNavigateOnClickListener(R.id.action_mainMenu1Fragment_to_catalogueListFragment)
        )
        citasButton.setOnClickListener(
            Navigation.createNavigateOnClickListener(R.id.action_mainMenu1Fragment_to_appointments5Fragment)
        )
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}