package es.iescarrillo.pruebaroom.entidades;

import androidx.room.Embedded;
import androidx.room.Relation;

public class UserAndLibrary {
    @Embedded
    public User user;
    @Relation(
            parentColumn = "userId",
            entityColumn = "userOwnerId"
    )
    public Library library;
}
