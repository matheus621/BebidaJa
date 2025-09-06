package com.example.bebidaja.data

import com.example.bebidaja.domain.model.Product

interface ProductRepository {
    suspend fun listAll(): List<Product>
}