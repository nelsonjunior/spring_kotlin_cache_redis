package com.example.demo.comment.service.impl

import com.example.demo.comment.model.Comment
import com.example.demo.comment.repository.CommentRepository
import com.example.demo.comment.service.CommentService
import org.springframework.stereotype.Service

@Service
class CommentServiceImpl(private val repository: CommentRepository) : CommentService {
    override fun getAll(): MutableList<Comment> {
        return repository.findAll()
    }

    override fun recuperarPorId(id: Long): Comment {
        return repository.findById(id).get()
    }

    override fun adicionar(anuncio: Comment): Comment {
        return repository.save(anuncio)
    }

    override fun excluirPorId(id: Long) {
        repository.deleteById(id)
    }

    override fun alterar(anuncio: Comment): Comment {
        return repository.save(anuncio)
    }
}