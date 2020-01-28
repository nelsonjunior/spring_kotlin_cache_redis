package com.example.demo.feed.model

import java.io.Serializable
import javax.persistence.*

@Entity
data class Anuncio(
        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        val id: Long,
        val titulo: String,
        val resumo: String
) : Serializable {
    fun toDTO(): AnuncioDTO {
        return AnuncioDTO(id = this.id, titulo = this.titulo, resumo = this.resumo)
    }

}
