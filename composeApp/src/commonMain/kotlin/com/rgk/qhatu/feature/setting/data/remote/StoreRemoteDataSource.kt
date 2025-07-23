package com.rgk.qhatu.feature.setting.data.remote

import com.rgk.qhatu.feature.setting.data.remote.model.StoreModel
import dev.gitlive.firebase.firestore.FirebaseFirestore

private const val COLLECTION = "tienda"

class StoreRemoteDataSource(private val firestore: FirebaseFirestore) {

    suspend fun fetchCollection(): List<StoreModel> {
        val querySnapshot = firestore.collection(COLLECTION).get()
        return querySnapshot.documents.map { documentSnapshot ->
            documentSnapshot.data<StoreModel>()
        }
    }

    suspend fun uploadCollection(data: StoreModel) {
        firestore.collection(COLLECTION).document(data.id).set(data)
    }
}