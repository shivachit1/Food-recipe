package myapp.shiva.foodrecipe.fragments;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import myapp.shiva.foodrecipe.adapter.RecipeAnswersAdapter;
import myapp.shiva.foodrecipe.models.RecipeAnswer;
import myapp.shiva.foodrecipe.models.RecipeQuestion;
import myapp.shiva.foodrecipe.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class RecipeAnswersFragment extends DialogFragment {

private RecipeQuestion recipeQuestion;

    private DatabaseReference mFirebaseDatabaseReference= FirebaseDatabase.getInstance().getReference();
    private String UserID = FirebaseAuth.getInstance().getCurrentUser().getUid();
    private ArrayList<RecipeAnswer> recipeAnswerArrayList =new ArrayList<>();
    private RecipeAnswersAdapter recipeAnswersAdapter;
    private RecyclerView answersRecycleView;
    private TextView noAnswer;

    public RecipeAnswersFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View giveAnswerView= inflater.inflate(R.layout.fragment_recipe_answers, container, false);

        //getting Serializable object (RecipeQuestion) using string "recipe Question" as key Value
       recipeQuestion=(RecipeQuestion) getArguments().getSerializable("recipe Question");
       final TextView itemName=giveAnswerView.findViewById(R.id.recipeName);
       ImageView recipeImageView=giveAnswerView.findViewById(R.id.recipeimage);
       Picasso.with(getContext()).load(recipeQuestion.getFoodImageuri()).fit().centerCrop().into(recipeImageView);
       itemName.setText(recipeQuestion.getRecipeName());

        answersRecycleView=giveAnswerView.findViewById(R.id.answersRecycleView);

        final LinearLayoutManager recipequestionlistLayoutmanager
                = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        answersRecycleView.setLayoutManager(recipequestionlistLayoutmanager);

        recipeAnswersAdapter=new RecipeAnswersAdapter(getContext(), recipeAnswerArrayList, new RecipeAnswersAdapter.OnItemClickListener() {
            @Override
            public void voteClick(RecipeAnswer item) {

                // saving data for vote counter.
                mFirebaseDatabaseReference.child("Votes").child(item.getRecipeQuestionID()).child(item.getAnswerBy()).child(UserID).setValue(UserID);
            }
        });
        getRecipeAnswers();
        answersRecycleView.setAdapter(recipeAnswersAdapter);

        noAnswer=giveAnswerView.findViewById(R.id.noAnswers);



       final EditText ingredients=giveAnswerView.findViewById(R.id.ingredientsEditText);
       final EditText cookingTechniques=giveAnswerView.findViewById(R.id.cookingTechniquesEditText);
       Button answer=giveAnswerView.findViewById(R.id.answer);

       answer.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               postMyAnswer(ingredients,cookingTechniques);
           }
       });

        return giveAnswerView;
    }
    // Method for getting recipe Question's Answers
    // The methods return Array List of Recipe Answer Objects which is inflated over the Recycle View.
    private ArrayList<RecipeAnswer> getRecipeAnswers() {
        mFirebaseDatabaseReference.child("Answers").child("Recipes").child(recipeQuestion.getQuestionID()).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists()){
                    recipeAnswerArrayList.clear();
                    for(DataSnapshot dataSnapshot1:dataSnapshot.getChildren()){
                        String answeredBy=dataSnapshot1.child("answerBy").getValue(String.class);
                        String cookingTechniques=dataSnapshot1.child("cookingTechniques").getValue(String.class);
                        String recipeIngredients=dataSnapshot1.child("recipeIngredients").getValue(String.class);
                        String recipeName=dataSnapshot1.child("recipeName").getValue(String.class);
                        String recipeQuestionID=dataSnapshot1.child("recipeQuestionID").getValue(String.class);
                        RecipeAnswer foodRecipeAnswer =new RecipeAnswer(recipeName,recipeQuestionID,recipeIngredients,cookingTechniques,answeredBy);
                        recipeAnswerArrayList.add(foodRecipeAnswer);
                        recipeAnswersAdapter.notifyDataSetChanged();


                    }
                }
                if(recipeAnswerArrayList.size()==0){
                    // Showing Textview "No answers posted yet" if the array list is empty
                    noAnswer.setVisibility(View.VISIBLE);
                    // Setting Recycleview Visibilty to invisible as the array list empty and there is nothing to show
                    answersRecycleView.setVisibility(View.INVISIBLE);
                }else{
                    noAnswer.setVisibility(View.INVISIBLE);
                    answersRecycleView.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        return recipeAnswerArrayList;
    }

    public void postMyAnswer(EditText ingredients,EditText cookingTechniques) {
        String ingre=ingredients.getText().toString();
        String techn=cookingTechniques.getText().toString();

        // Checking if the user has left the field empty
        if(TextUtils.isEmpty(ingre)||TextUtils.isEmpty(techn)){
            Toast.makeText(getContext(), "Empty Fields!",
                    Toast.LENGTH_SHORT).show();
        }
        // If the user put some values RecipeAnswer object is created and save to the database
        else {

            RecipeAnswer foodRecipeAnswer =new RecipeAnswer(recipeQuestion.getRecipeName(),recipeQuestion.getQuestionID(),ingre,techn,UserID);

            // Simpleset way of saving data to database
            mFirebaseDatabaseReference.child("Answers").child("Recipes").child(foodRecipeAnswer.getRecipeQuestionID()).child(UserID).setValue(foodRecipeAnswer);

            // Dismissing the dialog Fragment which is shown on screen right now. (Recipe AnswersFragment)
           dismiss();

        }
    }




}
