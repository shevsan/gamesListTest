package ua.oshevchuk.api

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import retrofit2.HttpException
import ua.oshevchuk.api.adapter.RecyclerAdapter
import ua.oshevchuk.api.data.RetrofitInstance
import ua.oshevchuk.api.databinding.ActivityMainBinding
import java.io.IOException

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var recyclerAdapter:RecyclerAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initAdapter()
        lifecycleScope.launchWhenCreated {
            binding.mainProgressBar.isVisible = true
            val response =try {
                RetrofitInstance.api.getGamesList()
            }catch (ex:IOException){
                binding.errorText.isVisible = true
                binding.errorText.text = "IOException"
                return@launchWhenCreated
            }catch (ex:HttpException){
                binding.errorText.isVisible = true
                binding.errorText.text = "HttpException"
                return@launchWhenCreated
            }
            if (response.isSuccessful&&response.body()!=null){
                recyclerAdapter.games = response.body()!!
            }else{
                return@launchWhenCreated
            }
            binding.mainProgressBar.isVisible = false
        }


    }
    private fun initAdapter()=binding.mainRecyclerView.apply{
        recyclerAdapter = RecyclerAdapter()
        adapter = recyclerAdapter
        layoutManager = LinearLayoutManager(this@MainActivity)

    }
}