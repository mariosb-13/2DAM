package es.iescarrillo.pruebaroom.dao;

import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

import es.iescarrillo.pruebaroom.entidades.Playlist;

public interface PlaylistDAO {
    @Query("SELECT * FROM playlist")
    List<Playlist> getAll();

    @Insert
    void insertAll(Playlist... playlists);

    @Delete
    void delete(Playlist playlist);


    /**
     * Relacion N:N
     * Método que devuelve todas las instancias de la clase de datos que vincula la entidad principal (Playlist) con la secundaria (Song)
     * @return
     */
    @Query("SELECT * FROM playlist")
    List<Playlist> getPlaylistWithSongs();
}
