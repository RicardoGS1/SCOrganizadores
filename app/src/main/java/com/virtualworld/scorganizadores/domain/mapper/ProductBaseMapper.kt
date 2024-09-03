package com.virtualworld.scorganizadores.domain.mapper

interface ProductBaseMapper<I, O> {
    fun map(input: I): O
}
