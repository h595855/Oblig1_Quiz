package no.hvl.dat153;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.ImageDecoder;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import java.io.IOException;

public class AddPictureActivity extends AppCompatActivity {

    //preview of the selected image
    ImageView imageView;

    //upload button
    Button uploadButton;

    // constant to compare
    // the activity result code
    int SELECT_PICTURE = 200;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_picture);

        // register the UI widgets with their appropriate IDs
        imageView = findViewById(R.id.imageView);
        uploadButton = findViewById(R.id.uploadButton);

        /*
        handle the Choose Image button to trigger
        the image chooser function
         */
        uploadButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imagePicker();
            }
        });
    }

    /*
    function is triggered when button is clicked
     */
    private void imagePicker()
    {
        /*
        creates instance of intent of type image
         */
        Intent i = new Intent();
        i.setType("image/*");
        i.setAction(Intent.ACTION_GET_CONTENT);

        //launch the activity to add picture
        addPictureActivity.launch(i);
    }


    ActivityResultLauncher<Intent> addPictureActivity
            = registerForActivityResult(
            new ActivityResultContracts
            .StartActivityForResult(),
    result -> {
        if (result.getResultCode()
                == Activity.RESULT_OK) {
            Intent data = result.getData();
            // do your operation from here....
            if (data != null && data.getData() != null) {
                Uri selectedImageUri = data.getData();
                ImageDecoder.Source source = ImageDecoder.createSource(this.getContentResolver(), selectedImageUri);
                try {
                    Bitmap selectedImageBitmap = ImageDecoder.decodeBitmap(source);
                    imageView.setImageBitmap(selectedImageBitmap);
                }
                catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    });



}//class





