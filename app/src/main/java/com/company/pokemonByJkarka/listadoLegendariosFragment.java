package com.company.pokemonByJkarka;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;


public class listadoLegendariosFragment extends PokedexFragment {
    @Override
    LiveData<List<Pokemon>> obtenerElementos(){return pokemonViewModel.obtenerLegendarios();}
}