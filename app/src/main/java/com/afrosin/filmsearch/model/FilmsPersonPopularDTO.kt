package com.afrosin.filmsearch.model

data class FilmsPersonPopularDTO(
    val page: Int,
    val results: List<Person>,
    val total_pages: Int,
    val total_results: Int
)
