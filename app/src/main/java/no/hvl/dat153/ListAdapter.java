package no.hvl.dat153;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

import no.hvl.dat153.data.Animal;

public class ListAdapter extends ArrayAdapter<Animal> {

    private Context context;
    private List<Animal> listItems;

    public ListAdapter(@NonNull Context context, @LayoutRes int resource, @NonNull List<Animal> objects) {
        super(context, resource, objects);
        this.context = context;
        this.listItems = objects;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.animalitem, parent, false);

        ImageView imageView = view.findViewById(R.id.image_view);
        TextView textView = view.findViewById(R.id.text_view);

        Animal listItem = listItems.get(position);
        imageView.setImageBitmap(listItem.getImage());
        textView.setText(listItem.getName());

        return view;
    }
}
