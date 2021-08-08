package com.xenrath.manusiabuahpembeli.ui.delivery

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import com.xenrath.manusiabuahpembeli.R
import com.xenrath.manusiabuahpembeli.data.Constant
import com.xenrath.manusiabuahpembeli.data.database.PrefManager
import com.xenrath.manusiabuahpembeli.data.database.model.*
import com.xenrath.manusiabuahpembeli.ui.address.AddressActivity
import com.xenrath.manusiabuahpembeli.utils.ApiKey
import com.xenrath.manusiabuahpembeli.utils.CurrencyHelper
import com.xenrath.manusiabuahpembeli.utils.ToolbarHelper
import kotlinx.android.synthetic.main.activity_delivery.*
import kotlinx.android.synthetic.main.activity_delivery.btn_add_address
import kotlinx.android.synthetic.main.activity_delivery.progress_line
import kotlinx.android.synthetic.main.activity_delivery.toolbar

class DeliveryActivity : AppCompatActivity(), DeliveryContract.View {

    lateinit var prefManager: PrefManager
    lateinit var presenter: DeliveryPresenter

    lateinit var bargain: DataBargain
    lateinit var address: List<DataAddress>

    lateinit var shippingCost: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_delivery)

        prefManager = PrefManager(this)
        presenter = DeliveryPresenter(this)
    }

    override fun onStart() {
        super.onStart()
        presenter.getBargain(Constant.BARGAIN_ID)
        presenter.getAddress(prefManager.prefId.toString())
    }

    override fun onResume() {
        presenter.getAddress(prefManager.prefId.toString())
        super.onResume()
    }

    override fun initActivity() {
        ToolbarHelper.setToolbar(this, toolbar, "Pengiriman")
        showSpinnerCourier()
    }

    override fun initListener() {
        btn_add_address.setOnClickListener {
            startActivity(Intent(this, AddressActivity::class.java))
        }
    }

    override fun onLoading(loading: Boolean) {
        when (loading) {
            true -> {
                progress_line.visibility = View.VISIBLE
            }
            false -> {
                progress_line.visibility = View.GONE
            }
        }
    }

    override fun onLoadingAddress(loading: Boolean) {
        when (loading) {
            true -> {
                pb_address.visibility = View.VISIBLE
                tv_empty.visibility = View.GONE
                cv_address.visibility = View.GONE
            }
            false -> {
                pb_address.visibility = View.GONE
            }
        }
    }

    override fun onLoadingCost(loading: Boolean) {
        when (loading) {
            true -> {
                pb_cost.visibility = View.VISIBLE
            }
            false -> {
                pb_cost.visibility = View.GONE
            }
        }
    }

    override fun onResultBargain(responseBargainDetail: ResponseBargainDetail) {
        bargain = responseBargainDetail.bargain
    }

    @SuppressLint("SetTextI18n")
    override fun onResultAddress(responseAddressDetail: ResponseAddressDetail) {
        address = responseAddressDetail.address
        val a = address[0]
        if (address.isEmpty()) {
            tv_empty.visibility = View.VISIBLE
            cv_address.visibility = View.GONE
            layout_courier.visibility = View.GONE
        } else {
            tv_empty.visibility = View.GONE
            cv_address.visibility = View.VISIBLE

            tv_name.text = a.name
            tv_phone.text = a.phone
            tv_address.text =
                a.address + ", " + a.city_name + ", " + a.postal_code + ", (" + a.place + ")"
            btn_add_address.text = "Ubah alamat"
        }
    }

    override fun showSpinnerCourier() {
        val arrayString = ArrayList<String>()
        arrayString.add("Pilih Kurir")
        arrayString.add("JNE")
        arrayString.add("POS")
        arrayString.add("TIKI")

        val adapter = ArrayAdapter<Any>(this, R.layout.item_spiner, arrayString.toTypedArray())
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spin_courier.adapter = adapter

        spin_courier.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(p0: AdapterView<*>?, view: View?, position: Int, id: Long) {
                if (position != 0) {
                    tv_empty_cost.visibility = View.GONE
                    getCost(spin_courier.selectedItem.toString())
                }
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {

            }
        }
    }

    override fun getCost(courier: String) {
        val origin = bargain.product!!.city_id!!
        val destination = address[0].city_id!!
        val weight = Integer.valueOf(bargain.total_item!!)
        presenter.getCost(ApiKey.key, origin, destination, weight, courier.lowercase())
    }

    override fun onResultCost(responseRajaOngkirCost: ResponseRajaOngkirCost) {
        val dataResultsCost: List<DataResultsCost> = responseRajaOngkirCost.rajaongkir.results
        val courier = dataResultsCost[0].code!!
        val costs = dataResultsCost[0].costs!!

        showSpinnerCost(courier, costs)
    }

    override fun showSpinnerCost(courier: String, costs: List<DataCosts>) {
        val arrayCost = ArrayList<String>()
        arrayCost.add("Pilih Pengiriman")
        for (cost in costs) {
            arrayCost.add(courier + " " + cost.service + " (" + cost.description + ") ")
        }
        val adapter = ArrayAdapter(this, R.layout.item_spiner, arrayCost.toTypedArray())
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spin_cost.adapter = adapter
        spin_cost.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(p0: AdapterView<*>?, view: View?, position: Int, id: Long) {
                if (position != 0) {
                    setCost(costs[position-1])
                }
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {

            }
        }
    }

    override fun setCost(costs: DataCosts) {
        val cost = costs.cost!![0]
        layout_cost.visibility = View.VISIBLE
        tv_etd.text = cost.etd
        tv_value.text = cost.value.toString()

        setTotal(cost.value!!)
    }

    override fun setTotal(value: Int) {
        val totalExp = bargain.price_offer!!
        val shippingCost = (Integer.valueOf(bargain.total_item!!) * value).toString()
        tv_total_exp.text = CurrencyHelper.changeToRupiah(totalExp)
        tv_shipping_costs.text = CurrencyHelper.changeToRupiah(shippingCost)
        tv_total.text =
            CurrencyHelper.changeToRupiah(Integer.valueOf(totalExp) + Integer.valueOf(shippingCost))
    }

    override fun showMessage(message: String) {
        Toast.makeText(applicationContext, message, Toast.LENGTH_SHORT).show()
    }

}