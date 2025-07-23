package com.rgk.qhatu.feature.setting.data.remote

import com.rgk.qhatu.feature.setting.data.remote.model.BrandModel
import dev.gitlive.firebase.firestore.FirebaseFirestore

private const val COLLECTION = "marca"

class BrandRemoteDataSource(private val firestore: FirebaseFirestore) {
    suspend fun fetchCollection(): List<BrandModel> {
        val querySnapshot = firestore.collection(COLLECTION).get()
        val documents = querySnapshot.documents.map { documentSnapshot ->
            documentSnapshot.data<BrandModel>()
        }
        return documents
    }

    suspend fun uploadCollection(data: BrandModel) {
        firestore.collection(COLLECTION).document(data.id).set(data)
    }
}