package com.example.demo.comment.service

import com.example.demo.comment.model.Comment

interface CommentService {
    fun getAll(): MutableList<Comment>

    fun recuperarPorId(id: Long): Comment

    fun adicionar(anuncio: Comment): Comment

    fun excluirPorId(id: Long)

    fun alterar(anuncio: Comment): Comment

}