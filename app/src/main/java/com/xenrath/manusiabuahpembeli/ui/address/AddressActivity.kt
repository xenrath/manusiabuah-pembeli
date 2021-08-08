package com.xenrath.manusiabuahpembeli.ui.address

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.xenrath.manusiabuahpembeli.R
import com.xenrath.manusiabuahpembeli.data.database.PrefManager
import com.xenrath.manusiabuahpembeli.data.database.model.DataAddress
import com.xenrath.manusiabuahpembeli.data.database.model.ResponseAddressList
import com.xenrath.manusiabuahpembeli.data.database.model.ResponseAddressUpdate
import com.xenrath.manusiabuahpembeli.ui.address.add.AddAddressActivity
import com.xenrath.manusiabuahpembeli.utils.ToolbarHelper
import kotlinx.android.synthetic.main.activity_address.*

class AddressActivity : AppCompatActivity(), AddressContract.View {

    lateinit var prefManager: PrefManager
    lateinit var presenter: AddressPresenter

    private lateinit var addressAdapter: AddressAdapter
    lateinit var address: DataAddress

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_address)

        prefManager = PrefManager(this)
        presenter = AddressPresenter(this)
    }

    override fun onStart() {
        super.onStart()
        presenter.getAddress(prefManager.prefId.toString())
    }

    override fun onResume() {
        super.onResume()
        presenter.getAddress(prefManager.prefId.toString())
    }

    override fun initActivity() {
        ToolbarHelper.setToolbar(this, toolbar, "Tambah Alamat")

        val layoutManager = LinearLayoutManager(applicationContext)
        layoutManager.orientation = LinearLayoutManager.VERTICAL

        addressAdapter = AddressAdapter(this, arrayListOf())
        {
            dataAddress: DataAddress ->
            address = dataAddress
            presenter.checkAddress(address.id!!, prefManager.prefId.toString())
            onBackPressed()
        }
        rv_address.adapter = addressAdapter
        rv_address.layoutManager = layoutManager
    }

    override fun initListener() {
        btn_add_address.setOnClickListener {
            startActivity(Intent(this, AddAddressActivity::class.java))
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

    override fun onResultList(responseAddressList: ResponseAddressList) {
        val dataAddress: List<DataAddress> = responseAddressList.address
        if (dataAddress.isEmpty()){
            text_empty.visibility = View.VISIBLE
            rv_address.visibility = View.GONE
        }
        addressAdapter.setData(dataAddress)
    }

    override fun onResultChoice(responseAddressUpdate: ResponseAddressUpdate) {
        onBackPressed()
    }

    override fun showMessage(message: String) {
        Toast.makeText(applicationContext, message, Toast.LENGTH_SHORT).show()
    }
}