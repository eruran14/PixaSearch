package com.eru.pixasearch

data class PixaModel(
    val hits: List<ImageModel>
)

data class ImageModel (
    val largeImageURL: String
    )
