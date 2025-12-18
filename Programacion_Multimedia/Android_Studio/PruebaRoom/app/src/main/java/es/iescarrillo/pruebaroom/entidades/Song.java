package es.iescarrillo.pruebaroom.entidades;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "song")
public class Song {

    @PrimaryKey
    public long songId;

    public String title;

    public long playlistId;


}
