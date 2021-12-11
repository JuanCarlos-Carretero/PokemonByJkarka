package com.company.pokemonByJkarka;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.company.pokemonByJkarka.databinding.FragmentNuevoPokemonBinding;


public class NuevoPokemonFragment extends Fragment {
    private FragmentNuevoPokemonBinding binding;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return (binding = FragmentNuevoPokemonBinding.inflate(inflater, container, false)).getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        PokemonViewModel pokemonViewModel = new ViewModelProvider(requireActivity()).get(PokemonViewModel.class);
        NavController navController = Navigation.findNavController(view);

        binding.crear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nombre = binding.nombre.getText().toString();
                String descripcion = binding.descripcion.getText().toString();

                switch(nombre){
                    case "Bulbasaur":
                        pokemonViewModel.insertar(new Pokemon("001", R.drawable.bulbasaur, R.drawable.planta, R.drawable.veneno, nombre, descripcion, "Normal"));
                        break;
                    case "Ivysaur":
                        pokemonViewModel.insertar(new Pokemon("002", R.drawable.ivysaur, R.drawable.planta, R.drawable.veneno, nombre, descripcion, "Normal"));
                        break;
                    case "Venusaur":
                        pokemonViewModel.insertar(new Pokemon("003", R.drawable.venusaur, R.drawable.planta, R.drawable.veneno, nombre, descripcion, "Normal"));
                        break;
                    case "Charmander":
                        pokemonViewModel.insertar(new Pokemon("004", R.drawable.charmander, R.drawable.fuego, R.drawable.vacio, nombre, descripcion, "Normal"));
                        break;
                    case "Charmeleon":
                        pokemonViewModel.insertar(new Pokemon("005", R.drawable.charmeleon, R.drawable.fuego, R.drawable.vacio, nombre, descripcion, "Normal"));
                        break;
                    case "Charizard":
                        pokemonViewModel.insertar(new Pokemon("006", R.drawable.charizard, R.drawable.fuego, R.drawable.volador, nombre, descripcion, "Normal"));
                        break;
                    case "Squirtle":
                        pokemonViewModel.insertar(new Pokemon("007", R.drawable.squirtle, R.drawable.agua, R.drawable.vacio, nombre, descripcion, "Normal"));
                        break;
                    case "Wartortle":
                        pokemonViewModel.insertar(new Pokemon("008", R.drawable.wartortle, R.drawable.agua, R.drawable.vacio, nombre, descripcion, "Normal"));
                        break;
                    case "Blastoise":
                        pokemonViewModel.insertar(new Pokemon("009", R.drawable.blastoise, R.drawable.agua, R.drawable.vacio, nombre, descripcion, "Normal"));
                        break;
                    case "Pikachu":
                        pokemonViewModel.insertar(new Pokemon("025", R.drawable.pikachu, R.drawable.electrico, R.drawable.vacio, nombre, descripcion, "Normal"));
                        break;
                    case "Raichu":
                        pokemonViewModel.insertar(new Pokemon("026", R.drawable.raichu, R.drawable.electrico, R.drawable.vacio, nombre, descripcion, "Normal"));
                        break;
                    case "Mewtwo":
                        pokemonViewModel.insertar(new Pokemon("150", R.drawable.mewtwo, R.drawable.psiquico, R.drawable.vacio, nombre, descripcion, "Legendario"));
                        break;
                    case "Mew":
                        pokemonViewModel.insertar(new Pokemon("151", R.drawable.mew, R.drawable.psiquico, R.drawable.vacio, nombre, descripcion, "Mitico"));
                        break;
                    case "Pichu":
                        pokemonViewModel.insertar(new Pokemon("172", R.drawable.pichu, R.drawable.electrico, R.drawable.vacio, nombre, descripcion, "Normal"));
                        break;
                    case "Dialga":
                        pokemonViewModel.insertar(new Pokemon("483", R.drawable.dialga, R.drawable.acero, R.drawable.dragon, nombre, descripcion, "Legendario"));
                        break;
                    case "Palkia":
                        pokemonViewModel.insertar(new Pokemon("484", R.drawable.palkia, R.drawable.agua, R.drawable.dragon, nombre, descripcion, "Legendario"));
                        break;
                    case "Giratina":
                        pokemonViewModel.insertar(new Pokemon("487", R.drawable.giratina, R.drawable.fantasma, R.drawable.dragon, nombre, descripcion, "Legendario"));
                        break;
                    case "Manaphy":
                        pokemonViewModel.insertar(new Pokemon("490", R.drawable.manaphy, R.drawable.agua, R.drawable.vacio, nombre, descripcion, "Mitico"));
                        break;
                    case "Arceus":
                        pokemonViewModel.insertar(new Pokemon("493", R.drawable.arceus, R.drawable.normal, R.drawable.vacio, nombre, descripcion, "Mitico"));
                        break;
                    default:
                        pokemonViewModel.insertar(new Pokemon("?", R.drawable.missingno, R.drawable.vacio, R.drawable.vacio, nombre, descripcion, "No existe"));
                        break;
                }

                navController.popBackStack();
            }
        });
    }
}