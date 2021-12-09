package com.company.pokemonByJkarka;

import androidx.lifecycle.LiveData;

import java.util.List;


public class PokedexAlphaFragment extends PokedexFragment {
    @Override
    LiveData<List<Pokemon>> obtenerElementos(){return pokemonViewModel.ordenados();}
}