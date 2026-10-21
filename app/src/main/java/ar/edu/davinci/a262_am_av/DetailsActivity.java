package ar.edu.davinci.a262_am_av;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class DetailsActivity extends AppCompatActivity {

    public void onClick(View v) {
        EditText newTextValue = findViewById(R.id.newTextValue);
        String newText = String.valueOf(newTextValue.getText());
        Intent replayIntent = new Intent();
        replayIntent.putExtra("newText", newText);
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

            String image = "https://images.pexels.com/photos/9221307/pexels-photo-9221307.jpeg?_gl=1*1wi02ly*_ga*MTQxNDM2OTE0Mi4xNzkxOTQyNDA3*_ga_8JE65Q40S6*czE3OTE5NDI0MDYkbzEkZzAkdDE3OTE5NDI0MDYkajYwJGwwJGgw";
            //revisar si tengo conexión a internet
                //ejecución del worker
                ImageView detailsImage = findViewById(R.id.detailsImage);
                new ImageDownloader(detailsImage).execute(image);
        }


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}