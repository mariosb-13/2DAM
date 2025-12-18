package es.iescarrillo.pruebaroom.entidades;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Library {

    @PrimaryKey
    public long lybraryId;
    public long userOwnerId;
    public String name;

    public Library(long lybraryId, long userOwnerId, String name) {
        this.lybraryId = lybraryId;
        this.userOwnerId = userOwnerId;
        this.name = name;
    }
}
