package no.hvl.dat153.util;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

import no.hvl.dat153.adapters.AnimalListAdapter;
import no.hvl.dat153.data.Animal;
import no.hvl.dat153.data.AnimalViewModel;

public class SwipeToDeleteCallback extends ItemTouchHelper.SimpleCallback {

    private AnimalListAdapter adapter;
    private AnimalViewModel viewModel;

    public SwipeToDeleteCallback(AnimalListAdapter adapter, AnimalViewModel viewModel) {
        super(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT);
        this.adapter = adapter;
        this.viewModel = viewModel;
    }

    @Override
    public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
        return false;
    }

    @Override
    public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
        int position = viewHolder.getAdapterPosition();
        Animal animal = adapter.getAnimalList().get(position);
        viewModel.deleteAnimal(animal);
        adapter.notifyItemRemoved(position);
    }
}

