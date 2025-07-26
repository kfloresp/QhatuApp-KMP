package com.rgk.qhatu.feature.audit.data.remote

import com.rgk.qhatu.feature.audit.data.remote.model.AuditLogModel
import dev.gitlive.firebase.firestore.FirebaseFirestore

private const val COLLECTION = "auditoria"
class AuditLogRemoteDataSource(private val firestore: FirebaseFirestore) {
    suspend fun fetchCollection(): List<AuditLogModel> {
        val querySnapshot = firestore.collection(COLLECTION).get()
        val documents = querySnapshot.documents.map { documentSnapshot ->
            documentSnapshot.data<AuditLogModel>()
        }
        return documents
    }

    suspend fun uploadCollection(data: AuditLogModel) {
        firestore.collection(COLLECTION).document(data.id).set(data)
    }
}