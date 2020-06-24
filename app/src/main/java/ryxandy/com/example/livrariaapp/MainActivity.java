package ryxandy.com.example.livrariaapp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    FloatingActionButton addBotao;
    DataHelperClasse myDB;
    ArrayList<String> book_id, book_title, book_author, book_pages;
    CustomAdapter customAdapter;
    ImageView img_vazio;
    TextView txt_SemFilmes;
    ActionBar actionBar;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(false);
        actionBar.setHomeButtonEnabled(false);
        actionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#000000")));



        //Inicializar e colocar variáveis do bottom navigation

            BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        //Home itebm
            bottomNavigationView.setSelectedItemId(R.id.Home);
        //O que vai acontecer quando selecionar esse item
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()){
                    case  R.id.adicionar:
                        startActivity(new Intent(getApplicationContext(),AdicionarActivity.class));
                        overridePendingTransition(0,0);
                        return true;

                    case  R.id.Home:
                       // startActivity(new Intent(getApplicationContext(),MainActivity.class));
                        //overridePendingTransition(0,0);
                        return true;

                    case  R.id.about:
                        startActivity(new Intent(getApplicationContext(),AboutActivity.class));
                        overridePendingTransition(0,0);
                        return true;

                }

                return false;
            }
        });

        recyclerView = findViewById(R.id.recycleView);
        addBotao = findViewById(R.id.addBotao);
        txt_SemFilmes = findViewById(R.id.txtSemFilme);
        img_vazio = findViewById(R.id.imgVazio);


        //Adicionando os listenners

        addBotao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,AdicionarActivity.class);
                startActivity(intent);

            }
        });

        myDB = new DataHelperClasse(MainActivity.this);

        book_id = new ArrayList<>();
        book_title = new ArrayList<>();
        book_author = new ArrayList<>();
        book_pages = new ArrayList<>();

        guardarDadosArray();



        CustomAdapter customAdapter = new CustomAdapter(MainActivity.this,this,book_id,book_title,book_author,book_pages);
        recyclerView.setAdapter(customAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1){
            recreate();
        }
    }

    void guardarDadosArray (){
        Cursor cursor = myDB.readAllData();

        if (cursor.getCount() == 0){
            img_vazio.setVisibility(View.VISIBLE);
            txt_SemFilmes.setVisibility(View.VISIBLE);
        }else {
            while (cursor.moveToNext()){
                book_id.add(cursor.getString(0));
                book_title.add(cursor.getString(1));
                book_author.add(cursor.getString(2));
                book_pages.add(cursor.getString(3));
            }
            img_vazio.setVisibility(View.GONE);
            txt_SemFilmes.setVisibility(View.GONE);
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.my_menu, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
       if (item.getItemId() == R.id.delete_all);
            confirmarDialogo();

        return super.onOptionsItemSelected(item);
    }


    void confirmarDialogo(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Apagar tudo");
        builder.setMessage("Tem certeza que quer deletar todos os dados? ");
        builder.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                DataHelperClasse meuBanco = new DataHelperClasse(MainActivity.this);
                meuBanco.deletarTudo();
                Intent intent = new Intent(MainActivity.this,MainActivity.class);
                startActivity(intent);
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