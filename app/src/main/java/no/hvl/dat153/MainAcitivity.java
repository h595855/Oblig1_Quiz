package no.hvl.dat153;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainAcitivity extends AppCompatActivity {


    private Button hello;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        hello = (Button) findViewById(R.id.Hello);

        hello.setOnClickListener((View.OnClickListener) this);

    }

    @Override
    public void onClick(View v) {
        String text = "Test";
        Toast.makeText(MainAcitivity.this, text, Toast.LENGTH_SHORT).show();
    }
}