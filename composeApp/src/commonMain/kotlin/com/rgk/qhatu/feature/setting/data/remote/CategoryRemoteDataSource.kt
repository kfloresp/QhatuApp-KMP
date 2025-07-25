package com.rgk.qhatu.feature.setting.data.remote

import com.rgk.qhatu.feature.setting.data.remote.model.CategoryModel
import dev.gitlive.firebase.firestore.FirebaseFirestore

private const val COLLECTION = "categoria"

class CategoryRemoteDataSource(private val firestore: FirebaseFirestore) {
    suspend fun fetchCollection(): List<CategoryModel> {
        val querySnapshot = firestore.collection(COLLECTION).get()
        val documents = querySnapshot.documents.map { documentSnapshot ->
            documentSnapshot.data<CategoryModel>()
        }
        return documents
    }

    suspend fun uploadCollection(data: CategoryModel) {
        firestore.collection(COLLECTION).document(data.id).set(data)
    }
}