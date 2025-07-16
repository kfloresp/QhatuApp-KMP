package com.rgk.qhatu.feature.auth.data.remote

import com.rgk.qhatu.feature.auth.data.remote.model.UserModel
import dev.gitlive.firebase.auth.FirebaseAuth
import dev.gitlive.firebase.auth.FirebaseUser

class AuthRemoteDataSource(private val firebaseAuth : FirebaseAuth) {
    suspend fun login(
        email: String,
        password: String
    ): UserModel {
        val result = firebaseAuth.signInWithEmailAndPassword(email, password)
        return result.user?.toUser() ?: throw Exception("Login failed")
    }

    suspend fun register(
        email: String,
        password: String
    ): UserModel {
        val result = firebaseAuth.createUserWithEmailAndPassword(email, password)
        return result.user?.toUser() ?: throw Exception("Registration failed")
    }

    suspend fun logout(){
        firebaseAuth.signOut()
    }

    fun getCurrentUser(): UserModel? {
        return firebaseAuth.currentUser?.toUser()
    }

    private fun FirebaseUser.toUser(): UserModel {
        return UserModel(
            id = uid,
            email = email ?: "",
            displayName = displayName
        )
    }

}