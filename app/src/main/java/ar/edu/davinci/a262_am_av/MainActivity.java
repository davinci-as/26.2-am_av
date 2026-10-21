package ar.edu.davinci.a262_am_av;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    public final int UPDATE_ITEM_VALUE = 1;

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode != UPDATE_ITEM_VALUE) return;
        if(resultCode != RESULT_OK) return;

        if (data != null && !data.hasExtra("newText")) return;

        String newText = data != null ? data.getStringExtra("newText") : "";

        TextView demoText = findViewById(R.id.demoText);
        demoText.setText(newText);

    }

    public void addContent(View view) {
        LinearLayout container = (LinearLayout) findViewById(R.id.container);
        for (int i = 0; i < 20; i++) {
            TextView genericText = new TextView(this);
            genericText.setText(String.format("Generic Text: %d", i));

            int finalI = i;
            genericText.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(getApplicationContext(), DetailsActivity.class);
                    intent.putExtra("identificador", finalI);
                    startActivityForResult(intent, UPDATE_ITEM_VALUE);

                }
            });
            container.addView(genericText);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        new ApiRequest().execute("https://rickandmortyapi.com/api/character");

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}