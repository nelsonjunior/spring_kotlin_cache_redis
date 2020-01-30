package com.example.demo.shared.graphql

import com.coxautodev.graphql.tools.GraphQLMutationResolver
import com.example.demo.comment.repository.CommentRepository
import com.example.demo.feed.model.Anuncio
import com.example.demo.feed.repository.AnuncioRepository
import org.springframework.stereotype.Component

@Component
class AnuncioMutationResolver(private val anuncioRepository: AnuncioRepository, private val commentRepository: CommentRepository)
    : GraphQLMutationResolver {

    fun novoAnuncio(titulo: String, resumo: String): Anuncio? {
        val anuncio = Anuncio(-1, titulo, resumo)
        anuncioRepository.save(anuncio)
        return anuncio
    }

    fun deletarAnuncio(id: Long): Boolean {
        anuncioRepository.deleteById(id)
        return true
    }

}