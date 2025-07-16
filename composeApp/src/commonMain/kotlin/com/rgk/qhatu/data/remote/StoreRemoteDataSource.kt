package com.rgk.qhatu.data.remote

import com.rgk.qhatu.data.remote.model.StoreModel
import com.rgk.qhatu.domain.common.SyncTable
import dev.gitlive.firebase.firestore.FirebaseFirestore

class StoreRemoteDataSource(private val firestore: FirebaseFirestore) {

    suspend fun fetchCollection(): List<StoreModel> {
        val querySnapshot = firestore.collection(SyncTable.Store.collection).get()
        return querySnapshot.documents.map { documentSnapshot ->
            documentSnapshot.data<StoreModel>()
        }
    }

    suspend fun uploadCollection(data: StoreModel) {
        firestore.collection(SyncTable.Store.collection).document(data.id).set(data)
    }
}