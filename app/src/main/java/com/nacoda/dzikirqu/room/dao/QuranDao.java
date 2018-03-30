package com.nacoda.dzikirqu.room.dao;


import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.nacoda.dzikirqu.model.quran.QuranModel;

import java.util.List;

@Dao
public interface QuranDao {

    @Query("SELECT * FROM quran")
    List<QuranModel> getQuran();

    @Insert
    void insertQuran(List<QuranModel> data);

    @Query("SELECT * FROM quran WHERE id LIKE :id AND language LIKE :language")
    QuranModel getQuranByIdAndLanguage(String id, String language);

}
