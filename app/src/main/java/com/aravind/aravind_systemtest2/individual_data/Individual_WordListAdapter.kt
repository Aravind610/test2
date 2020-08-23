package com.aravind.aravind_systemtest2.individual_data

import android.content.Context
import android.content.Intent
import android.os.AsyncTask
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.aravind.aravind_systemtest2.NewWordActivity
import com.aravind.aravind_systemtest2.R


class individual_WordListAdapter internal constructor(
        context: Context
) : RecyclerView.Adapter<individual_WordListAdapter.WordViewHolder>() {

    private lateinit var individualWordViewModel: individual_WordViewModel

    private val inflater: LayoutInflater = LayoutInflater.from(context)
    private var words = emptyList<individualWord>() // Cached copy of words
    private var select = emptyList<individualWord>()

    inner class WordViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val wordItemView: TextView = itemView.findViewById(R.id.textView)
        val delete: TextView = itemView.findViewById(R.id.delete)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WordViewHolder {
        val itemView = inflater.inflate(R.layout.list_design, parent, false)
        return WordViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: WordViewHolder, position: Int) {
        val current = words[position]
        holder.wordItemView.text = current.word

        holder.delete.setOnClickListener(View.OnClickListener {
           // val word = individualWord(data.getStringExtra(NewWordActivity.EXTRA_REPLY))
          //  individualWordViewModel.delete(current)
        })

    }

    internal fun setWords(individualWords: List<individualWord>) {
        this.words = individualWords
        notifyDataSetChanged()
    }

    override fun getItemCount() = words.size
}


