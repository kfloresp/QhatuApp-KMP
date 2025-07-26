package com.rgk.qhatu.feature.customer.data.remote

import com.rgk.qhatu.feature.customer.data.remote.model.ClientModel
import dev.gitlive.firebase.firestore.FirebaseFirestore

private const val COLLECTION = "cliente"
class ClientRemoteDataSource(private val firestore: FirebaseFirestore) {
    suspend fun fetchCollection(): List<ClientModel> {
        val querySnapshot = firestore.collection(COLLECTION).get()
        val documents = querySnapshot.documents.map { documentSnapshot ->
            documentSnapshot.data<ClientModel>()
        }
        return documents
    }

    suspend fun uploadCollection(data: ClientModel) {
        firestore.collection(COLLECTION).document(data.id).set(data)
    }
}