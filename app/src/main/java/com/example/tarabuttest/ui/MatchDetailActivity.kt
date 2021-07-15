package com.example.tarabuttest.ui

import android.os.Bundle
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import com.bumptech.glide.Glide
import com.example.tarabuttest.R
import com.example.tarabuttest.model.Match
import com.example.tarabuttest.viewmodel.MatchViewModel
import kotlinx.android.synthetic.main.activity_detail.*
import java.text.SimpleDateFormat

class MatchDetailActivity : AppCompatActivity() {

    lateinit var match: Match

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);

        val match: Match = getIntent().getParcelableExtra("match")!!

        val matchScore = match.Score
        teamAscore.text = matchScore.substring(0, 1)
        teamBscore.text = matchScore.substring(
            matchScore.length - 1,
            matchScore.length)

        Glide.with(this)
            .load(match.link_A)
            .circleCrop()
            .into(teamAimage);

        Glide.with(this)
            .load(match.link_B)
            .circleCrop()
            .into(teamBimage);

        val teamAName = match.Team_A
        val teamBName = match.Team_B

        teamAname.text = teamAName.substring(0, 3)
        teamBname.text = teamBName.substring(0, 3)

        var date = match.Date
        var spf = SimpleDateFormat("dd MMMM yyyy HH:mm")
        val newDate = spf.parse(date)
        spf = SimpleDateFormat("EEE dd MMM")
        date = spf.format(newDate)
        matchDate.text = date

        var time = match.Date
        var spf1 = SimpleDateFormat("dd MMMM yyyy HH:mm")
        val newTime = spf1.parse(time)
        spf1 = SimpleDateFormat("hh:mm a")
        time = spf1.format(newTime)
        matchTime.text = time


    }
}