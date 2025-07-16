package com.rgk.qhatu.feature.setting.data.remote

import com.rgk.qhatu.feature.setting.data.remote.model.BrandModel
import com.rgk.qhatu.common.model.SyncTable
import dev.gitlive.firebase.firestore.FirebaseFirestore

class BrandRemoteDataSource(private val firestore: FirebaseFirestore) {
    suspend fun fetchCollection(): List<BrandModel> {
        val querySnapshot = firestore.collection(SyncTable.Brand.collection).get()
        val documents = querySnapshot.documents.map { documentSnapshot ->
            documentSnapshot.data<BrandModel>()
        }
        return documents
    }

    suspend fun uploadCollection(data: BrandModel) {
        firestore.collection(SyncTable.Brand.collection).document(data.id).set(data)
    }
}