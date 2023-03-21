package com.example.app.network

import android.app.Application
import android.content.Context
import androidx.room.Room
import com.example.app.database.AppDatabase
import com.example.app.model.CartItem
import com.example.app.repository.cart.CartItemLocalDataSource
import com.example.app.repository.cart.CartRepository

object ServiceLocator {

    /*
    초기화 작업
     */
    private var apiClient : ApiClient? = null
    private var database : AppDatabase? = null
    private var cartRepository : CartRepository? = null

    fun provideApiClient() : ApiClient{
        return apiClient ?: kotlin.run{
            ApiClient.create().also{
                apiClient = it
            }
        }
    }

    /*
    데이터베이스의 인스턴스를 생성성
    */
    private fun provideDatabase(applicationContext: Context) : AppDatabase{
        /*
        데이터베이스가 생성된적이 있다면 database를 반환하고
        생성된적이 없다면 (null이라면) 데이터베이스 생성
         */
        return database ?: kotlin.run{
            Room.databaseBuilder(
                applicationContext,
                AppDatabase::class.java,
                "shoppi_local"
            ).build().also{
                database = it
            }
        }
    }

    fun provideCartRepository(context : Context) : CartRepository{
        return cartRepository ?: kotlin.run{
            val dao = provideDatabase(context.applicationContext).cartItemDao()
            CartRepository(CartItemLocalDataSource(dao)).also{
                cartRepository = it
            }
        }
    }
}