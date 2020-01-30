package com.example.demo.feed.model

import com.example.demo.comment.model.Comment
import java.io.Serializable
import javax.persistence.*

@Entity
data class Anuncio(
        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        val id: Long,
        val titulo: String,
        val resumo: String,
        @OneToMany(mappedBy = "anuncio", cascade = arrayOf(CascadeType.ALL), fetch = FetchType.EAGER)
        val comments: List<Comment> = ArrayList()
) : Serializable {
    fun toDTO(): AnuncioDTO {
        return AnuncioDTO(id = this.id, titulo = this.titulo, resumo = this.resumo)
    }

}
