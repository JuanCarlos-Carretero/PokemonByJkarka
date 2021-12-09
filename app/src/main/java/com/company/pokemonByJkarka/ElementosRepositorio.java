package com.company.pokemonByJkarka;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class ElementosRepositorio {

    BaseDeDatos.ElementosDao elementosDao;
    Executor executor = Executors.newSingleThreadExecutor();

    ElementosRepositorio(Application application){
        elementosDao = BaseDeDatos.obtenerInstancia(application).obtenerElementosDao();
    }


    LiveData<List<Pokemon>> obtener(){
        return elementosDao.obtener();
    }

    LiveData<List<Pokemon>> ordenados() {
        return elementosDao.NumeroPokedex();
    }

    LiveData<List<Pokemon>> buscar(String d) {
        return elementosDao.buscar(d);
    }


    void insertar(Pokemon pokemon){
        executor.execute(new Runnable() {
            @Override
            public void run() {
                elementosDao.insertar(pokemon);
            }
        });
    }

    void eliminar(Pokemon pokemon) {
        executor.execute(new Runnable() {
            @Override
            public void run() {
                elementosDao.eliminar(pokemon);
            }
        });
    }

    public void actualizar(Pokemon pokemon, String nPokemon) {
        executor.execute(new Runnable() {
            @Override
            public void run() {
                pokemon.nPokedex = nPokemon;
                elementosDao.actualizar(pokemon);
            }
        });
    }
}