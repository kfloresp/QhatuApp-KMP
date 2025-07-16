package com.rgk.qhatu.feature.product.data.remote

import com.rgk.qhatu.feature.product.data.remote.model.ProductModel
import com.rgk.qhatu.common.model.SyncTable
import dev.gitlive.firebase.firestore.FirebaseFirestore

class ProductRemoteDataSource(private val firestore: FirebaseFirestore) {
    suspend fun fetchCollection(): List<ProductModel> {
        val querySnapshot = firestore.collection(SyncTable.Product.collection).get()
        val documents = querySnapshot.documents.map { documentSnapshot ->
            documentSnapshot.data<ProductModel>()
        }
        return documents
    }

    suspend fun uploadCollection(data: ProductModel) {
        firestore.collection(SyncTable.Product.collection).document(data.id).set(data)
    }
}