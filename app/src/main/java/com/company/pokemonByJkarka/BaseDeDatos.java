package com.company.pokemonByJkarka;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Database;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.Update;
import androidx.sqlite.db.SupportSQLiteDatabase;

import java.util.List;

@Database(entities = {Pokemon.class}, version = 1, exportSchema = false)
public abstract class BaseDeDatos extends RoomDatabase {

    public abstract ElementosDao obtenerElementosDao();

    private static volatile BaseDeDatos INSTANCIA;

    static BaseDeDatos obtenerInstancia(final Context context) {
        if (INSTANCIA == null) {
            synchronized (BaseDeDatos.class) {
                if (INSTANCIA == null) {
                    INSTANCIA = Room.databaseBuilder(context,
                            BaseDeDatos.class, "PokemonDtBs.db")
                            .fallbackToDestructiveMigration()
                            .addCallback(new Callback() {
                                @Override
                                public void onCreate(@NonNull SupportSQLiteDatabase db) {
                                    super.onCreate(db);
                                }
                            })
                            .build();
                }
            }
        }
        return INSTANCIA;
    }

    @Dao
    interface ElementosDao {
        @Query("SELECT * FROM Pokemon")
        LiveData<List<Pokemon>> obtener();

        @Insert
        void insertar(Pokemon pokemon);

        @Update
        void actualizar(Pokemon pokemon);

        @Delete
        void eliminar(Pokemon pokemon);

        @Query("SELECT * FROM Pokemon ORDER BY nPokedex")
        LiveData<List<Pokemon>> NumeroPokedex();

        @Query("SELECT * FROM Pokemon WHERE nombre LIKE '%' || :d || '%'")
        LiveData<List<Pokemon>> buscar(String d);
    }
}
