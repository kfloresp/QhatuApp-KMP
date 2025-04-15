package com.rgk.qhatu.data.remote

import com.rgk.qhatu.data.remote.model.AuditLogModel
import com.rgk.qhatu.data.remote.model.ConfigurationModel
import com.rgk.qhatu.domain.common.SyncTable
import dev.gitlive.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class AuditLogRemoteDataSource(private val firestore: FirebaseFirestore) {
    suspend fun fetchCollection(): List<AuditLogModel> {
        val querySnapshot = firestore.collection(SyncTable.AuditLog.collection).get()
        val documents = querySnapshot.documents.map { documentSnapshot ->
            documentSnapshot.data<AuditLogModel>()
        }
        return documents
    }

    suspend fun uploadCollection(data: AuditLogModel) {
        firestore.collection(SyncTable.AuditLog.collection).document(data.id).set(data)
    }
}