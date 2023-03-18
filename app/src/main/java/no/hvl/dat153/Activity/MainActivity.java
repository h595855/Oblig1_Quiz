package no.hvl.dat153.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Switch;

import androidx.appcompat.app.AppCompatActivity;

import no.hvl.dat153.R;

public class MainActivity extends AppCompatActivity {

    //declare
    private Switch mSwitch;
    private Boolean isCheckedo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button startButton = findViewById(R.id.Start);
        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                mSwitch = findViewById(R.id.hardMode);
                isCheckedo = mSwitch.isChecked();
                //checking if the switch is put on
                mSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                        isCheckedo = isChecked;
                    }
                });

                //passing it to game activity
                Intent intent = new Intent(MainActivity.this, GameActivity.class);
                intent.putExtra("timerEnabled", isCheckedo);
                startActivity(intent);
            }
        });

        Button Database = findViewById(R.id.database);
        Database.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, DatabaseActivity.class);
                startActivity(intent);
            }
        });
    }
}