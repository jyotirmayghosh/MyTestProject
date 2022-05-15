package com.jyotirmayg.mytestproject.view.login

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.biometric.BiometricManager
import androidx.biometric.BiometricManager.Authenticators.BIOMETRIC_WEAK
import androidx.biometric.BiometricPrompt
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.jyotirmayg.mytestproject.view.main.MainActivity
import com.jyotirmayg.mytestproject.R
import com.jyotirmayg.mytestproject.databinding.ActivityLoginBinding
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.kodein
import org.kodein.di.generic.instance

/**
 * @author jyoti
 * @created on 14-05-2022
 * This class is responsible for creating Login Activity.
 */
class LoginActivity : AppCompatActivity(), KodeinAware {

    override val kodein: Kodein by kodein()
    private val factory: LoginViewModelFactory by instance()
    private lateinit var viewModel: LoginViewModel

    private var mBinding: ActivityLoginBinding? = null

    private lateinit var biometricPrompt: BiometricPrompt
    private lateinit var biometricManager: BiometricManager


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel = ViewModelProvider(this, factory)[LoginViewModel::class.java]

        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_login)
        mBinding?.let {
            it.viewmodel = viewModel
            it.executePendingBindings()
        }

        setupBiometricAuthentication()
        doBiometricLogin()

        mBinding?.txtAuthenticate?.setOnClickListener {
            doBiometricLogin()
        }

        viewModel.loginStatus.observe(this) {
            if (it.isLoginSuccess) {
                navigateToHome()
            }
            Toast.makeText(this, it.message, Toast.LENGTH_SHORT).show()
        }
    }

    /**
     * Initialize BiometricManager and BiometricPrompt.
     * */
    private fun setupBiometricAuthentication() {
        biometricManager = BiometricManager.from(this)
        val executor = ContextCompat.getMainExecutor(this)
        biometricPrompt = BiometricPrompt(this, executor, biometricCallback)
    }

    /**
     * This checks if the biometric hardware is present.
     * This function prompt for biometric auth login otherwise throw an error message.
     * */
    private fun doBiometricLogin() {
        if (biometricManager.canAuthenticate(BIOMETRIC_WEAK) == BiometricManager.BIOMETRIC_SUCCESS) {
            biometricPrompt.authenticate(buildBiometricPrompt())
        } else {
            mBinding?.txtAuthenticate?.apply {
                isEnabled = false
                setTextColor(ContextCompat.getColor(context, R.color.gray_200))
            }
            mBinding?.txtError?.text = getString(R.string.error_no_biometric_hardware)
        }
    }

    /**
     * Navigate to main activity once login success.
     * */
    private fun navigateToHome() {
        startActivity(Intent(this@LoginActivity, MainActivity::class.java))
        finish()
    }

    /**
     * Biometric dialog is prompted if the device have biometric supported hardware.
     * */
    private fun buildBiometricPrompt(): BiometricPrompt.PromptInfo {
        return BiometricPrompt.PromptInfo.Builder()
            .setTitle(getString(R.string.verifiy_identity))
            .setDescription(getString(R.string.verify_biometric_message))
            .setNegativeButtonText(getString(R.string.use_credential))
            //Allows user to authenticate without performing an action,
            //such as pressing a button, after their biometric credential is accepted.
            .setConfirmationRequired(false)
            .build()
    }

    /**
     * Biometric callback sends biometric auth status.
     * */
    private val biometricCallback = object : BiometricPrompt.AuthenticationCallback() {
        override fun onAuthenticationSucceeded(result: BiometricPrompt.AuthenticationResult) {
            super.onAuthenticationSucceeded(result)
            navigateToHome()
        }

        override fun onAuthenticationError(errorCode: Int, errString: CharSequence) {
            super.onAuthenticationError(errorCode, errString)
            //setErrorNotice(errString.toString())
        }
    }
}