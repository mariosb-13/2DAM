package es.iescarrillo.pruebaroom.entidades;

import androidx.room.Embedded;
import androidx.room.Relation;

public class SongWithPlaylist {

    @Embedded
    public Song song;

    @Relation(
            parentColumn = "songId",
            entityColumn = "playlistId"

    )
    public Playlist playlist;
}
