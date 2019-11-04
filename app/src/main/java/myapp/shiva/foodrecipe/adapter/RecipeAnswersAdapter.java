package myapp.shiva.foodrecipe.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import myapp.shiva.foodrecipe.R;
import myapp.shiva.foodrecipe.models.RecipeAnswer;

public class RecipeAnswersAdapter extends RecyclerView.Adapter<RecipeAnswersAdapter.MyViewHolder>{

    public interface OnItemClickListener {
        void voteClick(RecipeAnswer item);
    }
    private Context context;
    private final OnItemClickListener listener;
    private ArrayList<RecipeAnswer> data;
    private DatabaseReference mFirebaseDatabaseReference= FirebaseDatabase.getInstance().getReference();

    public RecipeAnswersAdapter(Context context, OnItemClickListener listener) {
        this.context = context;
        this.listener=listener;
    }

    public RecipeAnswersAdapter(Context context, ArrayList<RecipeAnswer> data, OnItemClickListener listener) {
        this.data = data;
        this.context = context;
        this.listener = listener;

    }


    public class MyViewHolder extends RecyclerView.ViewHolder{
        View itemview;
        private TextView ingredients;
        private TextView cookingTechniques;
        private TextView postedBy;
        private TextView voteCounter;
        private Button vote;



        private MyViewHolder(View v) {
            super(v);
            itemview = v;
            ingredients=v.findViewById(R.id.ingre);
            cookingTechniques=v.findViewById(R.id.techni);
            postedBy=v.findViewById(R.id.answerBy);
            voteCounter=v.findViewById(R.id.voteCounter);
            vote=v.findViewById(R.id.vote);

        }

        private void bind(final RecipeAnswer item, final RecipeAnswersAdapter.OnItemClickListener listener) {

            vote.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.voteClick(item);
                }
            });


        }
    }


    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recipeanswer_eachview, parent, false);
        Context context = parent.getContext();
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        if (data != null) {
            RecipeAnswer recipeAnswer =(RecipeAnswer)data.get(position);
            holder.bind(data.get(position), listener);
            holder.ingredients.setText(recipeAnswer.getRecipeIngredients());
            holder.cookingTechniques.setText(recipeAnswer.getCookingTechniques());
            holder.postedBy.setText("Answered By : "+ recipeAnswer.getAnswerBy());
            mFirebaseDatabaseReference.child("Votes").child(recipeAnswer.getRecipeQuestionID()).child(recipeAnswer.getAnswerBy()).addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    if(dataSnapshot.exists()){
                        int i=0;
                        for(DataSnapshot dataSnapshot1:dataSnapshot.getChildren()){
                            i++;
                        }
                        holder.voteCounter.setText(String.valueOf(i));
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });

        }

    }

    @Override
    public int getItemCount() {
        return data.size();
    }
}