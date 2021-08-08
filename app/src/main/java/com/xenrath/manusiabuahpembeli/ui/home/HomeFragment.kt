package com.xenrath.manusiabuahpembeli.ui.home

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.widget.EditText
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.xenrath.manusiabuahpembeli.R
import com.xenrath.manusiabuahpembeli.data.ResponseProduct
import com.xenrath.manusiabuahpembeli.data.database.PrefManager
import com.xenrath.manusiabuahpembeli.data.database.model.DataProduct
import com.xenrath.manusiabuahpembeli.data.database.model.ResponseProductList
import kotlinx.android.synthetic.main.fragment_product.*

class HomeFragment : Fragment(), HomeContract.View {

    lateinit var presenter: HomePresenter
    lateinit var prefManager: PrefManager

    private lateinit var homeAdapter: HomeAdapter
    private lateinit var progressLine: ProgressBar
    private lateinit var etSeach: EditText

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        val view: View = inflater.inflate(R.layout.fragment_product, container, false)

        presenter = HomePresenter(this)
        prefManager = PrefManager(requireActivity())

        initListener(view)

        return view
    }

    override fun onStart() {
        super.onStart()
        presenter.getProduct(prefManager.prefId.toString(), "Eceran")
    }

    @SuppressLint("SetTextI18n")
    override fun initListener(view: View) {
        val ivBack = view.findViewById<ImageView>(R.id.iv_back)
        val tvTitle = view.findViewById<TextView>(R.id.tv_title)
        val ivHelp = view.findViewById<ImageView>(R.id.iv_help)
        val rvProduct = view.findViewById<RecyclerView>(R.id.rv_product)

        progressLine = view.findViewById(R.id.progress_line)
        etSeach = view.findViewById(R.id.et_search)

        tvTitle.text = "Home"
        ivBack.visibility = View.GONE
        ivHelp.visibility = View.GONE

        homeAdapter = HomeAdapter(requireActivity(), ArrayList())
        rvProduct.apply {
            layoutManager = GridLayoutManager(requireActivity(), 2)
            adapter = homeAdapter
        }

        etSeach.setOnEditorActionListener { _, i, _ ->
            if (i == EditorInfo.IME_ACTION_SEARCH) {
                presenter.searchProduct(et_search.text.toString())
                true
            } else {
                false
            }
        }
    }

    override fun onResultList(responseProductList: ResponseProductList) {
        val dataProduct: List<DataProduct> = responseProductList.dataProduct
        homeAdapter.setData(dataProduct)
    }

    override fun onResultSearch(responseProduct: ResponseProduct) {
        val dataProduct: List<DataProduct> = responseProduct.product
        homeAdapter.setData(dataProduct)
    }

    override fun onLoading(loading: Boolean) {
        when(loading) {
            true -> progressLine.visibility = View.VISIBLE
            false -> progressLine.visibility = View.GONE
        }
    }

}