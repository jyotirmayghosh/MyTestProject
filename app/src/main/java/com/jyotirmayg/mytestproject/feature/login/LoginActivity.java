package com.jyotirmayg.mytestproject.feature.login;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.biometric.BiometricManager;
import androidx.biometric.BiometricPrompt;
import androidx.core.content.ContextCompat;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import com.jyotirmayg.mytestproject.R;
import com.jyotirmayg.mytestproject.databinding.ActivityLoginBinding;
import com.jyotirmayg.mytestproject.feature.main.MainActivity;

import org.jetbrains.annotations.Nullable;

import java.util.concurrent.Executor;

/**
 * @author jyoti
 * @created on 14-05-2022
 * This class is responsible for creating Login Activity.
 */
public class LoginActivity extends AppCompatActivity {

    private ActivityLoginBinding mBinding;
    private BiometricPrompt biometricPrompt;
    private BiometricManager biometricManager;

    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        LoginViewModel viewModel = new ViewModelProvider(this).get(LoginViewModel.class);

        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_login);
        mBinding.setViewmodel(viewModel);

        setupBiometricAuthentication();
        doBiometricLogin();

        mBinding.txtAuthenticate.setOnClickListener(view -> doBiometricLogin());
        mBinding.btnLogin.setOnClickListener(view -> {
            String userName = mBinding.etUserName.getText().toString().trim();
            String password = mBinding.etPassword.getText().toString().trim();

            viewModel.onClickLogin(userName, password);
        });

        viewModel.loginStatus.observe(this, loginStatus -> {
            if (loginStatus.isLoginSuccess()) {
                navigateToHome();
            }
            Toast.makeText(this, loginStatus.getMessage(), Toast.LENGTH_SHORT).show();
        });
    }

    /**
     * Initialize BiometricManager and BiometricPrompt.
     */
    private void setupBiometricAuthentication() {
        biometricManager = BiometricManager.from(this);
        Executor executor = ContextCompat.getMainExecutor(this);
        biometricPrompt = new BiometricPrompt(this, executor, new BiometricPrompt.AuthenticationCallback() {
            public void onAuthenticationSucceeded(@NonNull BiometricPrompt.AuthenticationResult result) {
                super.onAuthenticationSucceeded(result);
                navigateToHome();
            }

            public void onAuthenticationError(int errorCode, @NonNull CharSequence errString) {
                super.onAuthenticationError(errorCode, errString);
                //setErrorNotice(errString.toString())
            }
        });
    }

    /**
     * This checks if the biometric hardware is present.
     * This function prompt for biometric auth login otherwise throw an error message.
     */
    private void doBiometricLogin() {
        if (biometricManager.canAuthenticate(BiometricManager.Authenticators.DEVICE_CREDENTIAL) == BiometricManager.BIOMETRIC_SUCCESS) {
            biometricPrompt.authenticate(buildBiometricPrompt());
        } else {
            mBinding.txtAuthenticate.setEnabled(false);
            mBinding.txtAuthenticate.setTextColor(ContextCompat.getColor(this, R.color.gray_200));
        }
        mBinding.txtError.setText(getString(R.string.error_no_biometric_hardware));
    }

    /**
     * Navigate to main activity once login success.
     */
    private void navigateToHome() {
        startActivity(new Intent(this, MainActivity.class));
        finish();
    }

    /**
     * Biometric dialog is prompted if the device have biometric supported hardware.
     */
    private BiometricPrompt.PromptInfo buildBiometricPrompt() {
        return new BiometricPrompt.PromptInfo.Builder()
                .setTitle(getString(R.string.verifiy_identity))
                .setDescription(getString(R.string.verify_biometric_message))
                .setNegativeButtonText(getString(R.string.use_credential))
                //Allows user to authenticate without performing an action,
                //such as pressing a button, after their biometric credential is accepted.
                .setConfirmationRequired(false)
                .build();
    }
}
