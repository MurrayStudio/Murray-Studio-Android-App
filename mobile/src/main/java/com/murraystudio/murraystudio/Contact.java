package com.murraystudio.murraystudio;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.rengwuxian.materialedittext.MaterialEditText;

public class Contact extends Fragment {

    private static final String KEY_LAYOUT_MANAGER = "layoutManager";
    private static final int DATASET_COUNT = 60;

    android.support.v7.app.ActionBar actionBar;
    private MaterialEditText nameField;
    private MaterialEditText subjectField;
    private MaterialEditText messageField;
    private Button sendButton;
    private boolean canSend;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Initialize dataset, this data would usually come from a local content provider or
        // remote server.
        initDataset();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.contact, container, false);

        actionBar = (android.support.v7.app.ActionBar) ((MainActivity) getActivity())
                .getSupportActionBar();
        actionBar.setTitle("Contact");

        nameField = (MaterialEditText) view.findViewById(R.id.nameField);
        subjectField = (MaterialEditText) view.findViewById(R.id.subjectField);
        messageField = (MaterialEditText) view.findViewById(R.id.messageField);
        sendButton = (Button) view.findViewById(R.id.sendButton);

        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(nameField.getText().toString().trim().length() <= 1){
                    Toast.makeText(getActivity(), "Name not long enough.", Toast.LENGTH_SHORT).show();
                    canSend = false;
                }
                else if(subjectField.getText().toString().trim().length() <= 1){
                    Toast.makeText(getActivity(), "Subject not long enough.", Toast.LENGTH_SHORT).show();
                    canSend = false;
                }
                else if(messageField.getText().toString().trim().length() <= 1){
                    Toast.makeText(getActivity(), "Message not long enough.", Toast.LENGTH_SHORT).show();
                    canSend = false;
                }
                else{
                    canSend = true;
                }

                if(canSend == true) {
                    Intent emailIntent = new Intent(Intent.ACTION_SENDTO, Uri.fromParts(
                            "mailto", "s.murray.studio@gmail.com", null));
                    emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Subject: " + subjectField.getText().toString() + ". From: " + nameField.getText().toString());
                    emailIntent.putExtra(Intent.EXTRA_TEXT, messageField.getText().toString());
                    startActivity(Intent.createChooser(emailIntent, "Send email..."));
                }
            }
        });

        return view;
    }

    /**
     * Generates Strings for AboutMe
     */
    private void initDataset() {
    }
}

