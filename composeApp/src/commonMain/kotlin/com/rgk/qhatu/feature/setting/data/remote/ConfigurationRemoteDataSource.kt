package com.rgk.qhatu.feature.setting.data.remote

import com.rgk.qhatu.feature.setting.data.remote.model.ConfigurationModel
import dev.gitlive.firebase.firestore.FirebaseFirestore

private const val COLLECTION = "configuracion"

class ConfigurationRemoteDataSource(private val firestore: FirebaseFirestore) {
    suspend fun fetchCollection(): List<ConfigurationModel> {
        val querySnapshot = firestore.collection(COLLECTION).get()
        val documents = querySnapshot.documents.map { documentSnapshot ->
            documentSnapshot.data<ConfigurationModel>()
        }
        return documents
    }

    suspend fun uploadCollection(data: ConfigurationModel) {
        firestore.collection(COLLECTION).document(data.id).set(data)
    }

}