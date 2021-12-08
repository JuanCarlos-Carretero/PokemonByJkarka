package com.company.pokemonByJkarka;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.company.pokemonByJkarka.databinding.FragmentMostrarElementoBinding;


public class MostrarPokemonFragment extends Fragment {
    private FragmentMostrarElementoBinding binding;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return (binding = FragmentMostrarElementoBinding.inflate(inflater, container, false)).getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        PokemonViewModel pokemonViewModel = new ViewModelProvider(requireActivity()).get(PokemonViewModel.class);

        pokemonViewModel.seleccionado().observe(getViewLifecycleOwner(), new Observer<Pokemon>() {
            @Override
            public void onChanged(Pokemon pokemon) {
                binding.nPokedex.setText(pokemon.nPokedex);
                binding.nombre.setText(pokemon.nombre);
                binding.descripcion.setText(pokemon.descripcion);
                binding.imagen.setImageResource(pokemon.imagen);
                binding.tipo1.setImageResource(pokemon.tipo1);
                binding.tipo2.setImageResource(pokemon.tipo2);
            }
        });
    }
}