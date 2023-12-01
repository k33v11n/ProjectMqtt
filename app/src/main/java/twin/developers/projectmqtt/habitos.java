package twin.developers.projectmqtt;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class habitos extends AppCompatActivity {
    Button button;
    View view;
    Intent intent;
    public class Habito {
        public String habito1;
        public String habito2;
        public String habito3;
        public String habito4;
        public String habito5;

        // Constructor vacío requerido por Firebase
        public Habito() {
        }

        public Habito(String habito1, String habito2, String habito3, String habito4, String habito5) {
            this.habito1 = habito1;
            this.habito2 = habito2;
            this.habito3 = habito3;
            this.habito4 = habito4;
            this.habito5 = habito5;
        }
    }

    private Button btnAgregar;
    private Button btnLimpiar;
    private EditText habito1;
    private EditText habito2;
    private EditText habito3;
    private EditText habito4;
    private EditText habito5;

    // Referencia a la base de datos de Firebase
    private DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_habitos);
        // Inicializar Firebase
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference("habitos"); // "habitos" es el nombre de la colección en Firebase

        // Inicializar vistas
        btnAgregar = findViewById(R.id.btnagregar);
        btnLimpiar = findViewById(R.id.btnlimpiar);
        habito1 = findViewById(R.id.habito1);
        habito2 = findViewById(R.id.habito2);
        habito3 = findViewById(R.id.habito3);
        habito4 = findViewById(R.id.habito4);
        habito5 = findViewById(R.id.habito5);

        // Establecer onClickListener para el botón Agregar
        btnAgregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                agregarHabitos();
            }
        });

        // Establecer onClickListener para el botón Limpiar
        btnLimpiar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                limpiarHabitos();
            }
        });
    }

    private void agregarHabitos() {
        // Obtener el texto de cada EditText
        String textoHabito1 = habito1.getText().toString();
        String textoHabito2 = habito2.getText().toString();
        String textoHabito3 = habito3.getText().toString();
        String textoHabito4 = habito4.getText().toString();
        String textoHabito5 = habito5.getText().toString();

        // Crear un objeto Habito para enviar a Firebase (puedes adaptar esto según tus necesidades)
        Habito nuevoHabito = new Habito(textoHabito1, textoHabito2, textoHabito3, textoHabito4, textoHabito5);

        // Enviar el objeto Habito a Firebase
        databaseReference.push().setValue(nuevoHabito);

        // Limpiar los EditText después de enviar a Firebase (puedes ajustar esto según tus necesidades)
        limpiarHabitos();
    }

    private void limpiarHabitos() {
        // Limpiar el texto de cada EditText
        habito1.getText().clear();
        habito2.getText().clear();
        habito3.getText().clear();
        habito4.getText().clear();
        habito5.getText().clear();

        Button btnIrAPantalla2 = findViewById(R.id.btnsiguiente);
        btnIrAPantalla2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(habitos.this, MainActivity.class);
                startActivity(intent);
            }
        });


    }
}

