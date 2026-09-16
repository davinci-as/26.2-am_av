package ar.edu.davinci.a262_am_av;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class DetailsActivity extends AppCompatActivity {

    public void onClick(View v) {
        Intent replayIntent = new Intent();
        replayIntent.putExtra("newText", "20");
        setResult(RESULT_OK, replayIntent);
        finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_details);

        Intent intent = getIntent();

        if(intent.hasExtra("identificador")) {
            int identificador = intent.getIntExtra("identificador", 0);
            Log.i("identificador", String.valueOf(identificador));
            TextView idViewer = findViewById(R.id.id_viewer);
            idViewer.setText(String.valueOf(identificador));
        }


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}