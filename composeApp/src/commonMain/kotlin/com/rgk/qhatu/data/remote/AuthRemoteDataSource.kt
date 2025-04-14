package com.rgk.qhatu.data.remote

import com.rgk.qhatu.domain.model.UserModel
import dev.gitlive.firebase.Firebase
import dev.gitlive.firebase.auth.FirebaseUser
import dev.gitlive.firebase.auth.auth

class AuthRemoteDataSource {
    val firebaseAuth = Firebase.auth

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

    fun getCurrentUser(): UserModel ? {
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