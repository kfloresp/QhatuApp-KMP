package com.rgk.qhatu.feature.sale.data.remote

import com.rgk.qhatu.feature.sale.data.remote.model.PaymentTransactionModel
import dev.gitlive.firebase.firestore.FirebaseFirestore

private const val COLLECTION = "pagomovimiento"

class PaymentTransactionRemoteDataSource(private val firestore: FirebaseFirestore) {
    suspend fun fetchCollection(): List<PaymentTransactionModel> {
        val querySnapshot = firestore.collection(COLLECTION).get()
        val documents = querySnapshot.documents.map { documentSnapshot ->
            documentSnapshot.data<PaymentTransactionModel>()
        }
        return documents
    }

    suspend fun uploadCollection(data: PaymentTransactionModel) {
        firestore.collection(COLLECTION).document(data.id).set(data)
    }
}