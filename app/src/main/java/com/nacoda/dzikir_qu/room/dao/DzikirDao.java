package com.nacoda.dzikir_qu.room.dao;


import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.nacoda.dzikir_qu.model.dzikir.Dzikir;

import java.util.List;

@Dao
public interface DzikirDao {

    @Query("SELECT * FROM dzikir")
    List<Dzikir> getDzikir();

    @Insert
    void insertDzikir(List<Dzikir> data);

}
