package com.example.myspending;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class EditList extends AppCompatActivity {

    TextView edit_name, edit_price;
    Button btn_edit, btn_delete;

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
        setContentView(R.layout.activity_edit_list);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        edit_name = findViewById(R.id.edit_name);
        edit_price = findViewById(R.id.edit_price);
        btn_edit = findViewById(R.id.btn_edit);
        btn_delete = findViewById(R.id.btn_delete);

        Intent intent = getIntent();
        edit_name.setText(intent.getStringExtra("name"));
        edit_price.setText(String.valueOf(intent.getIntExtra("price", 0)));

        btn_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(edit_name.getText().toString().equals("")){
                    Toast.makeText(EditList.this, "Name must be filled", Toast.LENGTH_SHORT).show();
                }else if(edit_price.getText().toString().equals("")){
                    Toast.makeText(EditList.this, "Price must be filled", Toast.LENGTH_SHORT).show();
                }else {
                    ListHelper helper = new ListHelper(getApplicationContext());
                    helper.editList(intent.getIntExtra("id", 0), edit_name.getText().toString(), Integer.parseInt(edit_price.getText().toString()));
                    Intent intent = new Intent(EditList.this, MainActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);

                }

            }

        });

        //Saya menambahkan button delete untuk mempermudah user
        btn_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ListHelper helper = new ListHelper(getApplicationContext());
                helper.deleteList(intent.getIntExtra("id", 0));
                Intent intent = new Intent(EditList.this, MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });

    }
}