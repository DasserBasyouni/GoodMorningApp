package com.tech.futureteric.backend.database;

import com.tech.futureteric.backend.model.Message;

import java.util.List;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

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
