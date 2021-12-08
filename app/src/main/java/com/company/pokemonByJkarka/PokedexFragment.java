package com.company.pokemonByJkarka;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.company.pokemonByJkarka.databinding.FragmentRecyclerElementosBinding;
import com.company.pokemonByJkarka.databinding.ViewholderElementoBinding;

import java.util.List;


public class PokedexFragment extends Fragment {

    private FragmentRecyclerElementosBinding binding;
    PokemonViewModel pokemonViewModel;
    private NavController navController;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return (binding = FragmentRecyclerElementosBinding.inflate(inflater, container, false)).getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        pokemonViewModel = new ViewModelProvider(requireActivity()).get(PokemonViewModel.class);
        navController = Navigation.findNavController(view);

        binding.irANuevoElemento.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navController.navigate(R.id.action_nuevoElementoFragment);
            }
        });

        ElementosAdapter elementosAdapter;
        elementosAdapter = new ElementosAdapter();

        binding.recyclerView.setAdapter(elementosAdapter);

        binding.recyclerView.addItemDecoration(new DividerItemDecoration(requireContext(), DividerItemDecoration.VERTICAL));

        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(
                ItemTouchHelper.UP | ItemTouchHelper.DOWN,
                ItemTouchHelper.RIGHT  | ItemTouchHelper.LEFT) {

            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return true;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                int posicion = viewHolder.getAdapterPosition();
                Pokemon pokemon = elementosAdapter.obtenerElemento(posicion);
                pokemonViewModel.eliminar(pokemon);

            }
        }).attachToRecyclerView(binding.recyclerView);

        obtenerElementos().observe(getViewLifecycleOwner(), new Observer<List<Pokemon>>() {
            @Override
            public void onChanged(List<Pokemon> pokemons) {
                elementosAdapter.establecerLista(pokemons);
            }
        });
    }

    LiveData<List<Pokemon>> obtenerElementos(){
        return pokemonViewModel.obtener();
    }

    class ElementosAdapter extends RecyclerView.Adapter<ElementoViewHolder> {

        List<Pokemon> pokemons;

        @NonNull
        @Override
        public ElementoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            return new ElementoViewHolder(ViewholderElementoBinding.inflate(getLayoutInflater(), parent, false));
        }

        @Override
        public void onBindViewHolder(@NonNull ElementoViewHolder holder, int position) {

            Pokemon pokemon = pokemons.get(position);

            holder.binding.nombre.setText(pokemon.nombre);
            holder.binding.nPokedex.setText(pokemon.nPokedex);
            Glide.with(PokedexFragment.this).load(pokemon.imagen).into(holder.binding.imagen);
            Glide.with(PokedexFragment.this).load(pokemon.tipo1).into(holder.binding.tipo1);
            Glide.with(PokedexFragment.this).load(pokemon.tipo2).into(holder.binding.tipo2);

            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    pokemonViewModel.seleccionar(pokemon);
                    navController.navigate(R.id.action_mostrarElementoFragment);
                }
            });
        }

        @Override
        public int getItemCount() {
            return pokemons != null ? pokemons.size() : 0;
        }

        public void establecerLista(List<Pokemon> pokemons){
            this.pokemons = pokemons;
            notifyDataSetChanged();
        }

        public Pokemon obtenerElemento(int posicion){
            return pokemons.get(posicion);
        }
    }

    static class ElementoViewHolder extends RecyclerView.ViewHolder {
        private final ViewholderElementoBinding binding;

        public ElementoViewHolder(ViewholderElementoBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}