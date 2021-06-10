package edu.itesm.tiendaperroingles.view.recyclerview.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import edu.itesm.tiendaperroingles.R
import edu.itesm.tiendaperroingles.databinding.CatalogueElementLayoutBinding
import edu.itesm.tiendaperroingles.model.CatalogueModel
import edu.itesm.tiendaperroingles.view.recyclerview.list.CatalogueListFragmentDirections

class CatalogueAdapter :RecyclerView.Adapter<CatalogueAdapter.CatalogueViewHolder>() {

    private var catalogueItems: List<CatalogueModel> = emptyList()
    inner class CatalogueViewHolder(val binding: CatalogueElementLayoutBinding) : RecyclerView.ViewHolder(binding.root)

    fun setData(data: List<CatalogueModel>) {
        catalogueItems = data
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CatalogueViewHolder {
        val binding = CatalogueElementLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CatalogueViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CatalogueViewHolder, position: Int) {
        with(holder) {
            with(catalogueItems[position]) {
                binding.tituloProducto.text = this.name
                binding.precioProducto.text = this.price
                Glide.with(binding.root)
                    .load(this.image)
                    .placeholder(R.drawable.cama_perro_template)
                    .into(binding.imagenProducto)

                itemView.setOnClickListener {
                    val action =
                       CatalogueListFragmentDirections.actionCatalogueListFragmentToProductFragment(this)
                    itemView.findNavController().navigate(action)
                }
            }
        }
    }

    override fun getItemCount(): Int {
        return catalogueItems.size
    }
}