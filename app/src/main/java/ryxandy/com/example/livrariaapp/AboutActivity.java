package ryxandy.com.example.livrariaapp;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.media.Image;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class AboutActivity extends AppCompatActivity {

  ActionBar actionBar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.about_me);

        //Configurações da actionBar
        actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(false);
        actionBar.setHomeButtonEnabled(false);
        actionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#5851DB")));


        //ImageView imgwip = findViewById(R.id.imgWip);


        //Inicializar e colocar variáveis do bottom navigation

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        //Home itebm
        bottomNavigationView.setSelectedItemId(R.id.about);
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
                        startActivity(new Intent(getApplicationContext(),MainActivity.class));
                        overridePendingTransition(0,0);
                        return true;

                    case  R.id.about:
                       // startActivity(new Intent(getApplicationContext(),AboutActivity.class));
                        // overridePendingTransition(0,0);
                        return true;

                }

                return false;
            }
        });
    }
}
