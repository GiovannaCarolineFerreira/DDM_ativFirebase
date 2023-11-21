package giovanna2005.ativ_firebase;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private FirebaseHelper firebaseHelper;
    private EditText nomeEditText, pesoEditText, idadeEditText, sexoEditText, descritivoEditText;
    private RadioGroup diaSemanaRadioGroup;
    private ListView listViewAtletas;
    private List<Atleta> atletasList;
    private ArrayAdapter<Atleta> arrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        firebaseHelper = new FirebaseHelper();
        nomeEditText = findViewById(R.id.nomeEditText);
        pesoEditText = findViewById(R.id.pesoEditText);
        idadeEditText = findViewById(R.id.idadeEditText);
        sexoEditText = findViewById(R.id.sexoEditText);
        diaSemanaRadioGroup = findViewById(R.id.diaSemanaRadioGroup);
        descritivoEditText = findViewById(R.id.descritivoEditText);
        listViewAtletas = findViewById(R.id.listViewAtletas);

        findViewById(R.id.salvarButton).setOnClickListener(v -> salvarAtleta());
        atletasList = new ArrayList<>();
        arrayAdapter = new ArrayAdapter<>(this, R.layout.list_item_atleta, R.id.nomeTextView, atletasList);
        listViewAtletas.setAdapter(arrayAdapter);
        setupAtletasListView();
    }

    private void salvarAtleta() {
        String nome = nomeEditText.getText().toString();
        String peso = pesoEditText.getText().toString();
        String idade = idadeEditText.getText().toString();
        String sexo = sexoEditText.getText().toString();
        int selectedRadioButtonId = diaSemanaRadioGroup.getCheckedRadioButtonId();
        RadioButton selectedRadioButton = findViewById(selectedRadioButtonId);
        String diaSemana = selectedRadioButton.getText().toString();
        String descritivoTreino = descritivoEditText.getText().toString();

        Atleta atleta = new Atleta(nome, peso, idade, sexo, diaSemana, descritivoTreino);
        firebaseHelper.addAtleta(atleta);

        // Limpar campos após salvar
        nomeEditText.setText("");
        pesoEditText.setText("");
        idadeEditText.setText("");
        sexoEditText.setText("");
        diaSemanaRadioGroup.clearCheck();
        descritivoEditText.setText("");
    }

    private void setupAtletasListView() {
        firebaseHelper.getAtletasReference().addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                atletasList.clear();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    Atleta atleta = snapshot.getValue(Atleta.class);
                    atletasList.add(atleta);
                }
                arrayAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(MainActivity.this, "Erro ao obter dados: " + databaseError.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}