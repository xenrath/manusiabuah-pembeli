package com.xenrath.manusiabuahpembeli.ui.bargain

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.xenrath.manusiabuahpembeli.R
import com.xenrath.manusiabuahpembeli.ui.bargain.tabs.accepted.AcceptedFragment
import com.xenrath.manusiabuahpembeli.ui.bargain.tabs.canceled.CanceledFragment
import com.xenrath.manusiabuahpembeli.ui.bargain.tabs.done.DoneFragment
import com.xenrath.manusiabuahpembeli.ui.bargain.tabs.rejected.RejectedFragment
import com.xenrath.manusiabuahpembeli.ui.bargain.tabs.waiting.WaitingFragment
import kotlinx.android.synthetic.main.activity_bargain.*
import kotlinx.android.synthetic.main.toolbar_custom.*

class BargainActivity : AppCompatActivity(), BargainContract.View {

    lateinit var presenter: BargainPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bargain)

        presenter = BargainPresenter(this)
    }

    @SuppressLint("SetTextI18n")
    override fun initActivity() {
        tv_title.text = "Tawaran"

        val adapter = ViewPagerAdapter(supportFragmentManager)
        adapter.addFragment(WaitingFragment())
        adapter.addFragment(RejectedFragment())
        adapter.addFragment(AcceptedFragment())
        adapter.addFragment(CanceledFragment())
        adapter.addFragment(DoneFragment())
        view_pager.adapter = adapter
        tabs.setupWithViewPager(view_pager)

        tabs.getTabAt(0)!!.setIcon(R.drawable.ic_baseline_access_time_24)
        tabs.getTabAt(1)!!.setIcon(R.drawable.ic_outline_cancel_24)
        tabs.getTabAt(2)!!.setIcon(R.drawable.ic_outline_check_circle_24)
        tabs.getTabAt(3)!!.setIcon(R.drawable.ic_baseline_not_interested_24)
        tabs.getTabAt(4)!!.setIcon(R.drawable.ic_baseline_task_alt_24)
    }

    override fun initListener() {
        iv_back.setOnClickListener {
            onBackPressed()
        }
    }
}