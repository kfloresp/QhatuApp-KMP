package com.rgk.qhatu.feature.sale.data.remote

import com.rgk.qhatu.feature.sale.data.remote.model.TransactionDetailModel
import dev.gitlive.firebase.firestore.FirebaseFirestore

private const val COLLECTION = "detallemovimiento"

class TransactionDetailRemoteDataSource(private val firestore: FirebaseFirestore) {
    suspend fun fetchCollection(): List<TransactionDetailModel> {
        val querySnapshot = firestore.collection(COLLECTION).get()
        val documents = querySnapshot.documents.map { documentSnapshot ->
            documentSnapshot.data<TransactionDetailModel>()
        }
        return documents
    }

    suspend fun uploadCollection(data: TransactionDetailModel) {
        firestore.collection(COLLECTION).document(data.id).set(data)
    }
}