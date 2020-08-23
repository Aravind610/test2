package com.aravind.aravind_systemtest2

import android.app.Activity
import android.app.SearchManager
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.widget.SearchView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.aravind.aravind_systemtest2.individual_data.individualWord
import com.aravind.aravind_systemtest2.individual_data.individual_WordListAdapter
import com.aravind.aravind_systemtest2.individual_data.individual_WordViewModel

import com.google.android.material.floatingactionbutton.FloatingActionButton

class individual_activity : AppCompatActivity() {

    private val newWordActivityRequestCode = 1
    private lateinit var individualWordViewModel: individual_WordViewModel
    private var searchView: SearchView? = null
    private val adapter: individual_WordListAdapter? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.individual_design)


        // Get a new or existing ViewModel from the ViewModelProvider.
        individualWordViewModel = ViewModelProvider(this).get(individual_WordViewModel::class.java)

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerview_tasks)
        val adapter = individual_WordListAdapter(this, individualWordViewModel)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        // Add an observer on the LiveData returned by getAlphabetizedWords.
        // The onChanged() method fires when the observed data changes and the activity is
        // in the foreground.
        individualWordViewModel.allWords.observe(this, Observer { words ->
            // Update the cached copy of the words in the adapter.
            words?.let { adapter.setWords(it) }
        })

        val fab = findViewById<FloatingActionButton>(R.id.floating_button_add)
        fab.setOnClickListener {
            val intent = Intent(this@individual_activity, NewWordActivity::class.java)
            startActivityForResult(intent, newWordActivityRequestCode)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.main_option_menu, menu)

        // Associate searchable configuration with the SearchView
        val searchManager = getSystemService(SEARCH_SERVICE) as SearchManager
        searchView = menu.findItem(R.id.action_search)
                .actionView as SearchView
        searchView?.setSearchableInfo(searchManager
                .getSearchableInfo(componentName))
        searchView?.setMaxWidth(Int.MAX_VALUE)


        // listening to search query text change
        searchView?.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                // filter recycler view when query submitted
                adapter?.getFilter()?.filter(query)
                return false
            }

            override fun onQueryTextChange(query: String): Boolean {
                // filter recycler view when text is changed
                adapter?.getFilter()?.filter(query)
                return false
            }
        })
        return true
    }

    override fun onBackPressed() {
        // close search view on back button pressed
        if (!searchView!!.isIconified) {
            searchView!!.isIconified = true
            return
        }
        super.onBackPressed()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, intentData: Intent?) {
        super.onActivityResult(requestCode, resultCode, intentData)

        if (requestCode == newWordActivityRequestCode && resultCode == Activity.RESULT_OK) {
            intentData?.let { data ->
                val word = individualWord(data.getStringExtra(NewWordActivity.EXTRA_REPLY))
                individualWordViewModel.insert(word)
                Unit
            }
        } else {
            Toast.makeText(
                    applicationContext,
                    "Word not saved because it is empty.",
                    Toast.LENGTH_LONG
            ).show()
        }
    }
}
