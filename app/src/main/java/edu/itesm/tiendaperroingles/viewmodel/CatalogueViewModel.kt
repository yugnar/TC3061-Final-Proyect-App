package edu.itesm.tiendaperroingles.viewmodel

import android.content.ContentValues
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import edu.itesm.tiendaperroingles.model.CatalogueModel

class CatalogueViewModel: ViewModel() {
    private val db = Firebase.firestore
    val catalogueItems = MutableLiveData<List<CatalogueModel>>()

    fun getCatalogueItems() {
        db.collection("catalogue").get().addOnSuccessListener { res ->
            val itemList = ArrayList<CatalogueModel>()
            for(document in res) {
                val item = document.toObject(CatalogueModel::class.java)
                itemList.add(item)
            }
            catalogueItems.postValue(itemList)
        }.addOnFailureListener { e ->
            Log.w(ContentValues.TAG, "Error getting documents.", e)
        }
    }
}