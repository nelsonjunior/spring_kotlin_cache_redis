package com.example.demo

import com.example.demo.comment.model.Comment
import com.example.demo.comment.repository.CommentRepository
import com.example.demo.feed.model.Anuncio
import com.example.demo.feed.repository.AnuncioRepository
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cache.annotation.EnableCaching
import org.springframework.context.annotation.Bean
import java.util.*


@SpringBootApplication
@EnableCaching
class DemoApplication {

    @Bean
    fun init(anuncioRepository: AnuncioRepository, commentRepository: CommentRepository) = CommandLineRunner {
        val anuncio1 = anuncioRepository.save(Anuncio(id = -1, titulo = "Oferta 01", resumo = "Resumo da oferta 01"))
        val anuncio2 = anuncioRepository.save(Anuncio(id = -1, titulo = "Oferta 02", resumo = "Resumo da oferta 02"))

        val comment1 = Comment(id = -1, text = "Comentário aleatório 01", usuario = "Usuario 01", data = Date(), anuncio = anuncio1)
        val comment2 = Comment(id = -1, text = "Comentário aleatório 02", usuario = "Usuario 02", data = Date(), anuncio = anuncio1)
        val comment3 = Comment(id = -1, text = "Comentário aleatório 03", usuario = "Usuario 03", data = Date(), anuncio = anuncio1)
        val comment4 = Comment(id = -1, text = "Comentário aleatório 04", usuario = "Usuario 03", data = Date(), anuncio = anuncio1)

        commentRepository.save(comment1)
        commentRepository.save(comment2)
        commentRepository.save(comment3)
        commentRepository.save(comment4)

        val comment5 = Comment(id = -1, text = "Comentário aleatório 05", usuario = "Usuario 05", data = Date(), anuncio = anuncio2)
        val comment6 = Comment(id = -1, text = "Comentário aleatório 06", usuario = "Usuario 06", data = Date(), anuncio = anuncio2)
        val comment7 = Comment(id = -1, text = "Comentário aleatório 07", usuario = "Usuario 07", data = Date(), anuncio = anuncio2)

        commentRepository.save(comment5)
        commentRepository.save(comment6)
        commentRepository.save(comment7)

    }

}

fun main(args: Array<String>) {
    runApplication<DemoApplication>(*args)
}
