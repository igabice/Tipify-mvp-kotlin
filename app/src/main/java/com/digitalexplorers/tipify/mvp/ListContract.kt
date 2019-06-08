package com.digitalexplorers.tipify.mvp

interface ListContract {

    interface View {
        fun setElements(element: List<Element>)
        fun showLoading()
        fun hideLoading()
        fun showError(str: String)

    }
}