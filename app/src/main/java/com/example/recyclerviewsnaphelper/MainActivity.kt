package com.example.recyclerviewsnaphelper

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearSnapHelper
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val itemLayout = listOf("Ayam", "Kambing", "Sapi", "Buaya", "Kadal", "Semut", "Belut", "Ular")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        rv_center_snap.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            setHasFixedSize(true)
            adapter = SimpleAdapter(itemLayout)
        }.also {
            LinearSnapHelper().attachToRecyclerView(it)
        }

        rv_start_snap.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            setHasFixedSize(true)
            adapter = SimpleAdapter(itemLayout)
        }.also {
            StartSnapHelper().attachToRecyclerView(it)
        }

    }
}
