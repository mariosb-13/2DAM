package es.iescarrillo.pruebaroom.entidades;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "users")
public class User {
    @PrimaryKey
    public long userId;

    @ColumnInfo(name = "first_name")
    public String firstName;

    @ColumnInfo(name = "age")
    public int age;

    public User(String firstName, long userId, int age) {
        this.firstName = firstName;
        this.userId = userId;
        this.age = age;
    }
}
