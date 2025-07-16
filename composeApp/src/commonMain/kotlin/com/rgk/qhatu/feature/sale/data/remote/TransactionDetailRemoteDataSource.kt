package com.rgk.qhatu.feature.sale.data.remote

import com.rgk.qhatu.feature.sale.data.remote.model.TransactionDetailModel
import com.rgk.qhatu.common.model.SyncTable
import dev.gitlive.firebase.firestore.FirebaseFirestore

class TransactionDetailRemoteDataSource(private val firestore: FirebaseFirestore) {
    suspend fun fetchCollection(): List<TransactionDetailModel> {
        val querySnapshot = firestore.collection(SyncTable.TransactionDetail.collection).get()
        val documents = querySnapshot.documents.map { documentSnapshot ->
            documentSnapshot.data<TransactionDetailModel>()
        }
        return documents
    }

    suspend fun uploadCollection(data: TransactionDetailModel) {
        firestore.collection(SyncTable.TransactionDetail.collection).document(data.id).set(data)
    }
}