package com.example.loginapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

public class DetailActivity extends AppCompatActivity {

    TextView detailDesc,detailName,detailPrice;
    ImageView detailImage;
    Button deleteButton,editButton;
    String key="";
    String imageUrl= "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_activity);

        detailDesc = findViewById(R.id.detailDesc);
        detailName = findViewById(R.id.detailName);
        detailImage = findViewById(R.id.detailImage);
        deleteButton = findViewById(R.id.deleteButton);
        editButton = findViewById(R.id.editButton);
        detailPrice = findViewById(R.id.detailPrice);

        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            String description = bundle.getString("Description", ""); // Default empty if null
            String name = bundle.getString("Name", ""); // Default empty if null
            String price = bundle.getString("Price", ""); // Default empty if null
            key = bundle.getString("Key", ""); // Default empty if null
            imageUrl = bundle.getString("Image", ""); // Default empty if null

            // ** Add these debug logs to track the values passed **
            Log.d("DetailActivity", "Key: " + key);
            Log.d("DetailActivity", "Name: " + name);
            Log.d("DetailActivity", "Description: " + description);
            Log.d("DetailActivity", "Price: " + price);
            Log.d("DetailActivity", "Image URL: " + imageUrl);

            // Set text only if the values are not null
            if (!description.isEmpty()) {
                detailDesc.setText(description);
            } else {
                detailDesc.setText("No Description available"); // Fallback text
            }

            if (!name.isEmpty()) {
                detailName.setText(name);
            } else {
                detailName.setText("No Name available"); // Fallback text
            }

            if (!price.isEmpty()) {
                detailPrice.setText(price);
            } else {
                detailPrice.setText("No Price available"); // Fallback text
            }

            // Load image using Glide, and handle empty imageUrl
            if (!imageUrl.isEmpty()) {
                Glide.with(this).load(imageUrl).into(detailImage);
            } else {
                detailImage.setImageResource(R.drawable.baseline_add_photo_alternate_24); // Set a vector asset if no image
            }
        } else {
            Toast.makeText(this, "No data passed", Toast.LENGTH_SHORT).show();
            finish(); // Exit if no data is passed
        }
        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (key != null && !key.isEmpty()) { // Ensure the key is not null or empty
                    final DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Android Tutorials");
                    FirebaseStorage storage = FirebaseStorage.getInstance();

                    StorageReference storageReference = storage.getReferenceFromUrl(imageUrl);
                    storageReference.delete().addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void unused) {
                            reference.child(key).removeValue(); // Use the key to delete the correct data entry
                            Toast.makeText(DetailActivity.this, "Deleted", Toast.LENGTH_LONG).show();
                            Intent i = new Intent(getApplicationContext(), fragment_home.class);
                            startActivity(i);
                            finish();
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(DetailActivity.this, "Delete failed: " + e.getMessage(), Toast.LENGTH_LONG).show();
                        }
                    });
                } else {
                    Toast.makeText(DetailActivity.this, "Key is missing, cannot delete the item.", Toast.LENGTH_SHORT).show();
                }
            }
        });

        editButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (key != null && !key.isEmpty()) { // Ensure the key is passed correctly
                    Intent intent = new Intent(DetailActivity.this, UpdateActivity.class);
                    intent.putExtra("Name", detailName.getText().toString());
                    intent.putExtra("Description", detailDesc.getText().toString());
                    intent.putExtra("Price", detailPrice.getText().toString());
                    intent.putExtra("Image", imageUrl);
                    intent.putExtra("Key", key); // Pass the auto-generated key
                    Log.d("DetailActivity", "Key passed: " + key); // Log the key value for debugging
                    startActivity(intent);
                } else {
                    Toast.makeText(DetailActivity.this, "Key is missing, cannot edit the item.", Toast.LENGTH_SHORT).show();
                }
            }
        });

//        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
//            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
//            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
//            return insets;
//        });
    }
}