package no.hvl.dat153;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
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

    //submit button
    Button submitButton;

    Animal animal = null;

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
        submitButton = findViewById(R.id.submitButton);

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

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                returnResult();
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

    //pics image from the gallery/filesystem
    ActivityResultLauncher<Intent> addPictureActivity
            = registerForActivityResult(
            new ActivityResultContracts
            .StartActivityForResult(),
    result -> {
        if (result.getResultCode() == Activity.RESULT_OK) {
            Intent data = result.getData();
            /*
            can do anything with the object retrieved from previous
            activity from here
             */
            if (data != null && data.getData() != null) {
                //retrieving the uri of the chosen image
                Uri selectedImageUri = data.getData();
                ImageDecoder.Source source = ImageDecoder.createSource(this.getContentResolver(), selectedImageUri);
                try {
                    Bitmap selectedImageBitmap = ImageDecoder.decodeBitmap(source);
                    imageView.setImageBitmap(selectedImageBitmap);
                    animal = new Animal("among", selectedImageBitmap);
                }
                catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    });

    private void returnResult() {
        Intent resultIntent = new Intent();
        //animal class has to implement serializable or parcelable
        resultIntent.putExtra("animal", animal);
        setResult(Activity.RESULT_OK, resultIntent);
        finish();
    }


}//class





