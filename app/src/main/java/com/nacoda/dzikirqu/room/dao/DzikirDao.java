package com.nacoda.dzikirqu.room.dao;


import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.nacoda.dzikirqu.model.dzikir.DzikirModel;

import java.util.List;

@Dao
public interface DzikirDao {

    @Query("SELECT * FROM dzikir")
    List<DzikirModel> getDzikir();

    @Insert
    void insertDzikir(List<DzikirModel> data);

    @Query("SELECT * FROM dzikir WHERE id LIKE :id AND language LIKE :language")
    DzikirModel getDzikirByIdAndLanguage(String id, String language);

    @Query("SELECT * FROM dzikir WHERE language LIKE :language")
    List<DzikirModel> getDzikirByLanguage(String language);

}
