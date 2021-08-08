package com.xenrath.manusiabuahpembeli.ui.address.add

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import com.xenrath.manusiabuahpembeli.R
import com.xenrath.manusiabuahpembeli.data.Constant
import com.xenrath.manusiabuahpembeli.data.database.PrefManager
import com.xenrath.manusiabuahpembeli.data.database.model.*
import com.xenrath.manusiabuahpembeli.utils.ApiKey
import com.xenrath.manusiabuahpembeli.utils.ToolbarHelper
import kotlinx.android.synthetic.main.activity_add_address.*
import kotlinx.android.synthetic.main.activity_add_address.btn_save
import kotlinx.android.synthetic.main.activity_add_address.et_name
import kotlinx.android.synthetic.main.activity_add_address.progress_line
import kotlinx.android.synthetic.main.activity_add_address.toolbar

class AddAddressActivity : AppCompatActivity(), AddAddressContract.View {

    lateinit var prefManager: PrefManager
    lateinit var presenter: AddAddressPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_address)

        prefManager = PrefManager(this)
        presenter = AddAddressPresenter(this)

    }

    override fun onStart() {
        super.onStart()
        presenter.getProvince(ApiKey.key)
    }

    override fun initActivity() {
        ToolbarHelper.setToolbar(this, toolbar, "Tambah Alamat")
    }

    override fun initListener() {
        btn_save.setOnClickListener {
            val name = et_name.text
            val phone = et_phone.text
            val place = et_place.text
            val address = et_address.text
            val kodePos = et_pos.text
            when {
                name.isEmpty() -> {
                    showMessage("Nama lengkap tidak boleh kosong")
                }
                phone.isEmpty() -> {
                    showMessage("Nomor telepon tidak boleh kosong")
                }
                place.isEmpty() -> {
                    showMessage("Tempat tidak boleh kosong")
                }
                kodePos.isEmpty() -> {
                    showMessage("Kode POS tidak boleh kosong")
                }
            }
            if (Constant.PROVINCE_ID == "0") {
                showMessage("Silahkan pilih provinsi")
            }
            if (Constant.CITY_ID == "0") {
                showMessage("Silahkan pilih kota / kabupaten")
            }
            presenter.insertAddress(
                prefManager.prefId.toString(),
                name.toString(),
                phone.toString(),
                place.toString(),
                address.toString(),
                Constant.PROVINCE_ID,
                Constant.PROVINCE_NAME,
                Constant.CITY_ID,
                Constant.CITY_NAME,
                kodePos.toString(),
            )
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

    override fun onLoadingTerritory(loading: Boolean) {
        when (loading) {
            true -> {
                progress.visibility = View.VISIBLE
            }
            false -> {
                progress.visibility = View.GONE
            }
        }
    }

    override fun onResult(responseAddressUpdate: ResponseAddressUpdate) {
        showMessage(responseAddressUpdate.message)
        finish()
    }

    override fun onResultProvince(responseRajaOngkirTerritory: ResponseRajaOngkirTerritory) {
        cv_province.visibility = View.VISIBLE

        val arrayString = ArrayList<String>()
        arrayString.add("Pilih Provinsi")
        val listProvince = responseRajaOngkirTerritory.rajaongkir.results
        for(prov in listProvince) {
            arrayString.add(prov.province!!)
        }
        val adapter = ArrayAdapter(this, R.layout.item_spiner, arrayString.toTypedArray())
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spin_province.adapter = adapter
        spin_province.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(p0: AdapterView<*>?, view: View?, position: Int, id: Long) {
                if (position != 0) {
                    val idProv = listProvince[position-1].province_id
                    presenter.getCity(ApiKey.key, idProv!!)
                    Constant.PROVINCE_ID = idProv
                    Constant.PROVINCE_NAME = listProvince[position - 1].province!!
                }
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {

            }

        }
    }

    override fun onResultCity(responseRajaOngkirTerritory: ResponseRajaOngkirTerritory) {
        cv_city.visibility = View.VISIBLE

        val arrayString = ArrayList<String>()
        arrayString.add("Pilih Kota / Kabupaten")
        val listDistrict = responseRajaOngkirTerritory.rajaongkir.results
        for(city in listDistrict) {
            arrayString.add(city.type + " " + city.city_name)
        }
        val adapter = ArrayAdapter(this, R.layout.item_spiner, arrayString.toTypedArray())
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spin_district.adapter = adapter
        spin_district.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(p0: AdapterView<*>?, view: View?, position: Int, id: Long) {
                if (position != 0) {
                    val idCity = listDistrict[position-1].city_id!!
                    Constant.CITY_ID = idCity
                    Constant.CITY_NAME = listDistrict[position-1].city_name!!
                    val postalCode = listDistrict[position-1].postal_code
                    et_pos.setText(postalCode)
                }
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {

            }

        }
    }

    override fun showMessage(message: String) {
        Toast.makeText(applicationContext, message, Toast.LENGTH_SHORT).show()
    }
}