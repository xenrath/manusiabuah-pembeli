package com.xenrath.manusiabuahpembeli.ui.product

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.PopupMenu
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.xenrath.manusiabuahpembeli.R
import com.xenrath.manusiabuahpembeli.data.Constant
import com.xenrath.manusiabuahpembeli.data.database.model.DataProduct
import com.xenrath.manusiabuahpembeli.utils.GlideHelper

class ProductAdapter(
    val context: Context,
    var dataProduct: ArrayList<DataProduct>,
    val clickListener: (DataProduct, Int, String) -> Unit
    ): RecyclerView.Adapter<ProductAdapter.Holder>() {

    class Holder(view: View): RecyclerView.ViewHolder(view) {
        val imgProduct = view.findViewById<ImageView>(R.id.iv_product)!!
        val tvName = view.findViewById<TextView>(R.id.tv_name)!!
        val tvAddress = view.findViewById<TextView>(R.id.tv_address)!!
        val tvPrice = view.findViewById<TextView>(R.id.tv_price)!!
        val cvProduct = view.findViewById<CardView>(R.id.layout_product)!!
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.adapter_my_product, parent, false)
        return Holder(view)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        val product = dataProduct[position]
        holder.tvName.text = product.name
        holder.tvPrice.text = product.price
        holder.tvAddress.text = product.address
        GlideHelper.setImage(context, Constant.IP_IMAGE + product.image!!, holder.imgProduct)
        holder.cvProduct.setOnClickListener {
            val popupMenu = PopupMenu(context, holder.cvProduct)
            popupMenu.inflate(R.menu.menu_options)
            popupMenu.setOnMenuItemClickListener {
                when (it.itemId) {
                    R.id.action_update -> {
                        Constant.PRODUCT_ID = product.id!!
                        clickListener(product, position, "update")
                    }

                    R.id.action_delete -> {
                        Constant.PRODUCT_ID = product.id!!
                        clickListener(product, position, "delete")
                    }
                }
                true
            }
            popupMenu.show()
        }
    }

    override fun getItemCount(): Int {
        return dataProduct.size
    }

    fun setData(newDataProduct: List<DataProduct>) {
        dataProduct.clear()
        dataProduct.addAll(newDataProduct)
        notifyDataSetChanged()
    }

    fun removeProduct(position: Int) {
        dataProduct.removeAt(position)
        notifyItemRemoved(position)
        notifyItemRangeChanged(position, dataProduct.size)
    }

}