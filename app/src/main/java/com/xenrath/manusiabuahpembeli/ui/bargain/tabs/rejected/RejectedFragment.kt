package com.xenrath.manusiabuahpembeli.ui.bargain.tabs.rejected

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.xenrath.manusiabuahpembeli.R
import com.xenrath.manusiabuahpembeli.data.database.PrefManager
import com.xenrath.manusiabuahpembeli.data.database.model.DataBargain
import com.xenrath.manusiabuahpembeli.data.database.model.ResponseBargainList
import com.xenrath.manusiabuahpembeli.ui.bargain.BargainAdapter

class RejectedFragment : Fragment(), RejectedContract.View {

    lateinit var prefManager: PrefManager
    lateinit var presenter: RejectedPresenter

    private lateinit var bargainAdapter: BargainAdapter
    lateinit var progressBar: ProgressBar

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_rejected, container, false)

        prefManager = PrefManager(requireActivity())
        presenter = RejectedPresenter(this)

        initFragment(view)

        return view
    }

    override fun onStart() {
        super.onStart()
        presenter.getBargainRejected(prefManager.prefId.toString(), "Ditolak")
    }

    override fun initFragment(view: View) {
        bargainAdapter = BargainAdapter(requireActivity(), ArrayList())
        progressBar = view.findViewById(R.id.progress_bar)

        val rvBargain = view.findViewById<RecyclerView>(R.id.rv_bargain)
        val layoutManager = LinearLayoutManager(activity)
        layoutManager.orientation = LinearLayoutManager.VERTICAL
        rvBargain.adapter = bargainAdapter
        rvBargain.layoutManager = layoutManager
    }

    override fun onLoading(loading: Boolean) {
        when(loading) {
            true -> progressBar.visibility = View.VISIBLE
            false -> progressBar.visibility = View.GONE
        }
    }

    override fun onResult(responseBargainList: ResponseBargainList) {
        val dataBargain: List<DataBargain> = responseBargainList.bargains
        bargainAdapter.setData(dataBargain)
    }

}