package com.rgk.qhatu.data.feature.customer.remote

import com.rgk.qhatu.data.feature.customer.remote.model.ClientModel
import com.rgk.qhatu.domain.common.SyncTable
import dev.gitlive.firebase.firestore.FirebaseFirestore

class ClientRemoteDataSource(private val firestore: FirebaseFirestore) {
    suspend fun fetchCollection(): List<ClientModel> {
        val querySnapshot = firestore.collection(SyncTable.Client.collection).get()
        val documents = querySnapshot.documents.map { documentSnapshot ->
            documentSnapshot.data<ClientModel>()
        }
        return documents
    }

    suspend fun uploadCollection(data: ClientModel) {
        firestore.collection(SyncTable.Client.collection).document(data.id).set(data)
    }
}