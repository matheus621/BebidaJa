package com.example.bebidaja.domain.model

data class Product(
    val id: Int,
    val name: String,
    val description: String,
    val price: String,
    val imageRes: Int,
    val type: String
)