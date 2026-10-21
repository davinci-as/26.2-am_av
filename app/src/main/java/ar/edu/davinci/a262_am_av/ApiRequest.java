package ar.edu.davinci.a262_am_av;

import android.os.AsyncTask;
import android.util.Log;

import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class ApiRequest extends AsyncTask<String, Integer, String> {

    OkHttpClient client = new OkHttpClient();

    String run(String url) throws IOException {
        Request request = new Request.Builder()
                .url(url)
                .build();

        try (Response response = client.newCall(request).execute()) {
            return response.body().string();//String
        }
    }

    @Override
    protected String doInBackground(String... strings) {
        //llamado a la biblioteca
        String url = strings[0];
        try {
            return this.run(url);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        /*JSONObject response = new JSONObject(s);
        JSONArray results = (JSONArray) response.get("results");
        JSONObject morthySmith = (JSONObject) results.get(0);
        String name = (String) morthySmith.get("name");*/
        Gson gson = new Gson();
        ApiResponse response = gson.fromJson(s, ApiResponse.class);
        ArrayList<Character> results = response.getResults();
        Character mortySmith = results.get(0);
        Log.i("on-post", String.valueOf(mortySmith.getGender()));

    }
}
