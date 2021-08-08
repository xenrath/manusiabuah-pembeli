package com.xenrath.manusiabuahpembeli.ui.profile

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.fragment.app.Fragment
import com.xenrath.manusiabuahpembeli.R
import com.xenrath.manusiabuahpembeli.data.database.PrefManager
import com.xenrath.manusiabuahpembeli.ui.login.LoginActivity
import com.xenrath.manusiabuahpembeli.ui.product.ProductActivity
import com.xenrath.manusiabuahpembeli.ui.profile.changepassword.ChangePasswordActivity
import com.xenrath.manusiabuahpembeli.ui.profile.update.ProfileUpdateActivity
import com.xenrath.manusiabuahpembeli.utils.GlideHelper

class ProfileFragment: Fragment(), ProfileContract.View {

    lateinit var prefManager: PrefManager
    lateinit var presenter: ProfilePresenter

    private lateinit var btnLogout: TextView
    private lateinit var ivProfile: ImageView
    private lateinit var tvName: TextView
    private lateinit var tvEmail: TextView
    private lateinit var btnUpdateProfile: TextView
    private lateinit var btnUpdatePassword: TextView
    private lateinit var btnHistoryTransaction: TextView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        val view: View = inflater.inflate(R.layout.fragment_profile, container, false)

        prefManager = PrefManager(requireActivity())
        presenter = ProfilePresenter(this)

        initListener(view)

        presenter.doLogin(prefManager)

        return view
    }

    @SuppressLint("SetTextI18n")
    override fun initListener(view: View) {
        val ivBack = view.findViewById<ImageView>(R.id.iv_back)
        val tvTitle = view.findViewById<TextView>(R.id.tv_title)
        val ivHelp = view.findViewById<ImageView>(R.id.iv_help)

        btnLogout = view.findViewById(R.id.btn_logout)
        ivProfile = view.findViewById(R.id.iv_profile)
        tvName = view.findViewById(R.id.tv_name)
        tvEmail = view.findViewById(R.id.tv_email)
        btnUpdateProfile = view.findViewById(R.id.btn_update_profile)
        btnUpdatePassword = view.findViewById(R.id.btn_update_password)
        btnHistoryTransaction = view.findViewById(R.id.btn_history_transaction)

        tvTitle.text = "Profile"
        ivBack.visibility = View.GONE
        ivHelp.setOnClickListener {

        }

        btnUpdateProfile.setOnClickListener {
            startActivity(Intent(requireActivity(), ProfileUpdateActivity::class.java))
        }
        btnUpdatePassword.setOnClickListener {
            startActivity(Intent(requireActivity(), ChangePasswordActivity::class.java))
        }
        btnHistoryTransaction.setOnClickListener {
            startActivity(Intent(requireActivity(), ProductActivity::class.java))
        }
        btnLogout.setOnClickListener {
            presenter.doLogout(prefManager)
        }
    }

    override fun onResultLogin(prefManager: PrefManager) {
        tvName.text = prefManager.prefName
        tvEmail.text = prefManager.prefEmail
        if (prefManager.prefImage != "") {
            GlideHelper.setImage(requireActivity(), prefManager.prefImage, ivProfile)
        }
    }

    override fun onResultLogout() {
        requireActivity().finish()
        startActivity(Intent(requireActivity(), LoginActivity::class.java))
    }

    override fun showMessage(message: String) {
        Toast.makeText(requireActivity().applicationContext, message, Toast.LENGTH_SHORT).show()
    }

}