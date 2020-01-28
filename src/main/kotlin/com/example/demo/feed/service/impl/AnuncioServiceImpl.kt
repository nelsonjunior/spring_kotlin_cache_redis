package com.example.demo.feed.service.impl

import com.example.demo.feed.model.Anuncio
import com.example.demo.feed.model.AnuncioDTO
import com.example.demo.feed.repository.AnuncioRepository
import com.example.demo.feed.service.AnuncioService
import org.springframework.cache.annotation.CacheEvict
import org.springframework.cache.annotation.CachePut
import org.springframework.cache.annotation.Cacheable
import org.springframework.cache.annotation.Caching
import org.springframework.stereotype.Service

@Service
class AnuncioServiceImpl(private val anuncioRepository: AnuncioRepository) : AnuncioService {

    @Cacheable("anuncios", unless = "#result.size() == 0")
    override fun getAll(): MutableList<Anuncio> {
        return anuncioRepository.findAll()
    }

    @Cacheable("anuncio", key = "#id")
    override fun recuperarPorId(id: Long): Anuncio {
        return anuncioRepository.findById(id).get()
    }

    @Caching(
            put = [CachePut("anuncio", key = "#result.id")],
            evict = [CacheEvict("anuncios", allEntries = true)]
    )
    override fun adicionar(anuncio: Anuncio): Anuncio {
        return anuncioRepository.save(anuncio)
    }

    @Caching(
            evict = [
                CacheEvict("anuncio", key = "#id"),
                CacheEvict("anuncios", allEntries = true)
            ]
    )
    override fun excluirPorId(id: Long) {
        anuncioRepository.deleteById(id)
    }

    @Caching(
            evict = [CacheEvict("anuncio", key = "#anuncio.id"),
                     CacheEvict("anuncios", allEntries = true)
            ]
    )
    override fun alterar(anuncio: Anuncio): Anuncio {
        return anuncioRepository.save(anuncio)
    }


}