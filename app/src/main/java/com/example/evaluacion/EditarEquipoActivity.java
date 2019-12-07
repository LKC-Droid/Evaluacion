package com.example.evaluacion;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.evaluacion.DAL.EquipoDAL;
import com.example.evaluacion.DAO.Equipo;

public class EditarEquipoActivity extends AppCompatActivity {
    private EditText editMarca;
    private EditText editDesc;
    private Button btnEditar;
    private EquipoDAL equipoDAL;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar_equipo);

        this.editMarca = (EditText) findViewById(R.id.editMarca);
        this.editDesc = (EditText) findViewById(R.id.editDesc);
        this.btnEditar = (Button) findViewById(R.id.btnEditar);

        this.equipoDAL = new EquipoDAL(getApplicationContext(), (Equipo) getIntent().getSerializableExtra("equipo"));

        this.editMarca.setText(equipoDAL.getEquipo().getMarca());
        this.editDesc.setText(equipoDAL.getEquipo().getDescripcion());

        btnEditar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Equipo e = equipoDAL.getEquipo();
                e.setMarca(String.valueOf(editMarca.getText()));
                e.setDescripcion(String.valueOf(editDesc.getText()));

                if (equipoDAL.actualizar(e)){
                    Toast.makeText(getApplicationContext(), "Se ha actualizado el equipo", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(getApplicationContext(), "No se ha podido actualizar el equipo", Toast.LENGTH_LONG).show();
                }
            }
        });

    }
}
