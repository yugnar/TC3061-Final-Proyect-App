package edu.itesm.tiendaperroingles.view.recyclerview.list

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import edu.itesm.tiendaperroingles.databinding.FragmentCatalogueListBinding
import edu.itesm.tiendaperroingles.model.CatalogueModel
import edu.itesm.tiendaperroingles.viewmodel.CatalogueViewModel
import kotlinx.android.synthetic.main.fragment_catalogue_list.*

/**
 * A simple [Fragment] subclass.
 * Use the [CatalogueListFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class  CatalogueListFragment : Fragment() {
    private var _binding: FragmentCatalogueListBinding? = null
    private val binding get() = _binding!!

    private lateinit var viewModel: CatalogueViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        viewModel = ViewModelProvider(this).get(CatalogueViewModel::class.java)
        _binding = FragmentCatalogueListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.recyclerCatalogue.layoutManager = GridLayoutManager(activity, 2)
        binding.recyclerCatalogue.adapter = CatalogueAdapter()

        viewModel.getCatalogueItems()
        viewModel.catalogueItems.observe(viewLifecycleOwner, Observer {
            (binding.recyclerCatalogue.adapter as CatalogueAdapter).setData(it)
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}