package com.example.demo.comment.model

import com.example.demo.feed.model.AnuncioDTO
import java.util.*

data class CommentDTO(
        val id: Long = -1,
        val text: String = "",
        val usuario: String = "",
        val data: Date = Date(),
        val anuncio: AnuncioDTO
)