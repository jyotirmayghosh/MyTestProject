package com.jyotirmayg.mytestproject.feature.addItem;

import static org.junit.Assert.*;

import android.content.Context;

import androidx.test.platform.app.InstrumentationRegistry;

import com.jyotirmayg.mytestproject.data.repositories.ItemRepository;

import junit.framework.TestCase;

import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

/**
 * @author jyoti
 * @created on 20-05-2022
 */
@RunWith(JUnit4.class)
public class AddItemViewModelTest extends TestCase {

    private AddItemViewModel viewModel;

    @Override
    protected void setUp() throws Exception {
        super.setUp();

        Context context = InstrumentationRegistry.getInstrumentation().getContext();
        //AppDatabase database = AppDatabase.getInstance(context);

        ItemRepository repository = ItemRepository.getInstance(context);
        viewModel = new AddItemViewModel(context);
    }
}