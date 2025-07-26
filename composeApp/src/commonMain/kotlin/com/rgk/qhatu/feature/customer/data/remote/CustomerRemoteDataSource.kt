package com.rgk.qhatu.feature.customer.data.remote

import com.rgk.qhatu.feature.customer.data.remote.model.CustomerModel
import dev.gitlive.firebase.firestore.FirebaseFirestore

private const val COLLECTION = "cliente"
class ClientRemoteDataSource(private val firestore: FirebaseFirestore) {
    suspend fun fetchCollection(): List<CustomerModel> {
        val querySnapshot = firestore.collection(COLLECTION).get()
        val documents = querySnapshot.documents.map { documentSnapshot ->
            documentSnapshot.data<CustomerModel>()
        }
        return documents
    }

    suspend fun uploadCollection(data: CustomerModel) {
        firestore.collection(COLLECTION).document(data.id).set(data)
    }
}