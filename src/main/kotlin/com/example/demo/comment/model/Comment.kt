package com.example.demo.comment.model

import com.example.demo.feed.model.Anuncio
import java.io.Serializable
import java.util.*
import javax.persistence.*

@Entity
data class Comment(
        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        val id: Long,
        val text: String,
        val usuario: String,
        val data: Date,
        @ManyToOne(fetch = FetchType.EAGER)
        @JoinColumn(name = "anuncio_id")
        val anuncio: Anuncio
) : Serializable {
    fun toDTO(): CommentDTO {
        return CommentDTO(id = this.id, text = this.text, usuario = this.usuario, data = this.data, anuncio = this.anuncio.toDTO())
    }

}