package com.example.simplechat;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.parse.LogInCallback;
import com.parse.Parse;
import com.parse.ParseAnonymousUtils;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseUser;
import com.parse.SaveCallback;

import java.util.*;

import okhttp3.OkHttpClient;

public class ChatActivity extends AppCompatActivity {

    public static final String TAG = "ChatApplication";
    public ImageButton sendButton;
    public EditText tvType;
    public RecyclerView rvMessages;
    public ArrayList<Message> mMessages;
    public boolean mFirstLoad;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        if (ParseUser.getCurrentUser() != null) { // Start with existing user
            startWithCurrentUser(); //TODO: We will build out this method in the next step
        } else { // If not logged in, login as a new anonymous user
            login();
        }
    }

    // Create an anonymous user using ParseAnonymousUtils and set sUserId
    void login() {
        ParseAnonymousUtils.logIn(new LogInCallback() {
            @Override
            public void done(ParseUser user, ParseException e) {
                if (e != null) {
                    Log.e(TAG, "Anonymous login failed: ", e);
                } else {
                    startWithCurrentUser();
                }
            }
        });
    }

    // Get the userId from the cached currentUser object
    void startWithCurrentUser() {
        setupMessagePosting();
    }

    public void setupMessagePosting() {
        sendButton = findViewById(R.id.sendButton);
        tvType = findViewById(R.id.tvType);
        rvMessages = findViewById(R.id.rvMessages);
        mMessages = new ArrayList<>();
        mFirstLoad = true;
        sendButton.setOnClickListener( new View.OnClickListener() {
            public void onClick(View v) {
                String messageText = tvType.getText().toString();

//                ParseObject message = new ParseObject("pObjectChat");
//                message.put("USER_ID_KEY", ParseUser.getCurrentUser().getObjectId());
//                message.put("BODY_KEY", messageText);

                // Using new `Message.java` Parse-backed model now
                Message message = new Message();
                message.setUserId(ParseUser.getCurrentUser().getObjectId());
                message.setBody(messageText);




                message.saveInBackground(new SaveCallback() {
                    @Override
                    public void done(ParseException e) {
                        if (e == null) {
                            Toast.makeText(ChatActivity.this, "Successfully created message on Parse",
                                    Toast.LENGTH_SHORT).show();
                        } else {
                            Log.e(TAG, "Failed to save message", e);
                        }
                    }
                });
                tvType.setText(null);
            }
        });

    }
}