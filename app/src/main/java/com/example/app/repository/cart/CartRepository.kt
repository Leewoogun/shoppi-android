package com.example.app.repository.cart

import com.example.app.model.CartItem
import com.example.app.model.Product
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class CartRepository(
    private val localDataSource: CartItemLocalDataSource,
    private val ioDispatcher : CoroutineDispatcher = Dispatchers.IO
    ) {



    suspend fun addCartItem(product : Product){
        withContext(ioDispatcher){
            val cartItem  = CartItem(
                productId = product.productId,
                label = product.label,
                price = product.price,
                brandName = product.brandName ?: "",
                thumbnailImageUrl = product.thumbnailImageUrl ?: "",
                type = product.type ?: "",
                amount = 1
            )
            localDataSource.addCartItem(cartItem)
        }
    }

    /*
    withContext : 코루틴 빌더 중 하나로 특정 스레드에서 코루틴을 실행하는데 사용
     */
    suspend fun getCartItems() : List<CartItem>{
        return withContext(ioDispatcher){
            localDataSource.getCartItems()
        }
    }
}