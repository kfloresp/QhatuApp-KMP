package com.rgk.qhatu.data.remote

import dev.gitlive.firebase.Firebase
import dev.gitlive.firebase.firestore.DocumentSnapshot
import dev.gitlive.firebase.firestore.firestore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class SyncRemoteDataSource {

    private val firestore = Firebase.firestore

    fun <T: Any> fetchCollection(collection: String): Flow<List<DocumentSnapshot>> = flow {
        firestore.collection(collection).snapshots.collect { querySnapshot ->
            emit(querySnapshot.documents)
        }
    }
}