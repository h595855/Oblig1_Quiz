package no.hvl.dat153;

import androidx.appcompat.app.AppCompatActivity;
import no.hvl.dat153.MainAcitivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainAcitivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button startButton = findViewById(R.id.Start);
        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainAcitivity.this, GameActivity.class);
                startActivity(intent);
            }
        });
    }
}