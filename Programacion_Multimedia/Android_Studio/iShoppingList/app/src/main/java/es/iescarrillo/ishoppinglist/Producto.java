package es.iescarrillo.ishoppinglist;

import java.io.Serializable;

public class Producto implements Serializable {
    public int id;
    public String name;
    public String note_info;
    public boolean state_buy;

    public Producto(int id, String name, String note_info, boolean state_buy) {
        this.id = id;
        this.name = name;
        this.note_info = note_info;
        this.state_buy = state_buy;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNote_info() {
        return note_info;
    }

    public void setNote_info(String note_info) {
        this.note_info = note_info;
    }

    public boolean isState_buy() {
        return state_buy;
    }

    public void setState_buy(boolean state_buy) {
        this.state_buy = state_buy;
    }

    @Override
    public String toString() {
        return name ;
    }
}
