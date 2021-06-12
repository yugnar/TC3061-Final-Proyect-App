package edu.itesm.tiendaperroingles.view.recyclerview.list

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.billingclient.api.*
import com.google.firebase.database.*
import edu.itesm.tiendaperroingles.databinding.FragmentCartBinding
import edu.itesm.tiendaperroingles.view.recyclerview.details.CartItemModel


abstract class SwipeToDelete (context: Context,
                              direccion: Int, direccionArrastre: Int):
    ItemTouchHelper.SimpleCallback(direccion, direccionArrastre){
    override fun onMove(
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder,
        target: RecyclerView.ViewHolder
    ): Boolean {
        return true
    }

    override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {

    }

}

class CartFragment : Fragment() {

    private lateinit var database: FirebaseDatabase
    private lateinit var reference: DatabaseReference
    private val userCart = ArrayList<CartItemModel>()

    private lateinit var billingClient: BillingClient
    private val skuList = listOf("producto1")

    private var _binding: FragmentCartBinding? = null
    private val binding get() = _binding!!


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        database = FirebaseDatabase.getInstance()
        val usuario = Firebase.auth.currentUser
        reference = database.getReference("cart/${usuario!!.uid}")

        setupBillingClient()

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentCartBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (userCart.size == 0) {
            retrieveCartData()
        } else {
            binding.recyclerCart.apply {
                layoutManager = LinearLayoutManager(activity)
                adapter = CartAdapter(userCart, context)
            }
        }
    }

    private fun setupBillingClient() {
        val purchasesUpdatedListener =
            PurchasesUpdatedListener { billingResult, purchases ->
                // To be implemented in a later section.
            }

        billingClient = BillingClient.newBuilder(requireContext())
            .enablePendingPurchases()
            .setListener(purchasesUpdatedListener)
            .build()

        billingClient.startConnection(object : BillingClientStateListener {
            override fun onBillingSetupFinished(billingResult: BillingResult) {
                Log.d("billing", billingResult.responseCode.toString())
                if (billingResult.responseCode ==  BillingClient.BillingResponseCode.OK) {
                    // The BillingClient is ready. You can query purchases here.
                    loadAllSKUs()
                }
            }
            override fun onBillingServiceDisconnected() {
                // Try to restart the connection on the next request to
                // Google Play by calling the startConnection() method.
            }

        })
    }

    private fun loadAllSKUs() = if (billingClient.isReady) {
        val params = SkuDetailsParams
            .newBuilder()
            .setSkusList(skuList)
            .setType(BillingClient.SkuType.INAPP)
            .build()

        billingClient.querySkuDetailsAsync(params) { billingResult, skuDetailsList ->
            if (skuDetailsList != null) {
                Log.d("sku", skuDetailsList.toString())
                if (billingResult.responseCode == BillingClient.BillingResponseCode.OK && skuDetailsList.isNotEmpty()) {
                    for (skuDetails in skuDetailsList) {
                        if (skuDetails.sku == "producto1")
                            binding.checkoutButton.setOnClickListener {
                                val billingFlowParams = BillingFlowParams
                                    .newBuilder()
                                    .setSkuDetails(skuDetails)
                                    .build()
                                billingClient.launchBillingFlow(
                                    requireActivity(),
                                    billingFlowParams
                                )
                            }
                    }
                }
            }
        }

    } else {
        println("Billing Client not ready")
    }



    private fun retrieveCartData(){
        binding.recyclerCart.apply {
            reference.addValueEventListener(object: ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    if(userCart.size == 0){
                        for(item in snapshot.children){
                            var objeto = item.getValue(CartItemModel::class.java)
                            userCart.add(objeto as CartItemModel)
                        }
                    }

                    layoutManager = LinearLayoutManager(activity)
                    adapter = CartAdapter(userCart, context)

                    val item = object : SwipeToDelete(context,
                        ItemTouchHelper.UP,ItemTouchHelper.LEFT){
                        override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                            super.onSwiped(viewHolder, direction)
                            val item = userCart[ viewHolder.adapterPosition ]
                            borraItem(item)
                        }
                    }
                    val itemTouchHelper = ItemTouchHelper(item)
                    itemTouchHelper.attachToRecyclerView(binding.recyclerCart)

                }
                override fun onCancelled(error: DatabaseError) {
                    Log.i("cartError", "Failed to get user items in cart.")
                }
            })
        }
    }

    private fun borraItem(item: CartItemModel){
        val usuario= Firebase.auth.currentUser
        val referencia= FirebaseDatabase.getInstance().getReference("cart/${usuario!!.uid}/${item.id}")
        userCart.remove(item)
        referencia.removeValue()
    }

}