package com.rgk.qhatu.data.feature.setting.remote

import com.rgk.qhatu.data.feature.setting.remote.model.ConfigurationModel
import com.rgk.qhatu.domain.common.SyncTable
import dev.gitlive.firebase.firestore.FirebaseFirestore

class ConfigurationRemoteDataSource(private val firestore: FirebaseFirestore) {
    suspend fun fetchCollection(): List<ConfigurationModel> {
        val querySnapshot = firestore.collection(SyncTable.Configuration.collection).get()
        val documents = querySnapshot.documents.map { documentSnapshot ->
            documentSnapshot.data<ConfigurationModel>()
        }
        return documents
    }

    suspend fun uploadCollection(data: ConfigurationModel) {
        firestore.collection(SyncTable.Configuration.collection).document(data.id).set(data)
    }

}