package com.example.demo.shared.graphql

import com.coxautodev.graphql.tools.GraphQLQueryResolver
import com.example.demo.comment.repository.CommentRepository
import com.example.demo.feed.model.Anuncio
import com.example.demo.feed.repository.AnuncioRepository
import org.springframework.stereotype.Component

@Component
class AnuncioQueryResolver(private val anuncioRepository: AnuncioRepository, private val commentRepository: CommentRepository)
    : GraphQLQueryResolver {

    fun obterAnuncios(): List<Anuncio> {
        return anuncioRepository.findAll()
    }

    fun contarAnuncios(): Long {
        return anuncioRepository.count()
    }

    fun obterAnuncioPorId(id: Long): Anuncio? {
        return anuncioRepository.findById(id).orElseThrow()
    }

}