package com.xenrath.manusiabuahpembeli.ui.home.detail

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.ontbee.legacyforks.cn.pedant.SweetAlert.SweetAlertDialog
import com.xenrath.manusiabuahpembeli.R
import com.xenrath.manusiabuahpembeli.data.Constant
import com.xenrath.manusiabuahpembeli.data.DataUser
import com.xenrath.manusiabuahpembeli.data.database.PrefManager
import com.xenrath.manusiabuahpembeli.data.database.model.DataProduct
import com.xenrath.manusiabuahpembeli.data.database.model.ResponseBargain
import com.xenrath.manusiabuahpembeli.data.database.model.ResponseProductDetail
import com.xenrath.manusiabuahpembeli.ui.user.UserActivity
import com.xenrath.manusiabuahpembeli.utils.CurrencyHelper
import com.xenrath.manusiabuahpembeli.utils.GlideHelper
import kotlinx.android.synthetic.main.activity_detail_product.*
import kotlinx.android.synthetic.main.layout_bottom_sheet.view.*
import kotlinx.android.synthetic.main.layout_map.view.*
import kotlinx.android.synthetic.main.toolbar_custom.*

class HomeDetailActivity : AppCompatActivity(), HomeDetailContract.View, OnMapReadyCallback {

    lateinit var prefManager: PrefManager
    private lateinit var presenterHome: HomeDetailPresenter
    lateinit var product: DataProduct
    private lateinit var user: DataUser

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_product)

        presenterHome = HomeDetailPresenter(this)
        prefManager = PrefManager(this)
    }

    override fun onStart() {
        super.onStart()
        presenterHome.getDetail(Constant.PRODUCT_ID)
    }

    override fun onDestroy() {
        super.onDestroy()
        Constant.PRODUCT_ID = 0
    }

    @SuppressLint("SetTextI18n")
    override fun initActivity() {
        tv_title.text = "Detail Produk"
    }

    override fun initListener() {
        iv_back.setOnClickListener {
            onBackPressed()
        }
        layout_profile_user.setOnClickListener {
            startActivity(Intent(this, UserActivity::class.java))
        }
    }

    override fun onLoadingDetail(loading: Boolean) {
        when (loading) {
            true -> progress_line.visibility = View.VISIBLE
            false -> progress_line.visibility = View.GONE
        }
    }

    @SuppressLint("InflateParams")
    override fun onLoadingBottomSheet(loading: Boolean) {
        val view = layoutInflater.inflate(R.layout.layout_bottom_sheet, null)
        when (loading) {
            true -> view.progress_bar.visibility = View.VISIBLE
            false -> view.progress_bar.visibility = View.GONE
        }
    }

    override fun onResultDetail(responseProductDetail: ResponseProductDetail) {
        product = responseProductDetail.product
        user = product.user!!

        GlideHelper.setImage(this, product.image!!, iv_product_image)
        if (user.image != null) {
            GlideHelper.setImage(this, user.image!!, iv_user_image)
        }
        tv_user_name.text = user.name
        tv_user_level.text = user.level
        tv_name.text = product.name
        tv_price.text = CurrencyHelper.changeToRupiah(product.price!!)
        tv_description.text = product.description

        btn_buy.setOnClickListener {
            showDialogBuy(product)
        }
        btn_bargain.setOnClickListener {
            showDialogBargain(product)
        }
        btn_location.setOnClickListener {
            showDialogLocation(product)
        }
    }

    override fun onResultBargain(responseBargain: ResponseBargain) {
        SweetAlertDialog(this, SweetAlertDialog.SUCCESS_TYPE)
            .setTitleText("Berhasil")
            .setContentText(responseBargain.message)
            .show()
    }

    @SuppressLint("InflateParams", "SetTextI18n")
    override fun showDialogBuy(dataProduct: DataProduct) {
        val dialog = BottomSheetDialog(this, R.style.BottomSheetDialogTheme)
        val view = layoutInflater.inflate(R.layout.layout_bottom_sheet, null)

        val price = Integer.valueOf(dataProduct.price!!)
        var totalItem = 1

        view.tv_title.text = "Beli Produk"
        view.tv_stock.text = dataProduct.stock
        view.tv_real.visibility = View.GONE
        view.tv_price.text = CurrencyHelper.changeToRupiah(price * totalItem)

        view.tv_total_item.text = totalItem.toString()
        view.iv_remove.setOnClickListener {
            if (totalItem <= 1) return@setOnClickListener
            totalItem--
            view.tv_total_item.text = totalItem.toString()
            view.tv_price.text = CurrencyHelper.changeToRupiah(price * totalItem)
        }
        view.iv_add.setOnClickListener {
            if (totalItem >= Integer.valueOf(dataProduct.stock!!)) return@setOnClickListener
            totalItem++
            view.tv_total_item.text = totalItem.toString()
            view.tv_price.text = CurrencyHelper.changeToRupiah(price * totalItem)
        }

        view.layout_bargain.visibility = View.GONE
        view.btn_bargain.visibility = View.GONE
        view.view_bottom.visibility = View.GONE
        view.btn_buy.setOnClickListener {
            Toast.makeText(this, "Beli Produk Sekarang", Toast.LENGTH_SHORT).show()
        }
        dialog.setContentView(view)
        dialog.show()
    }

    @SuppressLint("InflateParams", "SetTextI18n")
    override fun showDialogBargain(dataProduct: DataProduct) {
        val dialog = BottomSheetDialog(this, R.style.BottomSheetDialogTheme)
        val view = layoutInflater.inflate(R.layout.layout_bottom_sheet, null)

        val price = Integer.valueOf(dataProduct.price!!)
        var totalItem = 1

        view.tv_title.text = "Coba Tawar"
        view.tv_stock.text = dataProduct.stock
        view.tv_price.text = CurrencyHelper.changeToRupiah(price * totalItem)

        view.tv_total_item.text = totalItem.toString()
        view.iv_remove.setOnClickListener {
            if (totalItem <= 1) return@setOnClickListener
            totalItem--
            view.tv_total_item.text = totalItem.toString()
            view.tv_price.text = CurrencyHelper.changeToRupiah(price * totalItem)
        }
        view.iv_add.setOnClickListener {
            if (totalItem >= Integer.valueOf(dataProduct.stock!!)) return@setOnClickListener
            totalItem++
            view.tv_total_item.text = totalItem.toString()
            view.tv_price.text = CurrencyHelper.changeToRupiah(price * totalItem)
        }

        view.btn_buy.visibility = View.GONE
        view.btn_bargain.setOnClickListener {
            val priceReal = price * totalItem
            val priceOffer = view.et_price_offer.text
            val stockItem = view.tv_total_item.text
            if (
                priceOffer.isNullOrEmpty()
            ) {
                showMessage("Harga penawaran harus di isi")
            } else {
                presenterHome.bargainProduct(
                    prefManager.prefId.toString(),
                    dataProduct.id.toString(),
                    priceReal.toString(),
                    priceOffer.toString(),
                    stockItem.toString(),
                    "Menunggu"
                )
            }
        }
        dialog.setContentView(view)
        dialog.show()
    }

    @SuppressLint("InflateParams")
    override fun showDialogLocation(dataProduct: DataProduct) {
        val dialog = BottomSheetDialog(this, R.style.BottomSheetDialogTheme)
        val view = layoutInflater.inflate(R.layout.layout_map, null)

        view.tv_location.text = dataProduct.address
        val mapFragment = supportFragmentManager.findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)

        dialog.setContentView(view)
        dialog.show()
    }

    override fun showMessage(message: String) {
        Toast.makeText(applicationContext, message, Toast.LENGTH_SHORT).show()
    }

    override fun onMapReady(googleMap: GoogleMap) {
        val latLng = LatLng(product.latitude!!.toDouble(), product.longitude!!.toDouble())
        googleMap.addMarker(
            MarkerOptions().position(latLng).title("${product.name} - ${user.name}")
        )
        googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 12f))
    }

}