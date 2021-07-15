package com.example.tarabuttest.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.tarabuttest.model.Match


@Dao
interface MatchDao {

      @Insert(onConflict = OnConflictStrategy.REPLACE)
      fun insertMatch(snag: Match): Long

      @Insert(onConflict = OnConflictStrategy.REPLACE)
       fun updateMatchLocalDb(user: Match):Long

      @Query("SELECT * FROM 'Match' WHERE uid == :matchId")
      fun getMatch(matchId: Int): Match

      @Query("DELETE FROM `Match`")
      abstract fun deleteMatch()
}