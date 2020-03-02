package com.dimache.yarabatman.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentManager
import com.dimache.yarabatman.R
import com.dimache.yarabatman.base.BaseFragment
import com.dimache.yarabatman.databinding.FragmentDetailBinding
import kotlinx.android.synthetic.main.fragment_detail.*
import kotlinx.android.synthetic.main.item_movies.*
import kotlinx.android.synthetic.main.item_movies.txt_title
import org.koin.androidx.viewmodel.ext.android.viewModel

class DetailFragment : BaseFragment()
{

    val detailViewModel : DetailViewModel by viewModel()

    lateinit var viewBinding : FragmentDetailBinding

    var imdbId = "0"
    companion object{
        @JvmStatic
        fun newInstance(id : String) = DetailFragment().apply {
            imdbId = id
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_detail,container,false)
        viewBinding = FragmentDetailBinding.bind(view).apply {
              detail = detailViewModel
        }
        return view
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        txt_title.isSelected = true
        detailViewModel.fetchDetail(imdbId)


        back.setOnClickListener {
            activity?.onBackPressed()
        }

    }


}