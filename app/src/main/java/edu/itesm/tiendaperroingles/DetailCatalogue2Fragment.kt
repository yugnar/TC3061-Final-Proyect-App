package edu.itesm.tiendaperroingles

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.GridLayout
import androidx.navigation.Navigation
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.fragment_detail_catalogue2.*
import kotlinx.android.synthetic.main.fragment_main_menu1.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [DetailCatalogue2Fragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class DetailCatalogue2Fragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private val db = Firebase.firestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_detail_catalogue2, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recycler_catalogue.apply {
            layoutManager = GridLayoutManager(activity, 2)
            db.collection("catalogue").get().addOnSuccessListener { result ->
                var items = ArrayList<Catalogue>()
                for (document in result) {
                    val catalogueItem = document.toObject(Catalogue::class.java)
                    items.add(catalogueItem)
                    Log.d(TAG, "$items")
                }
                adapter = CatalogueAdapter(items)
            }.addOnFailureListener { exception ->
                Log.w(TAG, "Error getting documents.", exception)
            }
        }
    }
}