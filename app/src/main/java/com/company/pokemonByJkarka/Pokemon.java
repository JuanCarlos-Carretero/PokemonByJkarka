package com.company.pokemonByJkarka;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Pokemon {
    @PrimaryKey(autoGenerate = true)
    int id;

    int imagen;
    int tipo1;
    int tipo2;

    String nombre;
    String descripcion;
    String nPokedex;

    public Pokemon(String nPokedex, int imagen, int tipo1, Integer tipo2, String nombre, String descripcion) {
        this.nPokedex = nPokedex;
        this.imagen = imagen;
        this.tipo1 = tipo1;
        this.tipo2 = tipo2;
        this.nombre = nombre;
        this.descripcion = descripcion;
    }
}
