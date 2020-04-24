package com.jelndoca.spotyapp.models

data class ArtistsObjectModel(
    val id: Int,
    val name: String,
    val image: String,
    val genres: List<String>,
    val popularity: String,
    val spotifyUrl: String,
    val spotifyId: String,
    val createdAt: String,
    val updatedAt: String,
    val artist: Int
)