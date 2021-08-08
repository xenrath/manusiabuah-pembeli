package com.xenrath.manusiabuahpembeli.ui.bargain

import android.content.Context
import android.content.Intent
import android.graphics.Paint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.xenrath.manusiabuahpembeli.R
import com.xenrath.manusiabuahpembeli.data.Constant
import com.xenrath.manusiabuahpembeli.data.database.model.DataBargain
import com.xenrath.manusiabuahpembeli.ui.bargain.detail.BargainDetailActivity
import com.xenrath.manusiabuahpembeli.utils.CurrencyHelper

class BargainAdapter(
    var context: Context,
    var bargain: ArrayList<DataBargain>
): RecyclerView.Adapter<BargainAdapter.Holder>() {

    class Holder(view: View): RecyclerView.ViewHolder(view) {
        val tvProductName = view.findViewById<TextView>(R.id.tv_product_name)!!
        val tvTotalItem = view.findViewById<TextView>(R.id.tv_total_item)!!
        val tvPrice = view.findViewById<TextView>(R.id.tv_price)!!
        val tvPriceOffer = view.findViewById<TextView>(R.id.tv_price_offer)!!
        val tvStatus = view.findViewById<TextView>(R.id.tv_status)!!
        val layoutBargain = view.findViewById<LinearLayout>(R.id.layout_bargain)!!
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.adapter_bargain, parent, false)
        return Holder(view)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        val bargain = bargain[position]
        val product = bargain.product!!

        holder.tvProductName.text = product.name
        holder.tvPrice.text = CurrencyHelper.changeToRupiah(bargain.price!!)
        holder.tvPrice.paintFlags = holder.tvPrice.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG
        holder.tvPriceOffer.text = CurrencyHelper.changeToRupiah(bargain.price_offer!!)
        holder.tvTotalItem.text = bargain.total_item
        holder.tvStatus.text = bargain.status

        var color = context.getColor(R.color.wait)
        when (bargain.status) {
            "Menunggu" -> color = context.getColor(R.color.customWarning)
            "Diterima" -> color = context.getColor(R.color.customPrimary)
            "Ditolak" -> color = context.getColor(R.color.customDanger)
            "Selesai" -> color = context.getColor(R.color.customSuccess)
        }
        holder.tvStatus.setTextColor(color)

        holder.layoutBargain.setOnClickListener {
            Constant.BARGAIN_ID = bargain.id!!
            context.startActivity(Intent(context, BargainDetailActivity::class.java))
        }
    }

    override fun getItemCount(): Int {
        return bargain.size
    }

    fun setData(newDataBargain: List<DataBargain>) {
        bargain.clear()
        bargain.addAll(newDataBargain)
        notifyDataSetChanged()
    }

    fun removeProduct(position: Int) {
        bargain.removeAt(position)
        notifyItemRemoved(position)
        notifyItemRangeChanged(position, bargain.size)
    }

}