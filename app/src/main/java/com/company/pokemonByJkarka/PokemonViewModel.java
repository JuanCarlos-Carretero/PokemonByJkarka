package com.company.pokemonByJkarka;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.arch.core.util.Function;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;

import java.util.List;

public class PokemonViewModel extends AndroidViewModel {

    PokemonRepositorio pokemonRepositorio;

    MutableLiveData<Pokemon> elementoSeleccionado = new MutableLiveData<>();

    MutableLiveData<String> terminoBusqueda = new MutableLiveData<>();

    LiveData<List<Pokemon>> resultadoBusqueda = Transformations.switchMap(terminoBusqueda, new Function<String, LiveData<List<Pokemon>>>() {
        @Override
        public LiveData<List<Pokemon>> apply(String input) {
            return pokemonRepositorio.buscarPokemon(input);
        }
    });

    public PokemonViewModel(@NonNull Application application) {
        super(application);

        pokemonRepositorio = new PokemonRepositorio(application);
    }

    LiveData<List<Pokemon>> obtener(){
        return pokemonRepositorio.obtenerPokemon();
    }

    LiveData<List<Pokemon>> ordenados(){
        return pokemonRepositorio.ordenadosPokemon();
    }

    LiveData<List<Pokemon>> buscar(){
        return resultadoBusqueda;
    }

    void insertar(Pokemon pokemon){
        pokemonRepositorio.insertarPokemon(pokemon);
    }

    void eliminar(Pokemon pokemon){
        pokemonRepositorio.eliminarPokemon(pokemon);
    }

    void actualizar(Pokemon pokemon, String nPokemon){
        pokemonRepositorio.actualizarPokemon(pokemon, nPokemon);
    }

    void seleccionar(Pokemon pokemon){
        elementoSeleccionado.setValue(pokemon);
    }

    MutableLiveData<Pokemon> seleccionado(){
        return elementoSeleccionado;
    }

    void establecerTerminoBusqueda(String s){
        terminoBusqueda.setValue(s);
    }

    LiveData<List<Pokemon>> obtenerMiticos() {
        return pokemonRepositorio.obtenerMiticos();
    }

    LiveData<List<Pokemon>> obtenerLegendarios() {return pokemonRepositorio.obtenerLegendarios();}
}

