package com.example.demo.comment.rest

import com.example.demo.comment.model.Comment
import com.example.demo.comment.model.CommentDTO
import com.example.demo.comment.service.CommentService
import com.example.demo.feed.model.Anuncio
import com.example.demo.feed.model.AnuncioDTO
import com.example.demo.feed.service.AnuncioService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.util.UriComponentsBuilder


@RestController
@RequestMapping(path = ["/api/comments"])
class CommentRest(private val service: CommentService, private val anuncioService: AnuncioService) {

    @GetMapping
    fun getAllAnuncios(): ResponseEntity<MutableList<CommentDTO>> {
        return ResponseEntity.ok(service.getAll().map { it.toDTO() }.toMutableList())
    }

    @GetMapping("{id}")
    fun getById(@PathVariable id: Long): ResponseEntity<CommentDTO> = ResponseEntity.ok(service.recuperarPorId(id).toDTO())

    @PostMapping
    fun insert(@RequestBody dto: CommentDTO): ResponseEntity<CommentDTO> {
        val anuncio = anuncioService.recuperarPorId(dto.anuncio.id)
        return ResponseEntity.ok(service.adicionar(Comment(Long.MIN_VALUE, dto.text, dto.usuario, dto.data, anuncio)).toDTO())
    }

    @PutMapping
    fun update(@RequestBody dto: CommentDTO): ResponseEntity<CommentDTO> {
        val anuncio = anuncioService.recuperarPorId(dto.anuncio.id)
        val comment = service.alterar(Comment(Long.MIN_VALUE, dto.text, dto.usuario, dto.data, anuncio)).toDTO();
        return ResponseEntity.ok(comment)
    }

    fun deleteById(@PathVariable id: Long): ResponseEntity<Void> {
        service.excluirPorId(id)
        return ResponseEntity(HttpStatus.NO_CONTENT)
    }
}