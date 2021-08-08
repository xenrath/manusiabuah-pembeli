package com.xenrath.manusiabuahpembeli.ui.notification

import android.content.Intent
import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.Toast
import androidx.annotation.RequiresApi
import com.xenrath.manusiabuahpembeli.R
import com.xenrath.manusiabuahpembeli.data.database.PrefManager
import com.xenrath.manusiabuahpembeli.data.database.model.DataBargain
import com.xenrath.manusiabuahpembeli.data.database.model.ResponseBargainList
import com.xenrath.manusiabuahpembeli.data.database.model.ResponseBargainUpdate
import com.xenrath.manusiabuahpembeli.ui.bargain.BargainActivity

class NotificationFragment : Fragment(), NotificationContract.View {

    lateinit var prefManager: PrefManager
    lateinit var presenter: NotificationPresenter

    private lateinit var btnBargain: LinearLayout

    lateinit var bargain: DataBargain

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        val view: View = inflater.inflate(R.layout.fragment_notification1, container, false)

        prefManager = PrefManager(requireActivity())
        presenter = NotificationPresenter(this)

        initFragment(view)

        return view
    }

    @RequiresApi(Build.VERSION_CODES.M)
    override fun initFragment(view: View) {
        btnBargain = view.findViewById(R.id.btn_bargain)
        btnBargain.setOnClickListener {
            startActivity(Intent(requireActivity(), BargainActivity::class.java))
        }

//        btnOffer = view.findViewById(R.id.btn_offer)
//        btnOfferHistory = view.findViewById(R.id.btn_offer_history)
//        swipeRefresh = view.findViewById(R.id.swipe_refresh)
//        rvWaiting = view.findViewById(R.id.rv_waiting)
//        rvHistory = view.findViewById(R.id.rv_history)
//        if (status == 1) {
//            val active = requireActivity().getColor(R.color.fragmentNotification)
//            val nonactive = requireActivity().getColor(R.color.transparent)
//            btnOffer.setTextColor(nonactive)
//            btnOfferHistory.setTextColor(active)
//            rvWaiting.visibility = View.GONE
//            rvHistory.visibility = View.VISIBLE
//        } else if (status == 2) {
//            val active = requireActivity().getColor(R.color.fragmentNotification)
//            val nonactive = requireActivity().getColor(R.color.transparent)
//            btnOfferHistory.setTextColor(nonactive)
//            btnOffer.setTextColor(active)
//            rvWaiting.visibility = View.VISIBLE
//            rvHistory.visibility = View.GONE
//        }
//
//        btnOffer.setOnClickListener {
//            status = 2
//            presenter.getOfferWaiting(prefManager.prefId.toString())
//        }
//        btnOfferHistory.setOnClickListener {
//            status = 1
//            presenter.getOfferHistory(prefManager.prefId.toString())
//        }
//
//        val layoutManagerWaiting = LinearLayoutManager(activity)
//        layoutManagerWaiting.orientation = LinearLayoutManager.VERTICAL
//        notificationAdapter = NotificationAdapter(requireActivity(), arrayListOf())
//        {
//                dataBargain: DataBargain, position: Int, type: String ->
//            bargain = dataBargain
//            when (type) {
//                "reject" -> showDialogReject(dataBargain, position)
//                "accept" -> showDialogReject(dataBargain, position)
//            }
//        }
//        rvWaiting.adapter = notificationAdapter
//        rvWaiting.layoutManager = layoutManagerWaiting
//
//        val layoutManagerHistory = LinearLayoutManager(activity)
//        layoutManagerHistory.orientation = LinearLayoutManager.VERTICAL
//        notificationHistoryAdapter = NotificationHistoryAdapter(requireActivity(), ArrayList())
//        rvHistory.adapter = notificationHistoryAdapter
//        rvHistory.layoutManager = layoutManagerHistory
//
//        swipeRefresh.setOnRefreshListener {
//            presenter.getOfferWaiting(prefManager.prefId.toString())
//        }
    }

    override fun initListener() {

    }

    override fun onLoading(loading: Boolean) {
//        when(loading) {
//            true -> swipeRefresh.isRefreshing = true
//            false -> swipeRefresh.isRefreshing = false
//        }
    }

    override fun onResultListWaiting(responseBargainList: ResponseBargainList) {
//        val dataBargain: List<DataBargain> = responseBargainList.bargains
//        notificationAdapter.setData(dataBargain)
    }

    override fun onResultListHistory(responseBargainList: ResponseBargainList) {
//        val dataBargain: List<DataBargain> = responseBargainList.bargains
//        notificationHistoryAdapter.setData(dataBargain)
    }

    override fun onResultUpdate(responseBargainUpdate: ResponseBargainUpdate) {
//        showMessage(responseBargainUpdate.message)
    }

    override fun showDialogReject(dataBargain: DataBargain, position: Int) {
//        val dialog = AlertDialog.Builder(requireActivity())
//        dialog.setTitle("Konfirmasi")
//        dialog.setMessage("Tolak tawaran dari ${bargain.user_name}?")
//        dialog.setPositiveButton("Tolak") { dialog, _ ->
//            presenter.offerReject(Constant.BARGAIN_ID)
//            notificationAdapter.removeProduct(position)
//            dialog.dismiss()
//        }
//        dialog.setNegativeButton("Batal") { dialog, _ ->
//            dialog.dismiss()
//        }
//        dialog.show()
    }

    override fun showDialogAccept(dataBargain: DataBargain, position: Int) {
//        val dialog = AlertDialog.Builder(requireActivity())
//        dialog.setTitle("Konfirmasi")
//        dialog.setMessage("Terima tawaran dari ${bargain.user_name}?")
//        dialog.setPositiveButton("Terima") { dialog, _ ->
//            presenter.offerAccept(Constant.BARGAIN_ID)
//            notificationAdapter.removeProduct(position)
//            dialog.dismiss()
//        }
//        dialog.setNegativeButton("Batal") { dialog, _ ->
//            dialog.dismiss()
//        }
//        dialog.show()
    }

    override fun showMessage(message: String) {
        Toast.makeText(requireActivity().applicationContext, message, Toast.LENGTH_SHORT).show()
    }

}