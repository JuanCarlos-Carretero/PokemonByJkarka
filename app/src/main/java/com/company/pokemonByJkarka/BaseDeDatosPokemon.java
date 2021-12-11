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
public abstract class BaseDeDatosPokemon extends RoomDatabase {

    public abstract ElementosDaoPokemon obtenerElementosDao();

    private static volatile BaseDeDatosPokemon INSTANCIA;

    static BaseDeDatosPokemon obtenerInstancia(final Context context) {
        if (INSTANCIA == null) {
            synchronized (BaseDeDatosPokemon.class) {
                if (INSTANCIA == null) {
                    INSTANCIA = Room.databaseBuilder(context,
                            BaseDeDatosPokemon.class, "BaseDeDatosPokemon.db")
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
    interface ElementosDaoPokemon {
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

        @Query("SELECT * FROM Pokemon WHERE tipoNLM = 'Legendario' ORDER BY nPokedex")
        LiveData<List<Pokemon>> LegendarioPokedex();

        @Query("SELECT * FROM Pokemon WHERE tipoNLM = 'Mitico' ORDER BY nPokedex")
        LiveData<List<Pokemon>> MiticoPokedex();

        @Query("SELECT * FROM Pokemon WHERE nombre LIKE '%' || :d || '%'")
        LiveData<List<Pokemon>> buscar(String d);
    }
}
