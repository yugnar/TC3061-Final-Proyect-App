package edu.itesm.tiendaperroingles.view.recyclerview.details

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import edu.itesm.tiendaperroingles.R
import edu.itesm.tiendaperroingles.databinding.FragmentProductBinding
import kotlinx.android.synthetic.main.fragment_product.*
import kotlin.properties.Delegates


/**
 * A simple [Fragment] subclass.
 * Use the [ProductFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ProductFragment : Fragment() {
    private var _binding : FragmentProductBinding? = null
    private val binding get() = _binding!!

    private val args by navArgs<ProductFragmentArgs>()
    private var cantidad = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentProductBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        Glide.with(binding.root).load(args.productDetails.image).into(binding.imagenProducto)
        binding.tituloProducto.text = args.productDetails.name
        binding.precioProducto.text = args.productDetails.price

        binding.precioFinal.text = "$cantidad"

    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}