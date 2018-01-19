package com.example.administrador.superagentecomercio.superagente;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.example.administrador.superagentecomercio.R;
import com.example.administrador.superagentecomercio.utils.Constante;

import java.util.Timer;
import java.util.TimerTask;

public class Splash extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash);
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                Intent loginIntent = new Intent().setClass(Splash.this, LoginNumeroComercio.class);
                startActivity(loginIntent);
                finish();
            }
        };

        Timer timer = new Timer();
        timer.schedule(task, Constante.TIEMPO_SPLASH_SUPER_AGENTE);
    }
}
