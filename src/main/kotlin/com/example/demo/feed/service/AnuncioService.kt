package com.example.demo.feed.service

import com.example.demo.feed.model.Anuncio
import com.example.demo.feed.model.AnuncioDTO
import com.example.demo.feed.repository.AnuncioRepository
import org.springframework.cache.annotation.Cacheable
import org.springframework.stereotype.Service

interface AnuncioService {
    fun getAll(): MutableList<Anuncio>

    fun recuperarPorId(id: Long): Anuncio

    fun adicionar(anuncio: Anuncio): Anuncio

    fun excluirPorId(id: Long)

    fun alterar(anuncio: Anuncio): Anuncio
}