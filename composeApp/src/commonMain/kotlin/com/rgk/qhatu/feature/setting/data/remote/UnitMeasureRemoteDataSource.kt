package com.rgk.qhatu.feature.setting.data.remote

import com.rgk.qhatu.feature.setting.data.remote.model.UnitMeasureModel
import dev.gitlive.firebase.firestore.FirebaseFirestore

private const val COLLECTION = "unidadmedida"

class UnitMeasureRemoteDataSource(private val firestore: FirebaseFirestore) {
    suspend fun fetchCollection(): List<UnitMeasureModel> {
        val querySnapshot = firestore.collection(COLLECTION).get()
        val documents = querySnapshot.documents.map { documentSnapshot ->
            documentSnapshot.data<UnitMeasureModel>()
        }
        return documents
    }

    suspend fun uploadCollection(data: UnitMeasureModel) {
        firestore.collection(COLLECTION).document(data.id).set(data)
    }
}