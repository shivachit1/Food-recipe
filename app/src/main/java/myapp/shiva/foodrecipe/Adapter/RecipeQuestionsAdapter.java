package myapp.shiva.foodrecipe.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import myapp.shiva.foodrecipe.Objects.recipeQuestion;
import myapp.shiva.foodrecipe.R;

public class RecipeQuestionsAdapter extends RecyclerView.Adapter<RecipeQuestionsAdapter.MyViewHolder>{

public interface OnItemClickListener {
    void onItemClick(recipeQuestion item);
}
    private Context context;
    private final OnItemClickListener listener;
    private ArrayList<recipeQuestion> data;

    public RecipeQuestionsAdapter(Context context,OnItemClickListener listener) {
        this.context = context;
        this.listener=listener;
    }

    public RecipeQuestionsAdapter(Context context, ArrayList<recipeQuestion> data, OnItemClickListener listener) {
        this.data = data;
        this.context = context;
        this.listener = listener;

    }


public class MyViewHolder extends RecyclerView.ViewHolder{
    View itemview;
    private TextView recipeQuestion;
    private ImageView recipeimage;
    private TextView recipeName;
    private Button answer;



    private MyViewHolder(View v) {
        super(v);
        itemview = v;
        recipeQuestion=v.findViewById(R.id.recipeQuestion);
        recipeimage=v.findViewById(R.id.recipeimage);
        recipeName=v.findViewById(R.id.recipeName);
        answer=v.findViewById(R.id.answer);


    }

    private void bind(final recipeQuestion item, final OnItemClickListener listener) {


        answer.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {

                listener.onItemClick(item);

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
            recipeQuestion recipeQuestion=data.get(position);
            holder.recipeQuestion.setText("How to make ?");
            Picasso.with(context).load(recipeQuestion.getFoodImageuri()).fit().centerCrop().into(holder.recipeimage);
            holder.recipeName.setText(recipeQuestion.getRecipeName());

}
    }

    @Override
    public int getItemCount() {
        return data.size();
    }
}