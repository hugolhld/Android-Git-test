package com.neopixl.lesmovies

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.neopixl.lesmovies.model.Movie
import kotlinx.android.synthetic.main.activity_detail_movie.*
import kotlinx.android.synthetic.main.row_movie.view.*

class DetailMovieActivity : AppCompatActivity() {

    companion object {
        val movie_extra = "movie_extra"

        fun createIntent(context: Context, movie: Movie): Intent {
            val intent = Intent(context, DetailMovieActivity::class.java)
            intent.putExtra(movie_extra, movie)
            return intent
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_movie)

        val movie = intent.getParcelableExtra<Movie>(movie_extra)

        title = movie.title
        textViewDetailReleaseYear.text = movie.releaseDate
        textViewSynopsis.text = movie.overview

        if (movie.posterPath != null) {
            Glide.with(movieDetailImageView)
                .load("https://image.tmdb.org/t/p/w500" + movie.posterPath)
                .into(movieDetailImageView)
        }
    }
}
