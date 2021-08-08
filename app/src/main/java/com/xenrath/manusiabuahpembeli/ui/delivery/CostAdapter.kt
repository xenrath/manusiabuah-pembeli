package com.xenrath.manusiabuahpembeli.ui.delivery

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.xenrath.manusiabuahpembeli.R
import com.xenrath.manusiabuahpembeli.data.database.model.DataCosts
import com.xenrath.manusiabuahpembeli.utils.CurrencyHelper

class CostAdapter(
    var costs: ArrayList<DataCosts>,
    var weight: String,
    private var courier: String,
    private var listener: Listener
): RecyclerView.Adapter<CostAdapter.Holder>() {

    class Holder(view: View): RecyclerView.ViewHolder(view) {
        val tvCourier = view.findViewById<TextView>(R.id.tv_courier)!!
        val tvDeliveryTime = view.findViewById<TextView>(R.id.tv_delivery_time)!!
        val tvPrice = view.findViewById<TextView>(R.id.tv_price)!!
        val tvWeight = view.findViewById<TextView>(R.id.tv_weight)!!
        val rbCourier = view.findViewById<RadioButton>(R.id.rb_courier)!!
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.adapter_courier, parent, false)
        return Holder(view)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: Holder, position: Int) {
        val costs = costs[position]
        holder.tvCourier.text = courier + " " + costs.service
        val cost = costs.cost!![0]
        holder.tvDeliveryTime.text = cost.etd + " hari kerja"
        holder.tvPrice.text = CurrencyHelper.changeToRupiah(cost.value!!)
        holder.tvWeight.text = weight + " x " + CurrencyHelper.changeToRupiah(cost.value!!)
        holder.rbCourier.setOnClickListener {
            costs.is_active = true
            listener.onClicked(costs, holder.adapterPosition)
        }
    }

    override fun getItemCount(): Int {
        return costs.size
    }

    interface Listener {
        fun onClicked(data: DataCosts, position: Int)
    }

    fun setData(newDataCosts: List<DataCosts>) {
        costs.clear()
        costs.addAll(newDataCosts)
        notifyDataSetChanged()
    }

}