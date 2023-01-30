package no.hvl.dat153;

import androidx.appcompat.app.AppCompatActivity;

import android.content.res.TypedArray;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

public class GameActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        TypedArray list = getResources().obtainTypedArray(R.array.images);
        for (int i = 0; i < list.length(); ++i) {
            int id = list.getResourceId(i, -1);
        }

        View id = (ImageView) findViewById(R.id.image);

    }

}