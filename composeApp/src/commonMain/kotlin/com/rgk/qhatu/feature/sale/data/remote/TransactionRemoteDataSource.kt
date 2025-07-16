package com.rgk.qhatu.feature.sale.data.remote

import com.rgk.qhatu.feature.sale.data.remote.model.TransactionModel
import com.rgk.qhatu.common.model.SyncTable
import dev.gitlive.firebase.firestore.FirebaseFirestore

class TransactionRemoteDataSource(private val firestore: FirebaseFirestore) {
    suspend fun fetchCollection(): List<TransactionModel> {
        val querySnapshot = firestore.collection(SyncTable.Transaction.collection).get()
        val documents = querySnapshot.documents.map { documentSnapshot ->
            documentSnapshot.data<TransactionModel>()
        }
        return documents
    }

    suspend fun uploadCollection(data: TransactionModel) {
        firestore.collection(SyncTable.Transaction.collection).document(data.id).set(data)
    }
}