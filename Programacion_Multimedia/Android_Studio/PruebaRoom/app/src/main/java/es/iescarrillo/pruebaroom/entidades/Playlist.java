package es.iescarrillo.pruebaroom.entidades;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "playlist")
public class Playlist {

    @PrimaryKey
    public long playlistId;

    public long userCreatorId;

    @ColumnInfo(name = "playlist_name")
    public String playlistName;


}
