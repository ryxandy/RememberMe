package ryxandy.com.example.livrariaapp;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;


public class SplashActivity extends AppCompatActivity {

    private static int SPLASH_TIME_OUT = 4000;
    ImageView imgLogo;
    TextView txtRecurso;
    ImageView imgConstrucao;
    Animation rotateAnimation;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_screen);
       // imgConstrucao = findViewById(R.id.imgConstrucao);
       // rotateAnimation();
        getSupportActionBar().hide();



       imgLogo = findViewById(R.id.imgLogoID);
      // txtRecurso = findViewById(R.id.txtRecursos);


       //método pra colocar a splashcreen e determinar o tempo
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent Homeintent = new Intent(SplashActivity.this,MainActivity.class);
                startActivity(Homeintent);
                finish();
            }
        },SPLASH_TIME_OUT);

    }
    //Métodoque cuida da animacao

    private void rotateAnimation() {

        rotateAnimation= AnimationUtils.loadAnimation(this,R.anim.rotate);
        imgConstrucao.startAnimation(rotateAnimation);

    }
}
