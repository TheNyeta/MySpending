package com.example.myspending;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddList extends AppCompatActivity {

    EditText et_name, et_price;
    Button btn_submit;

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_list);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        et_name = findViewById(R.id.et_name);
        et_price = findViewById(R.id.et_price);
        btn_submit = findViewById(R.id.btn_submit);

        btn_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(et_name.getText().toString().equals("")){
                    Toast.makeText(AddList.this, "Name must be filled", Toast.LENGTH_SHORT).show();
                }else if(et_price.getText().toString().equals("")){
                    Toast.makeText(AddList.this, "Price must be filled", Toast.LENGTH_SHORT).show();
                }else {
                    ListHelper helper = new ListHelper(getApplicationContext());
                    helper.insertList(et_name.getText().toString(), Integer.parseInt(et_price.getText().toString()));
                    Intent intent = new Intent(AddList.this, MainActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);

                }

            }

        });

    }
}