package com.xenrath.manusiabuahpembeli.ui.user

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.xenrath.manusiabuahpembeli.R
import com.xenrath.manusiabuahpembeli.data.database.model.ResponseUser
import com.xenrath.manusiabuahpembeli.utils.GlideHelper
import kotlinx.android.synthetic.main.activity_user.*
import kotlinx.android.synthetic.main.toolbar_custom.*

class UserActivity : AppCompatActivity(), UserContract.View {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user)
    }

    @SuppressLint("SetTextI18n")
    override fun initActivity() {
        tv_title.text = "Detail Penjual"
    }

    override fun initListener() {
        iv_back.setOnClickListener {
            onBackPressed()
        }
        iv_help.setOnClickListener {

        }
    }

    override fun onLoading(loading: Boolean) {
        when(loading) {
            true -> progress_line.visibility = View.VISIBLE
            false -> progress_line.visibility = View.GONE
        }
    }

    override fun onResult(responseUser: ResponseUser) {
        val user = responseUser.user

        GlideHelper.setImage(this, user.image!!, iv_image)
        tv_name.text = user.name
        tv_status.text = user.level
        tv_email.text = user.email
        tv_phone.text = user.phone
        tv_address.text = user.address

    }

    override fun showMessage(message: String) {
        Toast.makeText(applicationContext, message, Toast.LENGTH_SHORT).show()
    }
}