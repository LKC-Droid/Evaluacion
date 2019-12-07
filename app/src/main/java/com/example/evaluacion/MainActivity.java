package com.example.evaluacion;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.evaluacion.DAL.EquipoDAL;
import com.example.evaluacion.DAO.Equipo;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private EquipoDAL equipoDAL;
    private ListView listEquipos;
    private ArrayAdapter<Equipo> adapter;
    private ArrayList<Equipo> listaEquipos;
    private int posSerie;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.equipoDAL = new EquipoDAL(getApplicationContext(), new Equipo());
        this.listaEquipos = new EquipoDAL(getBaseContext()).seleccionar();
        this.listEquipos = (ListView) findViewById(R.id.listEquipos);
        this.adapter = new ArrayAdapter<Equipo>(
                getApplicationContext(),android.R.layout.simple_list_item_1,this.listaEquipos

        );
        this.listEquipos.setAdapter(adapter);


        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setTitle("Confirmación");
        builder.setMessage("¿Desea borrar el equipo?");
        builder.setPositiveButton("Si", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                int serie = ((Equipo) listaEquipos.get(posSerie)).getSerie();

                if(equipoDAL.eliminar(serie)){
                    Toast.makeText(getApplicationContext(), "Se ha eliminado correctamente", Toast.LENGTH_SHORT).show();
                    actualizarLista();
                } else {
                    Toast.makeText(getApplicationContext(), "No se ha eliminado el equipo", Toast.LENGTH_LONG).show();
                }
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.cancel();
            }
        });

        final AlertDialog dialog = builder.create();


        listEquipos.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                posSerie = i;
                dialog.show();
                return false;
            }
        });

        listEquipos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                posSerie = i;
                abrirEditar();

            }
        });

    }

    private void abrirEditar() {
        Intent intento = new Intent(MainActivity.this, EditarEquipoActivity.class);
        Equipo e = (Equipo) listaEquipos.get(posSerie);

        intento.putExtra("equipo", e);
        startActivityForResult(intento, 100);
    }

    private void actualizarLista() {
        adapter.clear();
        adapter.addAll(equipoDAL.seleccionar());
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onResume(){
        super.onResume();
        actualizarLista();

    }



}
