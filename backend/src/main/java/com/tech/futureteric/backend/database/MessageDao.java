package com.tech.futureteric.backend.database;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.tech.futureteric.backend.model.Message;

import java.util.List;

@Dao
public interface MessageDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertMessage(Message messages);

    @Query("SELECT * FROM Message")
    List<Message> getAll();

    @Query("SELECT * FROM Message ORDER BY msg_id DESC LIMIT 1")
    Message getUpComingMessage();

    // TODO delete msgs after a day for example
    @Delete
    void delete(Message messages);
}
