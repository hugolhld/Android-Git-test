package com.neopixl.lesmovies.item

import android.view.View
import com.mikepenz.fastadapter.items.AbstractItem
import com.neopixl.lesmovies.R
import com.neopixl.lesmovies.model.Movie
import com.neopixl.lesmovies.viewholder.MovieViewHolder

class MovieItem(val movie: Movie): AbstractItem<MovieViewHolder>() {
    override val layoutRes: Int
        get() = R.layout.row_movie
    override val type: Int
        get() = R.id.textViewTitle

    override fun getViewHolder(v: View): MovieViewHolder {
        return MovieViewHolder(v)
    }

}