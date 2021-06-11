package edu.itesm.tiendaperroingles.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import edu.itesm.tiendaperroingles.R
import edu.itesm.tiendaperroingles.databinding.FragmentMainMenuBinding

/**
 * A simple [Fragment] subclass.
 * Use the [MainMenuFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class MainMenuFragment : Fragment() {
    private var _binding: FragmentMainMenuBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentMainMenuBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        binding.cat.setOnClickListener(
            Navigation.createNavigateOnClickListener(R.id.action_mainMenuFragment_to_catalogueListFragment)
        )

        binding.calendario.setOnClickListener(
            Navigation.createNavigateOnClickListener(R.id.action_mainMenuFragment_to_calendarFragment)
        )
    }
}