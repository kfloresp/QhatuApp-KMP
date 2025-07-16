package com.rgk.qhatu.data.feature.setting.remote

import com.rgk.qhatu.data.feature.setting.remote.model.UnitMeasureModel
import com.rgk.qhatu.domain.common.SyncTable
import dev.gitlive.firebase.firestore.FirebaseFirestore

class UnitMeasureRemoteDataSource(private val firestore: FirebaseFirestore) {
    suspend fun fetchCollection(): List<UnitMeasureModel> {
        val querySnapshot = firestore.collection(SyncTable.UnitMeasure.collection).get()
        val documents = querySnapshot.documents.map { documentSnapshot ->
            documentSnapshot.data<UnitMeasureModel>()
        }
        return documents
    }

    suspend fun uploadCollection(data: UnitMeasureModel) {
        firestore.collection(SyncTable.UnitMeasure.collection).document(data.id).set(data)
    }
}