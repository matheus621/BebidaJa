package com.example.bebidaja.data

import com.example.bebidaja.R
import com.example.bebidaja.domain.model.Product

class FakeProductRepository : ProductRepository {
    override suspend fun listAll() = listOf(
        Product(
            1,
            "Bohemia",
            "Cerveja Bohemia Pilsen 350ml – Pack com 12 unidades",
            "R$35,88",
            R.drawable.sem_imagem,
            "cerveja"
        ), Product(
            2,
            "Corona",
            "Cerveja Corona Extra 355ml – Pack com 6 garrafas long neck",
            "R$35,94",
            R.drawable.sem_imagem,
            "cerveja"
        ), Product(
            3,
            "Budweiser",
            "Cerveja Budweiser American Lager 350ml – Lata unitária",
            "R$5,99",
            R.drawable.sem_imagem,
            "cerveja"
        ), Product(
            4,
            "Jack Daniels",
            "Whisky Jack Daniel’s Old No. 7 Tennessee – Garrafa 1L",
            "R$129,90",
            R.drawable.sem_imagem,
            "destilado"
        ), Product(
            5,
            "Miolo",
            "Vinho Tinto Miolo Seleção – Garrafa 750ml",
            "R$49,90",
            R.drawable.sem_imagem,
            "vinho"
        ), Product(
            6,
            "Coca-Cola",
            "Refrigerante Coca-Cola Original – Garrafa PET 2L",
            "R$9,99",
            R.drawable.sem_imagem,
            "semalcool"
        )
    )
}