package com.example.demo.feed.repository

import com.example.demo.feed.model.Anuncio
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface AnuncioRepository : JpaRepository<Anuncio, Long>