package com.rgk.qhatu.feature.sale.data.remote

import com.rgk.qhatu.feature.sale.data.remote.model.TransactionModel
import dev.gitlive.firebase.firestore.FirebaseFirestore

private const val COLLECTION = "movimiento"

class TransactionRemoteDataSource(private val firestore: FirebaseFirestore) {
    suspend fun fetchCollection(): List<TransactionModel> {
        val querySnapshot = firestore.collection(COLLECTION).get()
        val documents = querySnapshot.documents.map { documentSnapshot ->
            documentSnapshot.data<TransactionModel>()
        }
        return documents
    }

    suspend fun uploadCollection(data: TransactionModel) {
        firestore.collection(COLLECTION).document(data.id).set(data)
    }
}