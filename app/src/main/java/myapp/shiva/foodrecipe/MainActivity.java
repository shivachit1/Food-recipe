package myapp.shiva.foodrecipe;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;

import myapp.shiva.foodrecipe.fragments.QuestionsListFragment;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FirebaseApp.initializeApp(this);
        setContentView(R.layout.activity_main);

        Button signOut=findViewById(R.id.signOut);

        signOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "User Signed Out...",
                        Toast.LENGTH_SHORT).show();
                // Signing Out the user from Firebase and laso from Application
                FirebaseAuth.getInstance().signOut();
                Intent intent = new Intent(MainActivity.this, LoginandSignUp.class);
                startActivity(intent);
                finish();
            }
        });

        // Directly Inflating fragment over Constraint Layout.
        android.support.v4.app.FragmentTransaction fragmentTransaction=getSupportFragmentManager().beginTransaction();
        android.support.v4.app.Fragment fragment=new QuestionsListFragment();
        fragmentTransaction.replace(R.id.viewlayout,fragment,null).commit();


    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i(TAG, "On Destroy .....");
    }
    /* (non-Javadoc)
     * @see android.app.Activity#onPause()
     */
    @Override
    protected void onPause() {
        super.onPause();
        Log.i(TAG, "On Pause .....");
    }

    /* (non-Javadoc)
     * @see android.app.Activity#onRestart()
     */
    @Override
    protected void onRestart() {
        super.onRestart();
        Log.i(TAG, "On Restart .....");
    }

    /* (non-Javadoc)
     * @see android.app.Activity#onResume()
     */
    @Override
    protected void onResume() {
        super.onResume();
        Log.i(TAG, "On Resume .....");
    }

    /* (non-Javadoc)
     * @see android.app.Activity#onStart()
     */
    @Override
    protected void onStart() {
        super.onStart();
        Log.i(TAG, "On Start .....");

    }
    /* (non-Javadoc)
     * @see android.app.Activity#onStop()
     */
    @Override
    protected void onStop() {
        super.onStop();
        Log.i(TAG, "On Stop .....");
    }
}
