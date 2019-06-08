package com.digitalexplorers.tipify.mvp

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import com.digitalexplorers.tipify.R
import kotlinx.android.synthetic.main.activity_element.*

class ElementActivity : AppCompatActivity(), ListContract.View {

    var presenter = ElementPresenter(this)
    var elementt: List<Element> = ArrayList<Element>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_element)
        presenter.loadElements()
    }

    override fun setElements(element: List<Element> ) {
        elementt = element

        if(elementt.isEmpty()){
            Toast.makeText(this@ElementActivity, "EMPTY LIST", Toast.LENGTH_LONG).show()
            Log.e("@MVP","EMPTY LIST" )
            return
        }
            Toast.makeText(this@ElementActivity, elementt.toString() , Toast.LENGTH_LONG).show()
            Log.e("@MVP", elementt.toString() )

    }

    override fun showLoading() {
        progress.visibility = View.VISIBLE
    }

    override fun hideLoading() {
        progress.visibility = View.GONE
    }

    override fun showError(str: String) {
        Toast.makeText(this@ElementActivity, "ERROR"+" "+str, Toast.LENGTH_LONG).show()
        Log.e("@MVP", "ERROR"+" "+str )
    }
}
