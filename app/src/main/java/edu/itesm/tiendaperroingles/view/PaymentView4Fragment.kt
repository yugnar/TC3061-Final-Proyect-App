package edu.itesm.tiendaperroingles.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import edu.itesm.tiendaperroingles.R

/**
 * A simple [Fragment] subclass.
 * Use the [PaymentView4Fragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class PaymentView4Fragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_payment_view4, container, false)
    }

}