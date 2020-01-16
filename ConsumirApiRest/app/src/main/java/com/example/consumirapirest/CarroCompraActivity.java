package com.example.consumirapirest;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.example.consumirapirest.adapters.CarritoAdapter;
import com.example.consumirapirest.model.Insumo;

import java.util.ArrayList;
import java.util.List;

public class CarroCompraActivity extends AppCompatActivity {
    ArrayList<Insumo> carroCompras;

    CarritoAdapter adaptador;

    RecyclerView rvListaCarro;
    TextView tvTotal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_carro_compra);


        carroCompras = (ArrayList<Insumo>) getIntent().getSerializableExtra("CarroCompras");

        rvListaCarro = findViewById(R.id.rvListaCarro);
        rvListaCarro.setLayoutManager(new GridLayoutManager(CarroCompraActivity.this, 1));
        tvTotal = findViewById(R.id.tvTotal);

        adaptador = new CarritoAdapter(CarroCompraActivity.this, carroCompras, tvTotal);
        rvListaCarro.setAdapter(adaptador);

    }
}
