package ryxandy.com.example.livrariaapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AtualizarActivty extends AppCompatActivity {

    EditText title_input, author_input, pages_input;
    Button update_button, btnDeletar;
    String id, title, author, pages;
    ActionBar actionBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_atualizar_activty);



        actionBar = getSupportActionBar();
        actionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#5851DB")));

    title_input = findViewById(R.id.edtNome2);
    author_input = findViewById(R.id.edtAutor2);
    pages_input = findViewById(R.id.edtPagina2);
    btnDeletar = findViewById(R.id.btnDeletar);
    update_button = findViewById(R.id.btnAdicionar2);


    pegarSetarDadosIntent();


    //mudando o título da action bar dinamicamente, setar o parent activity no manifest
    ActionBar ab = getSupportActionBar();
    if (ab!=null){
        ab.setTitle(title);
    }




    update_button.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            //Primeiro chamar o banco
            DataHelperClasse meuBanco = new DataHelperClasse(AtualizarActivty.this);
            title = title_input.getText().toString();
            author = author_input.getText().toString();
            pages = pages_input.getText().toString();
            //Depoois atualizar com o método da classe helper
            meuBanco.atualizarDados(id,title,author,pages);

        }
    });

    btnDeletar.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            confirmarDialogo();

        }
    });





    }

        void  pegarSetarDadosIntent(){

        if (getIntent().hasExtra("id") && getIntent().hasExtra("title") && getIntent().hasExtra("author") && getIntent().hasExtra("pages")){

            //Pegar dados de um Intent
            id = getIntent().getStringExtra("id");
            title = getIntent().getStringExtra("title");
            author = getIntent().getStringExtra("author");
            pages = getIntent().getStringExtra("pages");

            //Setando dados

            title_input.setText(title);
            author_input.setText(author);
            pages_input.setText(pages);

        } else {
            Toast.makeText(this, "Infelizmente não encontramos os dados ", Toast.LENGTH_SHORT).show();
        }
    }


    void confirmarDialogo(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Deletar " + title + " ?");
        builder.setMessage("Tem certeza que quer deletar " + title + " ?");
        builder.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                DataHelperClasse meuBanco = new DataHelperClasse(AtualizarActivty.this);
                meuBanco.deletarUmaLinha(id);
                finish();
            }
        });
        builder.setNegativeButton("Não", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        builder.create().show();
    }

}