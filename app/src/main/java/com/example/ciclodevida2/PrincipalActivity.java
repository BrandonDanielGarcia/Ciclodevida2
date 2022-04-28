package com.example.ciclodevida2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.view.View;

import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.List;

public class PrincipalActivity extends AppCompatActivity {

    EditText tnombre, tedad;
    Button btnagregar,btnmostrar;
    FloatingActionButton bfinfo;
    DatoModel datoModel;
    List<Datos> lista = new ArrayList<>();
    GridView gridLayout;
    ArrayList<String> nombres = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);

        tnombre = findViewById(R.id.txtnombre);
        tedad = findViewById(R.id.txtedad);
        btnagregar = findViewById(R.id.btnagregar);
        btnmostrar = findViewById(R.id.btnmostrar);
        bfinfo = findViewById(R.id.bfinfo);


        datoModel = new ViewModelProvider(this).get(DatoModel.class);
        gridLayout = (GridView) findViewById(R.id.gridView1);

        cargarGrid();

        btnagregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Datos datos = new Datos(tnombre.getText().toString(),Integer.valueOf(tedad.getText().toString()));
                datoModel.agregar(datos);
            }
        });



        btnmostrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                lista = datoModel.getDatos();
                nombres.clear();
                nombres.add("NOMBRE");
                nombres.add("EDAD");
                for(Datos dato : lista){
                    // recorro la lista
                    nombres.add(dato.getNombre());
                    nombres.add(Integer.toString(dato.getEdad()));
                }
                datoModel.setArray(nombres);
                cargarGrid();
            }
        });



        bfinfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //snackbar
                Snackbar.make(view, "Barra de mensaje", Snackbar.LENGTH_LONG);
                Toast.makeText(PrincipalActivity.this, "Mensaje", Toast.LENGTH_SHORT).show();
            }
        });
    }
    public void cargarGrid(){
        ArrayAdapter<String> adaptador;
        adaptador = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,datoModel.getArray());
        gridLayout.setAdapter(adaptador);
    }
}