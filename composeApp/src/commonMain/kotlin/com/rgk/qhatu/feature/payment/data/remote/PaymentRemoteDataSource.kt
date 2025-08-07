package com.rgk.qhatu.feature.payment.data.remote

import com.rgk.qhatu.feature.payment.data.remote.model.PaymentModel
import dev.gitlive.firebase.firestore.FirebaseFirestore

private const val COLLECTION = "customerPayments"
class ClientPaymentRemoteDataSource(private val firestore: FirebaseFirestore) {
    suspend fun fetchCollection(): List<PaymentModel> {
        val querySnapshot = firestore.collection(COLLECTION).get()
        val documents = querySnapshot.documents.map { documentSnapshot ->
            documentSnapshot.data<PaymentModel>()
        }
        return documents
    }

    suspend fun uploadCollection(data: PaymentModel) {
        firestore.collection(COLLECTION).document(data.id).set(data)
    }
}