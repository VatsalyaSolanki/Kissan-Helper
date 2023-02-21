package com.project.agriculturalapp.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import androidx.appcompat.widget.Toolbar;
import android.util.Log;
import android.view.View;

import com.applozic.mobicomkit.api.account.register.RegistrationResponse;
import com.project.agriculturalapp.R;

import io.kommunicate.KmConversationBuilder;
import io.kommunicate.Kommunicate;
import io.kommunicate.callbacks.KMLoginHandler;
import io.kommunicate.callbacks.KmCallback;

public class ChatbotActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chatbot);

        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        Kommunicate.init(this, "853379892b42de638a923ab862780a16");

       /* new KmConversationBuilder(this)
                .launchConversation(new KmCallback() {
                    @Override
                    public void onSuccess(Object message) {
                        Log.d("Conversation", "Success : " + message);
                    }

                    @Override
                    public void onFailure(Object error) {
                        Log.d("Conversation", "Failure : " + error);
                    }
                });*/

        Kommunicate.loginAsVisitor(this, new KMLoginHandler() {
            @Override
            public void onSuccess(RegistrationResponse registrationResponse, Context context) {

                Kommunicate.openConversation(context, null, new KmCallback() {
                    @Override
                    public void onSuccess(Object message) {
                        Log.d("Conversation", "Success : " + message);
                    }

                    @Override
                    public void onFailure(Object error) {
                        Log.d("Conversation", "Failure : " + error);
                    }
                });
                // You can perform operations such as opening the conversation, creating a new conversation or update user details on success
            }

            @Override
            public void onFailure(RegistrationResponse registrationResponse, Exception exception) {
                // You can perform actions such as repeating the login call or throw an error message on failure
            }
        });
    }
}