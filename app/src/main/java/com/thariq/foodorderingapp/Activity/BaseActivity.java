package com.thariq.foodorderingapp.Activity;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;
import com.thariq.foodorderingapp.R;

public class BaseActivity extends AppCompatActivity {
FirebaseAuth mAuth;
FirebaseDatabase database;
public String TAG="food";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        database=FirebaseDatabase.getInstance("https://food-ordering-app-28309-default-rtdb.asia-southeast1.firebasedatabase.app");
        mAuth=FirebaseAuth.getInstance();

        getWindow().setStatusBarColor(ContextCompat.getColor(this, R.color.white));
    }
}