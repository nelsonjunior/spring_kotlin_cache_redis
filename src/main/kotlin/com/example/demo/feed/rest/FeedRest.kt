package com.example.demo.feed.rest

import com.example.demo.feed.model.Anuncio
import com.example.demo.feed.model.AnuncioDTO
import com.example.demo.feed.service.AnuncioService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.util.UriComponentsBuilder


@RestController
@RequestMapping(path = ["/api"])
class FeedRest(private val anuncioService: AnuncioService) {

    @GetMapping("/anuncios")
    fun getAllAnuncios(): MutableList<AnuncioDTO> {
        return anuncioService.getAll().map { it.toDTO() }.toMutableList()
    }

    @GetMapping("/anuncios/{id}")
    fun getById(@PathVariable id: Long): AnuncioDTO = anuncioService.recuperarPorId(id).toDTO()

    @PostMapping("/anuncios")
    fun insert(@RequestBody anuncio: AnuncioDTO): AnuncioDTO {
        return anuncioService.adicionar(Anuncio(Long.MIN_VALUE, anuncio.titulo, anuncio.resumo)).toDTO()
    }

    @PutMapping("/anuncios")
    fun update(@RequestBody anuncio: AnuncioDTO): AnuncioDTO = anuncioService.alterar(Anuncio(anuncio.id, anuncio.titulo, anuncio.resumo)).toDTO()


    @DeleteMapping("/anuncios/{id}")
    fun deleteById(@PathVariable id: Long): ResponseEntity<Void> {
        anuncioService.excluirPorId(id)
        return ResponseEntity(HttpStatus.NO_CONTENT)
    }
}