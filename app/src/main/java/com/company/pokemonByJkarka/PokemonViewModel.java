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

    ElementosRepositorio elementosRepositorio;


    MutableLiveData<Pokemon> elementoSeleccionado = new MutableLiveData<>();

    MutableLiveData<String> terminoBusqueda = new MutableLiveData<>();

    LiveData<List<Pokemon>> resultadoBusqueda = Transformations.switchMap(terminoBusqueda, new Function<String, LiveData<List<Pokemon>>>() {
        @Override
        public LiveData<List<Pokemon>> apply(String input) {
            return elementosRepositorio.buscar(input);
        }
    });



    public PokemonViewModel(@NonNull Application application) {
        super(application);

        elementosRepositorio = new ElementosRepositorio(application);
    }



    LiveData<List<Pokemon>> obtener(){
        return elementosRepositorio.obtener();
    }

    LiveData<List<Pokemon>> masValorados(){
        return elementosRepositorio.masValorados();
    }

    LiveData<List<Pokemon>> buscar(){
        return resultadoBusqueda;
    }

    void insertar(Pokemon pokemon){
        elementosRepositorio.insertar(pokemon);
    }

    void eliminar(Pokemon pokemon){
        elementosRepositorio.eliminar(pokemon);
    }

    void actualizar(Pokemon pokemon, String nPokemon){
        elementosRepositorio.actualizar(pokemon, nPokemon);
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
}