package es.iescarrillo.pruebaroom.dao;

import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Transaction;

import java.util.List;

import es.iescarrillo.pruebaroom.entidades.Song;
import es.iescarrillo.pruebaroom.entidades.SongWithPlaylist;

public interface SongDAO {

    @Query("SELECT * FROM song")
    List<Song> getAll();

    @Insert
    void insertAll(Song... songs);

    @Delete
    void delete(Song song);

    @Transaction
    @Query("SELECT * FROM song")
    List<SongWithPlaylist> getSongWithPlaylist();
}
