package myapp.shiva.foodrecipe.adapter;

import android.content.Context;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import myapp.shiva.foodrecipe.models.RecipeQuestion;
import myapp.shiva.foodrecipe.R;

public class RecipeQuestionsAdapter extends RecyclerView.Adapter<RecipeQuestionsAdapter.MyViewHolder>{

public interface OnItemClickListener {
    void onAnswerClick(RecipeQuestion item);
}
    private Context context;
    private final OnItemClickListener listener;
    private ArrayList<RecipeQuestion> data;

    public RecipeQuestionsAdapter(Context context,OnItemClickListener listener) {
        this.context = context;
        this.listener=listener;
    }

    public RecipeQuestionsAdapter(Context context, ArrayList<RecipeQuestion> data, OnItemClickListener listener) {
        this.data = data;
        this.context = context;
        this.listener = listener;

    }


public class MyViewHolder extends RecyclerView.ViewHolder{
    View itemview;
    private TextView recipeQuestion;
    private ImageView recipeimage;
    private TextView recipeName;
    private ConstraintLayout layout;



    private MyViewHolder(View v) {
        super(v);
        itemview = v;
        recipeimage=v.findViewById(R.id.recipeimage);
        recipeName=v.findViewById(R.id.recipeName);
        layout =v.findViewById(R.id.layout);
    }

    private void bind(final RecipeQuestion item, final OnItemClickListener listener) {


        layout.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {

                listener.onAnswerClick(item);

            }
        });

    }
}


    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recipequestion_eachview, parent, false);
        Context context = parent.getContext();
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        if (data != null) {
            holder.bind(data.get(position), listener);
            RecipeQuestion recipeQuestion=data.get(position);
            Picasso.with(context).load(recipeQuestion.getFoodImageuri()).fit().centerCrop().into(holder.recipeimage);
            holder.recipeName.setText(recipeQuestion.getRecipeName());
}

    }

    @Override
    public int getItemCount() {
        return data.size();
    }
}