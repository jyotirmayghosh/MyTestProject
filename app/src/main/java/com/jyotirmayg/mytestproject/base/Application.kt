package com.jyotirmayg.mytestproject.base

import android.app.Application
import com.jyotirmayg.mytestproject.data.db.AppDatabase
import com.jyotirmayg.mytestproject.data.repositories.ItemRepository
import com.jyotirmayg.mytestproject.view.login.LoginViewModelFactory
import com.jyotirmayg.mytestproject.view.main.MainViewModelFactory
import com.jyotirmayg.mytestproject.view.main.addItem.AddItemModelFactory
import com.jyotirmayg.mytestproject.view.main.home.HomeModelFactory
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.androidXModule
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.provider
import org.kodein.di.generic.singleton

/**
 * @author jyoti
 * @created on 14-05-2022
 * This class is responsible for creating abject and add dependence injection with KodineAware.
 */
class Application : Application(), KodeinAware {

    override val kodein = Kodein.lazy {
        import(androidXModule(this@Application))

        bind() from singleton { AppDatabase(instance()) }
        bind() from singleton { ItemRepository(instance()) }

        bind() from provider { LoginViewModelFactory(this@Application) }
        bind() from provider { MainViewModelFactory(this@Application) }
        bind() from provider { HomeModelFactory(this@Application, instance()) }
        bind() from provider { AddItemModelFactory(this@Application, instance()) }
    }
}