package com.example.tarabuttest.ui

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout.OnRefreshListener
import com.example.myclassifieds.adapter.MatchAdapter
import com.example.tarabuttest.R
import com.example.tarabuttest.model.Match
import com.example.tarabuttest.util.Status
import com.example.tarabuttest.viewmodel.MatchViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(),MatchAdapter.CellClickListener {

    private lateinit var viewModel: MatchViewModel
    private lateinit var adapter: MatchAdapter
    lateinit var swipeContainer: SwipeRefreshLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        swipeContainer = findViewById(R.id.swipeContainer)

        viewModel = ViewModelProviders.of(this).get(MatchViewModel::class.java)

        setupUI()
        setupObservers()

        swipeContainer.setOnRefreshListener(OnRefreshListener {
            swipeContainer.isRefreshing = false

            setupUI()
            setupObservers()
        })
    }

    // add the list to recyclerview
    private fun setupUI() {
        matchView.layoutManager = LinearLayoutManager(this@MainActivity)
        adapter = MatchAdapter(arrayListOf(),this)
        matchView.adapter = adapter
    }


    // get the data from api call
    private fun setupObservers() {

        viewModel.getMatch1().observe(this, Observer {
            it?.let { resource ->
                when (resource.status) {
                    Status.SUCCESS -> {
                        matchView.visibility = View.VISIBLE
                        setupNextObservers(resource.data!!.body()!!  as ArrayList<Match>)
                    }
                    Status.ERROR -> {
                        matchView.visibility = View.VISIBLE
                    }
                    Status.LOADING -> {
                        matchView.visibility = View.VISIBLE
                    }
                }
            }
        })
    }

    private fun setupNextObservers(arrayList: ArrayList<Match>) {

        viewModel.getMatch2().observe(this, Observer {
            it?.let { resource ->
                when (resource.status) {
                    Status.SUCCESS -> {
                        matchView.visibility = View.VISIBLE
                        arrayList.addAll(resource.data!!.body()!!  as ArrayList<Match>)
                        retrieveList(arrayList)
                        saveInDB(arrayList)
                        Log.e("result%%",""+ arrayList)
                    }
                    Status.ERROR -> {
                        matchView.visibility = View.VISIBLE
                    }
                    Status.LOADING -> {
                        matchView.visibility = View.VISIBLE
                    }
                }
            }
        })
    }

    // save data to db
    private fun saveInDB(result: MutableList<Match>) {
        viewModel.deleteMatch();

        for (i in result) {
            viewModel.insertMatch(i);

        }
    }

    private  fun retrieveList(users: ArrayList<Match>) {

        adapter.apply {
            addUsers(users)
            notifyDataSetChanged()
        }


    }

    override fun onCellClickListener(data: Match) {

        val intent = Intent (this@MainActivity, MatchDetailActivity::class.java)
        intent.putExtra("match", data)
        startActivity(intent)

    }
}
