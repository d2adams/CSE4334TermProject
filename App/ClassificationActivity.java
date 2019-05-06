package com.example.musicrecommender;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class ClassificationActivity extends AppCompatActivity{

    private ServerConnection server;
    private TextView[] textViewArtist;
    private TextView[] textViewArtistProb;
    private int DisplayCpacity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_classification);
        DisplayCpacity = 3;
        server = new ServerConnection();
        textViewArtist = new TextView[DisplayCpacity];;
        textViewArtistProb = new TextView[DisplayCpacity];;
        textViewArtist[0] = findViewById(R.id.editTextArtist1);
        textViewArtist[1] = findViewById(R.id.editTextArtist2);
        textViewArtist[2] = findViewById(R.id.editTextArtist3);
        textViewArtistProb[0] = findViewById(R.id.editTextprobablity1);
        textViewArtistProb[1] = findViewById(R.id.editTextprobablity2);
        textViewArtistProb[2] = findViewById(R.id.editTextprobablity3);
    }

    public void sendQuery(String query) {
        server.SendClassificationData(query,1,this);
    }

    public void ReceivedQueryResult(JSONObject ReceivedJSonObj){
        DisplayQueryResult(ReceivedJSonObj);
    }

    public void DisplayQueryResult(JSONObject ReceivedJSonObj){
        try{
            JSONArray arrJsonArtist = ReceivedJSonObj.getJSONArray("Artist");
            String arrArtist[] = new String[arrJsonArtist.length()];

            JSONArray arrJsonArtDes = ReceivedJSonObj.getJSONArray("Prabablity");
            String ArtistProbablity[] = new String[arrJsonArtDes.length()];

            int totalResult = arrJsonArtist.length();
            for(int i = 0; i < totalResult; i++) {
                arrArtist[i] = arrJsonArtist.getString(i);
                ArtistProbablity[i] = arrJsonArtDes.getString(i);
                Log.d("Artist", arrArtist[i]);
                Log.d("Probablity", ArtistProbablity[i]);
                textViewArtist[i].setText(arrArtist[i]);
                textViewArtistProb[i].setText(ArtistProbablity[i]);
            }

        }catch (JSONException e) {
            Log.d("No Artist", "check code " );
        }

    }

    public void ClassificationQuery(View view){
        EditText editText = (EditText) findViewById(R.id.editTextquery);
        String message = editText.getText().toString();
        sendQuery(message);
    }
}
