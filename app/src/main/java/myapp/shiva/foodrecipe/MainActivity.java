package myapp.shiva.foodrecipe;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Switch;

import com.google.firebase.FirebaseApp;

import myapp.shiva.foodrecipe.Fragments.AnswersListFragment;
import myapp.shiva.foodrecipe.Fragments.QuestionsListFragment;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FirebaseApp.initializeApp(this);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();

        final Switch switchbutton=findViewById(R.id.switchbutton);
        changeFragment("Questions");
        switchbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    if(switchbutton.isChecked()){
                        changeFragment("Answers");
                    }else{
                        changeFragment("Questions");
                    }



            }
        });
    }

    private void changeFragment(String name){
        if(name.equals("Questions")){
            android.support.v4.app.FragmentTransaction fragmentTransaction=getSupportFragmentManager().beginTransaction();
            android.support.v4.app.Fragment fragment=new QuestionsListFragment();
            fragmentTransaction.replace(R.id.viewlayout,fragment,null).commit();
        }
        else if(name.equals("Answers")){
            android.support.v4.app.FragmentTransaction fragmentTransaction=getSupportFragmentManager().beginTransaction();
            android.support.v4.app.Fragment fragment=new AnswersListFragment();
            fragmentTransaction.replace(R.id.viewlayout,fragment,null).commit();
        }

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
