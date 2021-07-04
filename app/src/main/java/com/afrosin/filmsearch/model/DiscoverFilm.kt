package com.afrosin.filmsearch.model

data class FilmsDiscoverDTO(
    val page: Int,
    val results: List<Film>,
    val total_pages: Int,
    val total_results: Int
)