package ryxandy.com.example.livrariaapp;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CustomAdapter  extends RecyclerView.Adapter<CustomAdapter.MyViewHolder>{


    private Context context;
    private ArrayList book_id, book_title, book_author, book_pages;
    int position;
    Activity activity;

    Animation translateAnim;


    CustomAdapter(Activity activity, Context context,
                  ArrayList book_id,
                  ArrayList book_title,
                  ArrayList book_author,
                  ArrayList book_pages){

        this.activity = activity;
        this.context = context;
        this.book_id = book_id;
        this.book_title = book_title;
        this.book_author = book_author;
        this.book_pages = book_pages;

    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
      View view = inflater.inflate(R.layout.my_row,parent,false);
        return  new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CustomAdapter.MyViewHolder holder, final int position) {


        this.position = position;

        holder.txtID.setText(String.valueOf(book_id.get(position)));
        holder.txtTitulo.setText(String.valueOf(book_title.get(position)));
        holder.txtAuthor.setText(String.valueOf(book_author.get(position)));
        holder.txtPages.setText(String.valueOf(book_pages.get(position)));
        holder.mainLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, AtualizarActivty.class);
                intent.putExtra("id", String.valueOf(book_id.get(position)));
                intent.putExtra("title", String.valueOf(book_title.get(position)));
                intent.putExtra("author", String.valueOf(book_author.get(position)));
                intent.putExtra("pages", String.valueOf(book_pages.get(position)));
                activity.startActivityForResult(intent, 1);
            }
        });

    }

    @Override
    public int getItemCount() {
        return book_id.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView txtTitulo, txtAuthor, txtPages, txtID;
        LinearLayout mainLayout;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            txtID = itemView.findViewById(R.id.txtID);
       txtTitulo = itemView.findViewById(R.id.txtTitle);
       txtAuthor = itemView.findViewById(R.id.txtAuthor);
       txtTitulo = itemView.findViewById(R.id.txtTitle);
       txtPages = itemView.findViewById(R.id.txtPages);
       mainLayout = itemView.findViewById(R.id.mainLayout);
        //passando o arquivo de animação da recycleview para dentro do layout
       translateAnim = AnimationUtils.loadAnimation(context,R.anim.transacao_anim);
       mainLayout.setAnimation(translateAnim);




        }
    }
}
