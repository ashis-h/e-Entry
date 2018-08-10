package com.example.user.e_entry;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Scannerbtn extends AppCompatActivity {
    Button login;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scanner_btn);
        Button login=(Button)findViewById(R.id.button2);
    }

    public void Scan(View view) {
        Intent intent=new Intent(Scannerbtn.this,QrScanner.class);
        Scannerbtn.this.startActivity(intent);
        Scannerbtn.this.finish();

    }
}
