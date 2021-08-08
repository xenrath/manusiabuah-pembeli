package com.xenrath.manusiabuahpembeli.ui.profile.update

import android.app.Activity
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.xenrath.manusiabuahpembeli.R
import com.xenrath.manusiabuahpembeli.data.database.PrefManager
import com.xenrath.manusiabuahpembeli.data.database.model.ResponseProfileUpdate
import com.xenrath.manusiabuahpembeli.utils.FileUtils
import com.xenrath.manusiabuahpembeli.utils.GalleryHelper
import com.xenrath.manusiabuahpembeli.utils.GlideHelper
import com.xenrath.manusiabuahpembeli.utils.ToolbarHelper
import kotlinx.android.synthetic.main.activity_profile_update.*

class ProfileUpdateActivity : AppCompatActivity(), ProfileUpdateContract.View {

    lateinit var prefManager: PrefManager
    lateinit var presenter: ProfileUpdatePresenter

    private var uri: Uri? = null
    private var pickImage = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile_update)

        prefManager = PrefManager(this)
        presenter = ProfileUpdatePresenter(this)

    }

    override fun onStart() {
        super.onStart()

        et_name.setText(prefManager.prefName)
        et_email.setText(prefManager.prefEmail)
        et_phone.setText(prefManager.prefPhone)
        et_address.setText(prefManager.prefAddress)

        GlideHelper.setImage(this, prefManager.prefImage, iv_user_image)
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return super.onSupportNavigateUp()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == pickImage && resultCode == Activity.RESULT_OK) {
            uri = data!!.data
            iv_user_image.setImageURI(uri)
        }
    }

    override fun initActivity() {
        ToolbarHelper.setToolbar(this, toolbar, "Perbarui Profile")
    }

    override fun initListener() {
        iv_user_image.setOnClickListener {
            if (GalleryHelper.permissionGallery(this, this, pickImage)) {
                GalleryHelper.openGallery(this)
            }
        }

        btn_save.setOnClickListener {
            val name = et_name.text
            val email = et_email.text
            val phone = et_phone.text
            val address = et_address.text
            if (
                name.isNullOrEmpty() ||
                email.isNullOrEmpty() ||
                phone.isNullOrEmpty()
            ) {
                showMessage("Lengkapi data dengan benar")
            } else {
                presenter.updateProfile(
                    prefManager.prefId,
                    name.toString(),
                    email.toString(),
                    phone.toString(),
                    address.toString(),
                    FileUtils.getFile(this, uri)
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
        showMessage("Profile berhasil diperbarui")
    }

    override fun showMessage(message: String) {
        Toast.makeText(applicationContext, message, Toast.LENGTH_SHORT).show()
    }
}