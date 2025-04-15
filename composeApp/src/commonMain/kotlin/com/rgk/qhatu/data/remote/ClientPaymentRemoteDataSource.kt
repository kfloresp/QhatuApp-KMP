package com.rgk.qhatu.data.remote

import com.rgk.qhatu.data.remote.model.ClientPaymentModel
import com.rgk.qhatu.domain.common.SyncTable
import dev.gitlive.firebase.firestore.FirebaseFirestore

class ClientPaymentRemoteDataSource(private val firestore: FirebaseFirestore) {
    suspend fun fetchCollection(): List<ClientPaymentModel> {
        val querySnapshot = firestore.collection(SyncTable.ClientPayment.collection).get()
        val documents = querySnapshot.documents.map { documentSnapshot ->
            documentSnapshot.data<ClientPaymentModel>()
        }
        return documents
    }

    suspend fun uploadCollection(data: ClientPaymentModel) {
        firestore.collection(SyncTable.ClientPayment.collection).document(data.id).set(data)
    }
}