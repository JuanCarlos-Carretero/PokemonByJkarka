package com.company.pokemonByJkarka;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class PokemonRepositorio {

    BaseDeDatosPokemon.ElementosDaoPokemon elementosDaoPokemon;
    Executor executor = Executors.newSingleThreadExecutor();

    PokemonRepositorio(Application application){
        elementosDaoPokemon = BaseDeDatosPokemon.obtenerInstancia(application).obtenerElementosDao();
    }


    LiveData<List<Pokemon>> obtenerPokemon(){
        return elementosDaoPokemon.obtener();
    }

    LiveData<List<Pokemon>> ordenadosPokemon() {
        return elementosDaoPokemon.NumeroPokedex();
    }

    LiveData<List<Pokemon>> buscarPokemon(String d) {
        return elementosDaoPokemon.buscar(d);
    }


    void insertarPokemon(Pokemon pokemon){
        executor.execute(new Runnable() {
            @Override
            public void run() {
                elementosDaoPokemon.insertar(pokemon);
            }
        });
    }

    void eliminarPokemon(Pokemon pokemon) {
        executor.execute(new Runnable() {
            @Override
            public void run() {
                elementosDaoPokemon.eliminar(pokemon);
            }
        });
    }

    public void actualizarPokemon(Pokemon pokemon, String nPokemon) {
        executor.execute(new Runnable() {
            @Override
            public void run() {
                pokemon.nPokedex = nPokemon;
                elementosDaoPokemon.actualizar(pokemon);
            }
        });
    }

    LiveData<List<Pokemon>> obtenerMiticos() {
        return elementosDaoPokemon.MiticoPokedex();
    }

    LiveData<List<Pokemon>> obtenerLegendarios() {return elementosDaoPokemon.LegendarioPokedex();}
}