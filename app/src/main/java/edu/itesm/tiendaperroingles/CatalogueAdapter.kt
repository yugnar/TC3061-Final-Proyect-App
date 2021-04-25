package edu.itesm.tiendaperroingles

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView

class CatalogueAdapter(private val catalogueItems: List<Catalogue>):RecyclerView.Adapter<CatalogueAdapter.CatalogueViewHolder>() {
    inner class CatalogueViewHolder(item: View) : RecyclerView.ViewHolder(item){
        var titulo = item.findViewById<TextView>(R.id.tituloProducto)
        var foto = item.findViewById<ImageView>(R.id.imagenProducto)
        var precio = item.findViewById<TextView>(R.id.precioProducto)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CatalogueViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val itemVista = inflater.inflate(R.layout.catalogue_element_layout,parent, false)
        return CatalogueViewHolder(itemVista)
    }

    override fun onBindViewHolder(holder: CatalogueAdapter.CatalogueViewHolder, position: Int) {
        val catalogueItems = catalogueItems[position]
        holder.titulo.text = catalogueItems.name
        holder.precio.text = catalogueItems.price
        holder.itemView.setOnClickListener {
            val action = DetailCatalogue2FragmentDirections.actionDetailCatalogue2FragmentToProductDetail3Fragment(catalogueItems)
            holder.itemView.findNavController().navigate(action)
        }
    }

    override fun getItemCount(): Int {
        return catalogueItems.size
    }
}