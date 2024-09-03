package com.virtualworld.scorganizadores.domain.mapper



//product->productEntry
//productEntry->productUI
interface ProductListMapper<I, O> : ProductBaseMapper<List<I>, List<O>>
