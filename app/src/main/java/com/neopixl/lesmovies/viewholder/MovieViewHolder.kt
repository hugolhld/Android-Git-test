package com.neopixl.lesmovies.viewholder

import android.view.View
import com.bumptech.glide.Glide
import com.mikepenz.fastadapter.FastAdapter
import com.neopixl.lesmovies.item.MovieItem
import kotlinx.android.synthetic.main.row_movie.view.*

class MovieViewHolder(itemView: View): FastAdapter.ViewHolder<MovieItem>(itemView) {
    override fun bindView(item: MovieItem, payloads: MutableList<Any>) {
        val movie = item.movie

        itemView.textViewTitle.text = movie.title

        if (movie.posterPath != null) {
            Glide.with(itemView.movieImageView)
                .load("https://image.tmdb.org/t/p/w200" + movie.posterPath)
                .into(itemView.movieImageView)
        }
    }

    override fun unbindView(item: MovieItem) {
        itemView.textViewTitle.text = null
        itemView.movieImageView.setImageDrawable(null)
    }

}
