package com.xenrath.manusiabuahpembeli.ui.bargain.detail

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.ontbee.legacyforks.cn.pedant.SweetAlert.SweetAlertDialog
import com.xenrath.manusiabuahpembeli.R
import com.xenrath.manusiabuahpembeli.data.Constant
import com.xenrath.manusiabuahpembeli.data.database.model.DataBargain
import com.xenrath.manusiabuahpembeli.data.database.model.ResponseBargainDetail
import com.xenrath.manusiabuahpembeli.data.database.model.ResponseBargainUpdate
import com.xenrath.manusiabuahpembeli.ui.delivery.DeliveryActivity
import com.xenrath.manusiabuahpembeli.utils.CurrencyHelper
import com.xenrath.manusiabuahpembeli.utils.GlideHelper
import kotlinx.android.synthetic.main.activity_bargain_detail.*
import kotlinx.android.synthetic.main.toolbar_custom.*

class BargainDetailActivity : AppCompatActivity(), BargainDetailContract.View {

    lateinit var presenter: BargainDetailPresenter

    lateinit var bargain: DataBargain

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bargain_detail)

        presenter = BargainDetailPresenter(this)
    }

    override fun onStart() {
        super.onStart()
        presenter.getBargainDetail(Constant.BARGAIN_ID)
    }

    override fun onDestroy() {
        super.onDestroy()
        Constant.BARGAIN_ID = 0
    }

    @SuppressLint("SetTextI18n")
    override fun initActivity() {
        tv_title.text = "Detail Tawaran"
    }

    override fun initListener() {
        iv_back.setOnClickListener {
            onBackPressed()
        }
        btn_cancel.setOnClickListener {
            SweetAlertDialog(this, SweetAlertDialog.WARNING_TYPE)
                .setTitleText("Konfirmasi")
                .setContentText("Yakin membatalkan tawaran?")
                .setConfirmText("Ya, batalkan")
                .setConfirmClickListener {
                    it.dismissWithAnimation()
                    presenter.updateBargainStatus(bargain.id!!, "Dibatalkan")
                }
                .setCancelText("Tutup")
                .setCancelClickListener {
                    it.dismissWithAnimation()
                }.show()
        }
    }

    override fun onLoadingGet(loading: Boolean) {
        when (loading) {
            true -> progress_line.visibility = View.VISIBLE
            false -> progress_line.visibility = View.GONE
        }
    }

    override fun onLoadingAction(loading: Boolean) {
        val progress = SweetAlertDialog(this, SweetAlertDialog.PROGRESS_TYPE)
        when (loading) {
            true -> progress.show()
            false -> progress.dismiss()
        }
    }

    override fun onResult(responseBargainDetail: ResponseBargainDetail) {
        bargain = responseBargainDetail.bargain
        val product = bargain.product!!
        val user = product.user!!

        tv_status.text = bargain.status
        GlideHelper.setImage(this, product.image!!, iv_product)
        tv_product_name.text = product.name
        tv_price.text = CurrencyHelper.changeToRupiah(bargain.price!!)
        tv_price_offer.text = CurrencyHelper.changeToRupiah(bargain.price_offer!!)
        tv_total_item.text = bargain.total_item
        tv_user_name.text = user.name

        btn_order.setOnClickListener {
            Constant.BARGAIN_ID = bargain.id!!
            startActivity(Intent(this, DeliveryActivity::class.java))
        }

        when (bargain.status) {
            "Menunggu" -> {
                btn_order.visibility = View.GONE
            }
            "Diterima" -> {
                btn_cancel.visibility = View.GONE
            }
            else -> {
                btn_order.visibility = View.GONE
                btn_cancel.visibility = View.GONE
            }
        }
    }

    override fun onResultUpdate(responseBargainUpdate: ResponseBargainUpdate) {
        showMessage(responseBargainUpdate.message)
    }

    override fun showMessage(message: String) {
        Toast.makeText(applicationContext, message, Toast.LENGTH_SHORT).show()
    }
}