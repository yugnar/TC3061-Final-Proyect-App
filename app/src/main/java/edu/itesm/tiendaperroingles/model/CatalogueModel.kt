package edu.itesm.tiendaperroingles.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class CatalogueModel(var name: String?, var price: String?, var image: String?) : Parcelable {
    constructor():this("", "", "")
}
