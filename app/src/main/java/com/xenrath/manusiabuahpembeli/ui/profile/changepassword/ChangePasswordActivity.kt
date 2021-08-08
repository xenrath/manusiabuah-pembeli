package com.xenrath.manusiabuahpembeli.ui.profile.changepassword

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.xenrath.manusiabuahpembeli.R
import com.xenrath.manusiabuahpembeli.data.database.PrefManager
import com.xenrath.manusiabuahpembeli.data.database.model.ResponseProfileUpdate
import com.xenrath.manusiabuahpembeli.utils.ToolbarHelper
import kotlinx.android.synthetic.main.activity_change_password.*

class ChangePasswordActivity : AppCompatActivity(), ChangePasswordContract.View {

    lateinit var prefManager: PrefManager
    lateinit var presenter: ChangePasswordPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_change_password)

        prefManager = PrefManager(this)
        presenter = ChangePasswordPresenter(this)

    }

    override fun initActivity() {
        ToolbarHelper.setToolbar(this, toolbar, "Perbarui Password")
    }

    override fun initListener() {
        btn_save.setOnClickListener {
            val password = et_password.text
            val passwordConfirmation = et_password_confirmation.text
            if (
                password.isNullOrEmpty() ||
                passwordConfirmation.isNullOrEmpty()
            ) {
                showMessage("Kolom tidak boleh kosong")
            } else {
                presenter.updatePassword(
                    prefManager.prefId,
                    password.toString(),
                    passwordConfirmation.toString()
                )
            }
        }
    }

    override fun onLoading(loading: Boolean) {
        when(loading) {
            true -> progress_line.visibility = View.VISIBLE
            false -> progress_line.visibility = View.GONE
        }
    }

    override fun onResult(responseProfileUpdate: ResponseProfileUpdate) {
        showMessage(responseProfileUpdate.message)
    }

    override fun showMessage(message: String) {
        Toast.makeText(applicationContext, message, Toast.LENGTH_SHORT).show()
    }
}