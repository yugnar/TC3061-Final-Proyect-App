package edu.itesm.tiendaperroingles.view.recyclerview.details

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class CartItemModel(var id: String?, var name: String?, var price: String?, var image: String?, var quantity: Int) : Parcelable {
    constructor():this("", "", "", "",0)
}
