package com.rgk.qhatu.feature.payment.data.remote

import com.rgk.qhatu.feature.payment.data.remote.model.PaymentTransactionModel
import com.rgk.qhatu.common.model.SyncTable
import dev.gitlive.firebase.firestore.FirebaseFirestore

class PaymentTransactionRemoteDataSource(private val firestore: FirebaseFirestore) {
    suspend fun fetchCollection(): List<PaymentTransactionModel> {
        val querySnapshot = firestore.collection(SyncTable.PaymentTransaction.collection).get()
        val documents = querySnapshot.documents.map { documentSnapshot ->
            documentSnapshot.data<PaymentTransactionModel>()
        }
        return documents
    }

    suspend fun uploadCollection(data: PaymentTransactionModel) {
        firestore.collection(SyncTable.PaymentTransaction.collection).document(data.id).set(data)
    }
}