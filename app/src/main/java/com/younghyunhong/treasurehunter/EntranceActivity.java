package com.younghyunhong.treasurehunter;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.parse.LogInCallback;
import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

public class EntranceActivity extends AppCompatActivity implements View.OnClickListener {
    EditText usernameField;
    EditText passwordField;
    TextView changeSignUpModeTextView;
    Button signUpButton;

    Boolean signUpModeActive;

    @Override
    public void onClick(View v) {

        if (v.getId() == R.id.changeSignUpMode) {
            if (signUpModeActive == true) {

                signUpModeActive = false;
                changeSignUpModeTextView.setText("or Sign Up");
                signUpButton.setText("Log In");

            } else {

                signUpModeActive = true;
                changeSignUpModeTextView.setText("or Log In");
                signUpButton.setText("Sign Up");

            }
        }
    }

    public void signUpOrLogIn(View view) {

        //Log.i("AppInfo", String.valueOf(usernameField.getText()));
        //Log.i("AppInfo", String.valueOf(passwordField.getText()));

        if (signUpModeActive == true) {

            ParseUser user = new ParseUser();
            user.setUsername(String.valueOf(usernameField.getText()));
            user.setPassword(String.valueOf(passwordField.getText()));

            user.signUpInBackground(new SignUpCallback() {
                @Override
                public void done(ParseException e) {

                    if (e == null) {
                        Log.i("AppInfo", "Signup Successful");
                        String message = "Sign up Successful. Proceed Log In.";


                        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_LONG).show();

                        Intent i = new Intent (getApplicationContext(), MainActivity.class);
                        startActivity(i);


                    } else {
                        Toast.makeText(getApplicationContext(), e.getMessage().substring(e.getMessage().indexOf(" ")), Toast.LENGTH_LONG).show();
                    }
                }
            });

        } else {

            ParseUser.logInInBackground(String.valueOf(usernameField.getText()), String.valueOf(passwordField.getText()), new LogInCallback() {
                @Override
                public void done(ParseUser user, ParseException e) {

                    if (user != null) {
                        Log.i("AppInfo", "Login Successful");
                        String message = "Welcome.";

                        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_LONG).show();

                        Intent i = new Intent(getApplicationContext(), MainActivity.class);
                        startActivity(i);


                    } else {
                        Toast.makeText(getApplicationContext(), e.getMessage().substring(e.getMessage().indexOf(" ")), Toast.LENGTH_LONG).show();
                    }
                }
            });
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_entrance);

        //if(ParseUser.getCurrentUser() != null){
        //   Intent i = new Intent (getApplicationContext(), MainActivity.class);
        //   startActivity(i);
        //}

        signUpModeActive = true;

        usernameField = (EditText) findViewById(R.id.loginID);
        passwordField = (EditText) findViewById(R.id.loginPW);
        changeSignUpModeTextView = (TextView) findViewById(R.id.changeSignUpMode);
        signUpButton = (Button) findViewById(R.id.button_SignUp);

        changeSignUpModeTextView.setOnClickListener(this);

        Parse.initialize(this, "OZ6zs8vYcGwhe9qIx16wBYYe8QhOq6ilygirurOg", "KMMU0V6s65GVorr1Gie6O1Ltt6BMyY9bzK8vQW7B");

    }

}


