package com.neopixl.lesmovies

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.mikepenz.fastadapter.FastAdapter
import com.mikepenz.fastadapter.IAdapter
import com.mikepenz.fastadapter.adapters.ItemAdapter
import com.neopixl.lesmovies.item.MovieItem
import com.neopixl.lesmovies.model.Movie
import com.neopixl.lesmovies.model.MovieResult
import com.neopixl.lesmovies.network.MovieService
import kotlinx.android.synthetic.main.activity_add_movie.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class AddMovieActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_movie)

        var layoutManager = LinearLayoutManager(this)
        recylcerViewSearch.layoutManager = layoutManager

        val retrofit = Retrofit.Builder()
            .baseUrl("http://api.themoviedb.org/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        movieService = retrofit.create(MovieService::class.java)

        button.setOnClickListener {
            val searchQuery = editSearch.text.toString()
            if(!searchQuery.isBlank()){
                movieService.getMovies(searchQuery).enqueue(object: Callback<MovieResult>{
                    override fun onFailure(call: Call<MovieResult>, t: Throwable) {
                        Log.d('Movie', 'Error' + t.localizedMessage)
                    }

                    override fun onResponse(
                        call: Call<MovieResult>,
                        response: Response<MovieResult>
                    ) {
                        val results = response.body()?.results ?: emptyList<Movie>()

                        val itemAdapter = ItemAdapter<MovieItem>()
                        itemAdapter.add(results.map { MovieItem(it) })

                        val fastAdapter = FastAdapter.with(itemAdapter)
                        recylcerViewSearch.adapter = fastAdapter

                        recylcerViewSearch.addItemDecoration(DividerItemDecoration(this@AddMovieActivity, RecyclerView.VERTICAL))

                        fastAdapter.onClickListener = { view: View?, iAdapter: IAdapter<MovieItem>, movieItem: MovieItem, i:Int ->
                            val movie = movieItem.movie
                        }

                    }
                })
            }
        }
    }


}
