package com.jyotirmayg.mytestproject.view.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.jyotirmayg.mytestproject.R
import com.jyotirmayg.mytestproject.databinding.ActivityMainBinding
import com.jyotirmayg.mytestproject.view.login.LoginViewModel
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.kodein
import org.kodein.di.android.x.kodein
import org.kodein.di.generic.instance

/**
 * @author jyoti
 * @created on 14-05-2022
 * This class is responsible for creating Main Activity.
 */
class MainActivity : AppCompatActivity(), KodeinAware {

    override val kodein: Kodein by kodein()
    private val factory: MainViewModelFactory by instance()
    private lateinit var viewModel: MainViewModel

    private fun navController() = findNavController(R.id.fragment)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val mBinding = DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main)
        viewModel = ViewModelProvider(this, factory)[MainViewModel::class.java]

        viewModel.mainNavigationFlow.observe(this) { route ->
            mBinding?.let {
                when (route) {
                    Route.ADD_ITEM -> navController().navigate(R.id.action_homeFragment_to_addItemFragment)
                    Route.BACK -> navigationUp()
                    else -> {}
                }
            }
        }
    }

    /**
     * This function will helps to pops up fragment.
     * */
    private fun navigationUp() {
        navController().popBackStack()
    }
}