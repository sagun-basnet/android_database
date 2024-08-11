package com.example.recyclerwithdatabase;

import android.app.Activity;
import android.app.AlertDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

public class MyActivity extends Activity {
    Button btnNew;
    RecyclerView myRecycler;
    DataLayer dbLayer= new DataLayer(this);
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.acticity_layout);
        btnNew = findViewById(R.id.btnNew);
        myRecycler = findViewById(R.id.myRecycler);
        btnNew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog();
            }
        });
    }
    public void showDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Student Record");
        builder.setCancelable(false);
        LayoutInflater inflater = getLayoutInflater();
        View v = inflater.inflate(R.layout.dialog_layout, null, false);
        builder.setView(v);
        AlertDialog dlg = builder.create();
        dlg.show();


        Button btnCancel, btnSubmit;
        EditText editName, editAddress;
        btnCancel = v.findViewById(R.id.btnCancel);
        btnSubmit = v.findViewById(R.id.btnSubmit);
        editName = v.findViewById(R.id.editName);
        editAddress = v.findViewById(R.id.editAddress);
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dlg.cancel();
            }
        });

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Student s = new Student();
                s.Name = editName.getText().toString();
                s.Address= editAddress.getText().toString();
                s.Id = 0;

                if (dbLayer.InsertData(s)){
                    Toast.makeText(MyActivity.this, "Student registred.....",Toast.LENGTH_LONG).show();
                }else{
                    Toast.makeText( MyActivity.this,"Student registration faild.",Toast.LENGTH_LONG).show();
                }
                editName.setText("");
                editAddress.setText("");
            }
        });

    }
}
