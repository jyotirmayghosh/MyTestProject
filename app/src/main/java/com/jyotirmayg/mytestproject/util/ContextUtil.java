package com.jyotirmayg.mytestproject.util;

import android.content.Context;
import android.content.ContextWrapper;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.LocaleList;

import java.util.Locale;

/**
 * @author jyoti
 * @created on 20-05-2022
 */

public class ContextUtil extends ContextWrapper {

    public ContextUtil(Context context) {
        super(context);
    }

    public static ContextWrapper updateLocale(Context context, Locale localeToSwitchTo) {
        Resources resources = context.getResources();
        Configuration configuration = resources.getConfiguration();

        LocaleList localeList = new LocaleList(localeToSwitchTo);
        LocaleList.setDefault(localeList);
        configuration.setLocales(localeList);

        context = context.createConfigurationContext(configuration);

        return new ContextUtil(context);
    }
}
