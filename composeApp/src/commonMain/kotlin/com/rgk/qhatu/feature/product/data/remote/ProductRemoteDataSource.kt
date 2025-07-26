package com.rgk.qhatu.feature.product.data.remote

import com.rgk.qhatu.feature.product.data.remote.model.ProductModel
import dev.gitlive.firebase.firestore.FirebaseFirestore

private const val COLLECTION = "producto"

class ProductRemoteDataSource(private val firestore: FirebaseFirestore) {
    suspend fun fetchCollection(): List<ProductModel> {
        val querySnapshot = firestore.collection(COLLECTION).get()
        val documents = querySnapshot.documents.map { documentSnapshot ->
            documentSnapshot.data<ProductModel>()
        }
        return documents
    }

    suspend fun uploadCollection(data: ProductModel) {
        firestore.collection(COLLECTION).document(data.id).set(data)
    }
}