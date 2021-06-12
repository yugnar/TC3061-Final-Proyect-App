package edu.itesm.tiendaperroingles.view.recyclerview.list

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import edu.itesm.tiendaperroingles.view.recyclerview.details.CartItemModel
import edu.itesm.tiendaperroingles.R

class CartAdapter (private val items : List<CartItemModel>, val context: Context)
    : RecyclerView.Adapter<CartAdapter.ItemViewHolder>(){

    class ItemViewHolder(val row: View) : RecyclerView.ViewHolder(row){

        fun bind(property: CartItemModel){

            val name = row.findViewById<TextView>(R.id.row_name)
            val quantity = row.findViewById<TextView>(R.id.row_quantity)
            val price = row.findViewById<TextView>(R.id.row_cost)
            val image = row.findViewById<ImageView>(R.id.row_image)

            name.text = property.name
            quantity.text = property.quantity.toString()
            price.text = "$ - test || - " + property.price

            Glide.with(row.context)
                .load(property.image)
                .fitCenter()
                .into(image)
        }
    }

    //Crea el renglón
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val renglon_vista = inflater.inflate(R.layout.recycler_cart_row,parent, false)
        return ItemViewHolder(renglon_vista)
    }

    override fun getItemCount(): Int {
        return items!!.size
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val item = items[position]
        holder.bind(items!![position])
    }
}