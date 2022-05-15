package com.jyotirmayg.mytestproject.view.main.addItem

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModelProvider
import com.jyotirmayg.mytestproject.R
import com.jyotirmayg.mytestproject.databinding.FragmentAddItemBinding
import com.jyotirmayg.mytestproject.view.main.MainViewModel
import com.jyotirmayg.mytestproject.view.main.Route
import com.jyotirmayg.mytestproject.view.main.home.HomeModelFactory
import com.jyotirmayg.mytestproject.view.main.home.HomeViewModel
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.kodein
import org.kodein.di.generic.instance

/**
 * @author jyoti
 * @created on 14-05-2022
 * The [AddItemFragment] class will be used to accept and add item to the database.
 */
class AddItemFragment : Fragment(), KodeinAware {

    override val kodein: Kodein by kodein()
    private val factory: AddItemModelFactory by instance()
    private var viewModel: AddItemViewModel? = null
    private val mainViewModel: MainViewModel by activityViewModels()

    private var mBinding: FragmentAddItemBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_add_item, container, false)
        return mBinding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this, factory)[AddItemViewModel::class.java]
        mBinding?.let {
            it.addviewmodel = viewModel
            it.mainviewmodel = mainViewModel
            it.executePendingBindings()
        }

        viewModel?.addItemFlow?.observe(viewLifecycleOwner) {
            if (it > 0) {
                Toast.makeText(requireContext(), "Item added successfully", Toast.LENGTH_SHORT)
                    .show()
                mainViewModel.routeTo(Route.BACK)
            }
        }
    }
}