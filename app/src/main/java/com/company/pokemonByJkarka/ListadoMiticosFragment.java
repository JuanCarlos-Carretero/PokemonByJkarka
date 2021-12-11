package com.company.pokemonByJkarka;


import androidx.lifecycle.LiveData;

import java.util.List;

public class ListadoMiticosFragment extends PokedexFragment {
    @Override
    LiveData<List<Pokemon>> obtenerElementos(){return pokemonViewModel.obtenerMiticos();}
}