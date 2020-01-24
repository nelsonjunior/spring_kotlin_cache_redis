package com.example.demo

import com.example.demo.feed.model.Anuncio
import com.example.demo.feed.repository.AnuncioRepository
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cache.annotation.EnableCaching
import org.springframework.context.annotation.Bean

@SpringBootApplication
@EnableCaching
class DemoApplication{
	@Bean
	fun init(anuncioRepository: AnuncioRepository) = CommandLineRunner {
		anuncioRepository.save(Anuncio(id = -1, titulo = "Oferta 01", resumo = "Resumo da oferta 01"))
		anuncioRepository.save(Anuncio(id = -1, titulo = "Oferta 02", resumo = "Resumo da oferta 02"))
	}
}

fun main(args: Array<String>) {
	runApplication<DemoApplication>(*args)
}
