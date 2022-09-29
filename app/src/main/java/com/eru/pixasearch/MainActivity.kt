package com.eru.pixasearch

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.eru.pixasearch.databinding.ActivityMainBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    var imageAdapter = ImageAdapter(arrayListOf())
    private var page = 1
    private lateinit var binding: ActivityMainBinding
    private var perPage = 10


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initClicker()

        binding.recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener(){

            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                val linearLayoutManager = recyclerView.layoutManager as LinearLayoutManager
                if (linearLayoutManager.findLastVisibleItemPosition() == linearLayoutManager.itemCount - 1
                    && !recyclerView.canScrollVertically(1)){
                    page++
                    doRequest()
                }
            }
        })
    }


    private fun initClicker() {
        with(binding){
            changePageBtn.setOnClickListener {
                ++page
                doRequest()

            }
            requestBtn.setOnClickListener {
                doRequest()
            }
        }
    }

    private fun doRequest() {
        App.api.getImages(keyWord = binding.keyWordEt.text.toString(), page = page, perPage = perPage)
            .enqueue(object : Callback<PixaModel> {
                override fun onResponse(call: Call<PixaModel>, response: Response<PixaModel>) {
                    if (response.isSuccessful) {
                        response.body()?.hits?.forEach {
                            imageAdapter.addImage(it)
                        }
                        binding.recyclerView.adapter = imageAdapter
                    }
                }

                override fun onFailure(call: Call<PixaModel>, t: Throwable) {
                    Log.e("lol", "${t.message}")
                }
            })
    }
}