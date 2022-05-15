package com.jyotirmayg.mytestproject.view.main.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModelProvider
import com.jyotirmayg.mytestproject.R
import com.jyotirmayg.mytestproject.data.db.entities.Item
import com.jyotirmayg.mytestproject.databinding.HomeFragmentBinding
import com.jyotirmayg.mytestproject.view.main.MainViewModel
import com.jyotirmayg.mytestproject.view.main.MainViewModelFactory
import com.jyotirmayg.mytestproject.view.main.addItem.AddItemFragment
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.kodein
import org.kodein.di.generic.instance

/**
 * @author jyoti
 * @created on 14-05-2022
 */
class HomeFragment : Fragment(), KodeinAware {

    override val kodein: Kodein by kodein()
    private val factory: HomeModelFactory by instance()
    private var viewModel: HomeViewModel? = null
    private val mainViewModel: MainViewModel by activityViewModels()

    private var mBinding: HomeFragmentBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.home_fragment, container, false)
        return mBinding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this, factory)[HomeViewModel::class.java]
        mBinding?.let {
            it.mainviewmodel = mainViewModel
            it.executePendingBindings()
        }
    }

    override fun onResume() {
        super.onResume()

        CoroutineScope(Dispatchers.Main).launch {
            fetchBusRoutes()
        }
    }

    /**
     * This method will be called to get the updated data from the database.
     * */
    private suspend fun fetchBusRoutes() {
        val itemLiveData = viewModel?.getItemList()
        itemLiveData?.observe(viewLifecycleOwner) { itemList ->
            mBinding?.recycleView.let {
                it?.adapter = ItemAdapter(itemList)
            }
        }
    }

}