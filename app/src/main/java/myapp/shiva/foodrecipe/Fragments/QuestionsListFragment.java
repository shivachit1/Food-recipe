package myapp.shiva.foodrecipe.Fragments;


import android.app.Dialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import myapp.shiva.foodrecipe.Adapter.RecipeQuestionsAdapter;
import myapp.shiva.foodrecipe.Objects.recipeQuestion;
import myapp.shiva.foodrecipe.R;

import static android.app.Activity.RESULT_OK;

/**
 * A simple {@link Fragment} subclass.
 */
public class QuestionsListFragment extends Fragment {

    private int PICK_IMAGE_REQUEST = 111;
    private EditText recipeName;
    private StorageReference mStorageRef = FirebaseStorage.getInstance().getReference();
    private DatabaseReference mFirebaseDatabaseReference=FirebaseDatabase.getInstance().getReference();
    private DatabaseReference askquestionreference;
    private RecipeQuestionsAdapter recipeQuestionsAdapter;


    public QuestionsListFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(@NonNull final LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View questionFragment= inflater.inflate(R.layout.fragment_questions_list, container, false);


        FloatingActionButton askrecipe=questionFragment.findViewById(R.id.ask);
        RecyclerView recipequestionListView=questionFragment.findViewById(R.id.recipeQuestions);
        final LinearLayoutManager recipequestionlistLayoutmanager
                = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        recipequestionListView.setLayoutManager(recipequestionlistLayoutmanager);

        recipeQuestionsAdapter=new RecipeQuestionsAdapter(getContext(), getRecipeQuestions(), new RecipeQuestionsAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(recipeQuestion item) {

            }
        });

        recipequestionListView.setAdapter(recipeQuestionsAdapter);
        askrecipe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                askquestionreference = mFirebaseDatabaseReference.child("Questions").child("Recipes").push();

                postId = askquestionreference.getKey();
                final Dialog dialog=new Dialog(getContext());
                View askquestionView=inflater.inflate(R.layout.fragment_ask_question,container,false);
                recipeName=askquestionView.findViewById(R.id.recipeName);
                recipeImageView=askquestionView.findViewById(R.id.foodImage);

                Button selectImage=askquestionView.findViewById(R.id.uploadImage);

                Button ask=askquestionView.findViewById(R.id.ask);

                selectImage.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent();
                        intent.setType("image/*");
                        intent.setAction(Intent.ACTION_PICK);
                        startActivityForResult(Intent.createChooser(intent, "Select Image"), PICK_IMAGE_REQUEST);

                    }
                });

                ask.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        postQuestion();
                        dialog.dismiss();
                    }
                });

                dialog.setContentView(askquestionView);
                dialog.show();
            }
        });
        return questionFragment;
    }

    private void postQuestion() {

                if(formisValid()){
                    recipeQuestion recipeQuestion=new recipeQuestion(postId,recipeName.getText().toString(),uri1);
                    askquestionreference.setValue(recipeQuestion);
                }
    }

    private boolean formisValid() {
        if(postId!=null && recipeName.getText().toString()!=null && uri1!=null){

            return true;
        }else{
            return false;
        }


    }


    ImageView recipeImageView;
    String postId;
    StorageReference filePath;
    String uri1;

    @Override
    public void onActivityResult(@NonNull int requestCode, @NonNull int resultCode, @NonNull final Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
         if(data.getData()!=null){
             Uri uri=data.getData();
        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK) {

            filePath = mStorageRef.child("Recipes").child("Items").child(postId).child("image.jpg");


            filePath.putFile(uri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {


                    filePath.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                        @Override
                        public void onSuccess(Uri uri) {
                            Uri downloadUrl = uri;

                            Picasso.with(getContext()).load(downloadUrl).fit().centerCrop().into(recipeImageView);

                            uri1 = downloadUrl.toString();

                        }
                    });


                }
            });

        }
         }

    }

    private ArrayList<recipeQuestion> recipeQuestionArrayList=new ArrayList<>();
    private ArrayList<recipeQuestion> getRecipeQuestions(){
        mFirebaseDatabaseReference.child("Questions").child("Recipes").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists()){
                    recipeQuestionArrayList.clear();
                    for(DataSnapshot dataSnapshot1:dataSnapshot.getChildren()){
                        String questionID=dataSnapshot1.child("questionID").getValue(String.class);
                        String recipeName=dataSnapshot1.child("recipeName").getValue(String.class);
                        String foodImageuri=dataSnapshot1.child("foodImageuri").getValue(String.class);
                        recipeQuestion recipeQuestion=new recipeQuestion(questionID,recipeName,foodImageuri);
                        recipeQuestionArrayList.add(recipeQuestion);
                        recipeQuestionsAdapter.notifyDataSetChanged();
                    }
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        return recipeQuestionArrayList;
    }

}
