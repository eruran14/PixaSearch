package com.eru.pixasearch

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.eru.pixasearch.databinding.ActivityMainBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    var imageAdapter = ImageAdapter(mutableListOf())
    private var page = 1
    lateinit var binding: ActivityMainBinding
    private var list = listOf<ImageModel>()
    private var perPage = 10

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initClicker()
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

            loadMoreBtn.setOnClickListener {
                perPage += 10
                doRequest()
            }
        }
    }

    private fun ActivityMainBinding.doRequest() {
        App.api.getImages(keyWord = keyWordEt.text.toString(), page = page, perPage = perPage)
            .enqueue(object : Callback<PixaModel> {
                override fun onResponse(call: Call<PixaModel>, response: Response<PixaModel>) {
                    if (response.isSuccessful) {
                        list = response.body()?.hits!!
                        imageAdapter = ImageAdapter(list)
                        binding.recyclerView.adapter = imageAdapter

                    }
                }

                override fun onFailure(call: Call<PixaModel>, t: Throwable) {
                    Log.e("lol", "${t.message}")
                }
            })
    }
}