package ryxandy.com.example.livrariaapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class AdicionarActivity extends AppCompatActivity {

EditText edtNome, edtAutor, edtPaginas;
Button btnAdicionar;
ActionBar actionBar;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adicionar);


        actionBar = getSupportActionBar();
        actionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#5851DB")));

    edtNome = findViewById(R.id.edtNome);
     edtAutor = findViewById(R.id.edtAutor);
     edtPaginas = findViewById(R.id.edtPagina2);
     btnAdicionar = findViewById(R.id.btnAdicionar);








     btnAdicionar.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View v) {
            DataHelperClasse meuBanco = new DataHelperClasse(AdicionarActivity.this);

            meuBanco.adicionarLivro(edtNome.getText().toString(),edtAutor.getText().toString(),Integer.valueOf(edtPaginas.getText().toString()));

         }
     });



        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        //Home itebm
        bottomNavigationView.setSelectedItemId(R.id.about);
        //O que vai acontecer quando selecionar esse item
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()){
                    case  R.id.adicionar:
                       // startActivity(new Intent(getApplicationContext(),AdicionarActivity.class));
                        //overridePendingTransition(0,0);
                        return true;

                    case  R.id.Home:
                        startActivity(new Intent(getApplicationContext(),MainActivity.class));
                        overridePendingTransition(0,0);
                        return true;

                    case  R.id.about:
                         startActivity(new Intent(getApplicationContext(),AboutActivity.class));
                         overridePendingTransition(0,0);
                        return true;

                }

                return false;
            }
        });



    }
}