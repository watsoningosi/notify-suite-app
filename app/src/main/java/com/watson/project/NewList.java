package com.watson.project;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.backendless.Backendless;
import com.backendless.async.callback.AsyncCallback;
import com.backendless.exceptions.BackendlessFault;

public class NewList extends AppCompatActivity {
    EditText etTitle, etNotes;
    Button btnNotes;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_list);

        etTitle = findViewById(R.id.etTitle);
        etNotes = findViewById(R.id.etNotes);
        btnNotes = findViewById(R.id.btnNotes);


        btnNotes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (etTitle.getText().toString().isEmpty() ||
                        etNotes.getText().toString().isEmpty())
                {
                    Toast.makeText(NewList.this, "Please enter all fields!",
                            Toast.LENGTH_SHORT).show();
                }
                else
                {
                    String title = etTitle.getText().toString().trim();
                    String pnotes = etNotes.getText().toString().trim();

                    Notes notes = new Notes();
                    notes.setTitle(title);
                    notes.setPnotes(pnotes);
                    notes.setUserEmail(ApplicationClass.user.getEmail());


                    Backendless.Data.save(notes, new AsyncCallback<Notes>() {
                        @Override
                        public void handleResponse(Notes response) {
                            Toast.makeText(NewList.this, "You notes have succesfully been saved", Toast.LENGTH_SHORT).show();

                        }

                        @Override
                        public void handleFault(BackendlessFault fault) {
                            Toast.makeText(NewList.this, "Error"+fault.getMessage(), Toast.LENGTH_SHORT).show();


                        }
                    });

                }
            }
        });
    }


}