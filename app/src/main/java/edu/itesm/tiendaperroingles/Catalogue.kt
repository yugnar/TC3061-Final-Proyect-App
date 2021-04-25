package edu.itesm.tiendaperroingles

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Catalogue(var name: String?, var price: String?) : Parcelable {
    constructor():this("", "")
}
