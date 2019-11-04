package myapp.shiva.foodrecipe;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import myapp.shiva.foodrecipe.models.User;

public class LoginandSignUp extends Activity {
    private FirebaseAuth mAuth;
    private DatabaseReference mFirebaseDatabaseReference= FirebaseDatabase.getInstance().getReference();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // get the instance of Firebase Authorization
        mAuth = FirebaseAuth.getInstance();

        // get layout inflater for inflating xml views
        final LayoutInflater inflater = (LayoutInflater) LoginandSignUp.this.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);

        final EditText userEmail=findViewById(R.id.userEmail);
        final EditText userPassword=findViewById(R.id.userPassword);
        TextView signuphere=findViewById(R.id.signuphere);

        Button signin=findViewById(R.id.signin);

        signuphere.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            View signupView=inflater.inflate(R.layout.signup,null);
            Dialog dialog=new Dialog(LoginandSignUp.this);

            final EditText userName=signupView.findViewById(R.id.userName);
            final EditText userEmailAddress=signupView.findViewById(R.id.userEmail);
            final EditText userSignupPassword=signupView.findViewById(R.id.password);
            Button signup=signupView.findViewById(R.id.signup);

            signup.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    final String name=userName.getText().toString();
                    final String email=userEmailAddress.getText().toString();
                    String password=userSignupPassword.getText().toString();
                    if(TextUtils.isEmpty(name)|| TextUtils.isEmpty(email)|| TextUtils.isEmpty(password)){
                        Toast.makeText(LoginandSignUp.this, "Empty Fields... Please Check all fields are entered.",
                                Toast.LENGTH_SHORT).show();
                    }
                    else{
                        mAuth.createUserWithEmailAndPassword(email, password)
                                .addOnCompleteListener(LoginandSignUp.this, new OnCompleteListener<AuthResult>() {
                                    @Override
                                    public void onComplete(@NonNull Task<AuthResult> task) {
                                        if (task.isSuccessful()) {
                                            // Sign in success, update UI with the signed-in user's information
                                            User newUser=new User(name,email);

                                            // this will give the user which is recently signed UP
                                            FirebaseUser user = mAuth.getCurrentUser();

                                            // saving user data in the firebase database
                                            mFirebaseDatabaseReference.child("Users").child(user.getUid()).setValue(newUser);

                                            // update UI after saving data user Data to database
                                            updateUI(user);


                                        } else {
                                            // If sign in fails, display a message to the user.
                                            Toast.makeText(LoginandSignUp.this, "Authentication failed.",
                                                    Toast.LENGTH_SHORT).show();
                                        }

                                        // ...
                                    }
                                });

                    }
                }
            });

            dialog.setContentView(signupView);
            dialog.show();
            }
        });


        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String email=userEmail.getText().toString();
                String password=userPassword.getText().toString();

                if(TextUtils.isEmpty(email) || TextUtils.isEmpty(password)){
                    Toast.makeText(LoginandSignUp.this, "Empty Fields",
                            Toast.LENGTH_SHORT).show();
                }
                else{
                    mAuth.signInWithEmailAndPassword(email, password)
                            .addOnCompleteListener(LoginandSignUp.this, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()) {
                                        // Sign in success, update UI with the signed-in user's information
                                        FirebaseUser user = mAuth.getCurrentUser();
                                        updateUI(user);
                                    } else {
                                        // If sign in fails, display a message to the user.
                                        Toast.makeText(LoginandSignUp.this, "Authentication failed.",
                                                Toast.LENGTH_SHORT).show();

                                    }

                                    // ...
                                }
                            });
                }




            }
        });
    }




    private void updateUI(FirebaseUser currentUser) {
        // Using Intent to change one Activity to another Activity
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(intent);
    }



    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if (currentUser != null) {
            updateUI(currentUser);
        }

    }


}
