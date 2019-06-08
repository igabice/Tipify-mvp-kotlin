package com.digitalexplorers.tipify.mvp

import io.reactivex.*;

interface ElementsProvider {
    fun loadElements(): Single<List<Element>>
}