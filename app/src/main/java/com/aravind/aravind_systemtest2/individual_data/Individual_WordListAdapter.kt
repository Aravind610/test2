package com.aravind.aravind_systemtest2.individual_data

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.aravind.aravind_systemtest2.R


class individual_WordListAdapter internal constructor(
        context: Context, val viewModel: individual_WordViewModel
) : RecyclerView.Adapter<individual_WordListAdapter.WordViewHolder>() {

    private var contactList = ArrayList<individualWord>()
    private var contactListFiltered: List<individualWord>? = null


    private val inflater: LayoutInflater = LayoutInflater.from(context)
    private var words = ArrayList<individualWord>() // Cached copy of words
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
            delete(position)
        })

    }

    fun getFilter(): Filter? {
        return object : Filter() {
            override fun performFiltering(charSequence: CharSequence): FilterResults {
                val charString = charSequence.toString()
                if (charString.isEmpty()) {
                        words = contactList
                } else {
                    val filteredList: ArrayList<individualWord> = java.util.ArrayList<individualWord>()
                    if (contactList != null) {
                        for (row in contactList) {


                            if (row.word.toLowerCase().contains(charString.toLowerCase()) || row.word.contains(charSequence)) {
                                filteredList.add(row)
                            }

                        }
                    }
                    words = filteredList
                }
                val filterResults = FilterResults()
                filterResults.values = words
                return filterResults
            }

            override fun publishResults(charSequence: CharSequence, filterResults: FilterResults) {
                words = filterResults.values as java.util.ArrayList<individualWord>
                notifyDataSetChanged()
            }
        }
    }

    private fun delete(pos: Int){
        viewModel.delete(words[pos])
        words.removeAt(pos)
        notifyItemRemoved(pos)
    }

    internal fun setWords(individualWords: List<individualWord>) {
        this.words = individualWords as ArrayList<individualWord>
        notifyDataSetChanged()
    }

    override fun getItemCount() = words.size
}


