package com.jyotirmayg.mytestproject.feature.login;

import static com.google.common.truth.Truth.assertThat;

import junit.framework.TestCase;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

/**
 * @author jyoti
 * @created on 20-05-2022
 */
@RunWith(JUnit4.class)
public class LoginViewModelTest extends TestCase {

    private LoginViewModel viewModel;

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        viewModel = new LoginViewModel();
    }

    @Test
    public void empty_username_returns_false() {
        boolean result = viewModel.doLogin("", "123");
        assertThat(result).isFalse();
    }

    @Test
    public void empty_password_returns_false() {
        boolean result = viewModel.doLogin("admin", "");
        assertThat(result).isFalse();
    }

    @Test
    public void returns_true() {
        boolean result = viewModel.doLogin("admin", "admin123");
        assertThat(result).isTrue();
    }
}