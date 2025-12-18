package es.iescarrillo.pruebaroom.entidades;

import androidx.room.Embedded;
import androidx.room.Relation;

import java.util.List;

public class UserWithPlaylist {
    @Embedded
    public User user;

    @Relation(
            parentColumn = "userId",
            entityColumn = "userCreatorId"
    )

    public List<Playlist> playlists; // Se ha añadido el nombre "playlists" a la variable
}
