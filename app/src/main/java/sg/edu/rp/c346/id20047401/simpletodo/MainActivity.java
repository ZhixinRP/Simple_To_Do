package sg.edu.rp.c346.id20047401.simpletodo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    EditText etTask;
    Button btnAdd, btnClear, btnDelete;
    ListView lvTask;
    ArrayList<String> alTasks;
    ArrayAdapter<String> aaTask;
    Spinner spinAddDelete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etTask = findViewById(R.id.editTextTask);
        btnAdd = findViewById(R.id.buttonAdd);
        btnClear = findViewById(R.id.buttonClear);
        btnDelete = findViewById(R.id.buttonDelete);
        lvTask = findViewById(R.id.listViewTask);
        spinAddDelete = findViewById(R.id.spinner);

        alTasks = new ArrayList<>();
        aaTask = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,alTasks);
        lvTask.setAdapter(aaTask);

        spinAddDelete.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0:
                        etTask.setHint("Type in a new task here");
                        btnAdd.setEnabled(true);
                        btnDelete.setEnabled(false);
                        break;
                    case 1:
                        etTask.setHint("Type in the index of the task to be removed");
                        btnDelete.setEnabled(true);
                        btnAdd.setEnabled(false);
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String newTask = etTask.getText().toString();
                alTasks.add(newTask);
                aaTask.notifyDataSetChanged();
                etTask.setText("");
            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (alTasks.size() == 0 ) {
                    Toast.makeText(MainActivity.this, "You don't have any task to remove", Toast.LENGTH_LONG).show();
                } else if (Integer.parseInt(etTask.getText().toString()) > (alTasks.size()-1)){
                    Toast.makeText(MainActivity.this, "Wrong index number", Toast.LENGTH_LONG).show();
                } else {
                    int indexTask = Integer.parseInt(etTask.getText().toString());
                    alTasks.remove(indexTask);
                    aaTask.notifyDataSetChanged();
                    etTask.setText("");
                }
            }
        });

        btnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alTasks.clear();
                aaTask.notifyDataSetChanged();
            }
        });

        lvTask.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(MainActivity.this, alTasks.get(position), Toast.LENGTH_LONG).show();
                etTask.setText(""+position);
            }
        });


    }
}