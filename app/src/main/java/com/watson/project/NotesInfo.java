package com.watson.project;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.backendless.Backendless;
import com.backendless.async.callback.AsyncCallback;
import com.backendless.exceptions.BackendlessFault;

public class NotesInfo extends AppCompatActivity {

    TextView tvTitle;
    ImageView ivEdit2, ivDelete2;
    EditText etNotes,etTitle;
    Button btnSubmitN;

    boolean edit = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notes_info);
        
        tvTitle = findViewById(R.id.tvTitle);
        ivEdit2 = findViewById(R.id.ivEdit2);
        ivDelete2 = findViewById(R.id.ivDelete2);
        etNotes = findViewById(R.id.etNotes);
        etTitle = findViewById(R.id.etTitle);
        btnSubmitN = findViewById(R.id.btnSubmitN);


        
        etTitle.setVisibility(View.GONE);
        etNotes.setVisibility(View.VISIBLE);
        btnSubmitN.setVisibility(View.GONE);

        final int index = getIntent().getIntExtra("index", 0);
       
        etTitle.setText(ApplicationClass.notes.get(index).getTitle());
        etNotes.setText(ApplicationClass.notes.get(index).getPnotes());
        tvTitle.setText(ApplicationClass.notes.get(index).getTitle());
       
       
        ivEdit2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                edit = !edit;

                if (edit)
                {
                    etTitle.setVisibility(View.VISIBLE);
                    etNotes.setVisibility(View.VISIBLE);
                    btnSubmitN.setVisibility(View.VISIBLE);
                }
                else
                {
                    etTitle.setVisibility(View.GONE);
                    etNotes.setVisibility(View.VISIBLE);
                    btnSubmitN.setVisibility(View.GONE);
                }

            }
        });

        ivDelete2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final AlertDialog.Builder dialog = new AlertDialog.Builder(NotesInfo.this);
                dialog.setMessage("Are you sure you want to delete the Notes?");

                dialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {


                        Backendless.Data.of(Notes.class).remove(ApplicationClass.notes.get(index),
                                new AsyncCallback<Long>() {
                                    @Override
                                    public void handleResponse(Long response) {

                                        ApplicationClass.notes.remove(index);
                                        Toast.makeText(NotesInfo.this,
                                                "Notes successfully removed!", Toast.LENGTH_SHORT).show();
                                        setResult(RESULT_OK);
                                        NotesInfo.this.finish();

                                    }

                                    @Override
                                    public void handleFault(BackendlessFault fault) {

                                        Toast.makeText(NotesInfo.this, "Error" +fault.getMessage(), Toast.LENGTH_SHORT).show();
                                    }
                                });

                    }
                });

                dialog.setNegativeButton("NO", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });

                dialog.show();

            }
        });

        btnSubmitN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (etTitle.getText().toString().isEmpty() ||

                        etNotes.getText().toString().isEmpty())
                {
                    Toast.makeText(NotesInfo.this, "Please enter all details!",
                            Toast.LENGTH_SHORT).show();
                }
                else
                {

                    ApplicationClass.notes.get(index).setTitle(etTitle.getText().toString().trim());
                    ApplicationClass.notes.get(index).setPnotes(etNotes.getText().toString().trim());

                    Backendless.Data.save(ApplicationClass.notes.get(index), new AsyncCallback<Notes>() {

                        @Override
                        public void handleResponse(Notes response) {

                            tvTitle.setText(ApplicationClass.notes.get(index).getTitle());
                            Toast.makeText(NotesInfo.this, "Notes updated Succesfully ", Toast.LENGTH_SHORT).show();
                        }

                        @Override
                        public void handleFault(BackendlessFault fault) {

                            Toast.makeText(NotesInfo.this, "Error"+fault.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });



                }

            }
        });





    }

}