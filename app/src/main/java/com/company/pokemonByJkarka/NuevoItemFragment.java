package com.company.pokemonByJkarka;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.company.pokemonByJkarka.databinding.FragmentNuevoElementoBinding;

public class NuevoItemFragment extends Activity {private FragmentNuevoElementoBinding binding;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return (binding = FragmentNuevoElementoBinding.inflate(inflater, container, false)).getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ItemViewModel itemViewModel = new ViewModelProvider(requireActivity()).get(ItemViewModel.class);
        NavController navController = Navigation.findNavController(view);

        binding.crear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nombre = binding.nombre.getText().toString();

                switch(nombre){
                    case "Pocion":
                        itemViewModel.insertar(Item(nombre, "Utilizarla aumentara los PS de un Pokemon", nItem, R.drawable.pocion, 200, 100));
                        break;
                    case "Superpocion":
                        itemViewModel.insertar(Item(nombre, "Utilizarla aumentara mucho los PS de un Pokemon", nItem, R.drawable.superpocion, 700, 350));
                        break;
                    default:
                        itemViewModel.insertar(Item(nombre, "?", nItem, R.drawable.missingno));
                        break;
                }

                navController.popBackStack();
            }
        });
    }

}
