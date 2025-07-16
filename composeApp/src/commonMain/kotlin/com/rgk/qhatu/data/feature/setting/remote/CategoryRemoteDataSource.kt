package com.rgk.qhatu.data.feature.setting.remote

import com.rgk.qhatu.data.feature.setting.remote.model.CategoryModel
import com.rgk.qhatu.domain.common.SyncTable
import dev.gitlive.firebase.firestore.FirebaseFirestore

class CategoryRemoteDataSource(private val firestore: FirebaseFirestore){
    suspend fun fetchCollection(): List<CategoryModel> {
        val querySnapshot = firestore.collection(SyncTable.Category.collection).get()
        val documents = querySnapshot.documents.map { documentSnapshot ->
            documentSnapshot.data<CategoryModel>()
        }
        return documents
    }

    suspend fun uploadCollection(data: CategoryModel) {
        firestore.collection(SyncTable.Category.collection).document(data.id).set(data)
    }
}