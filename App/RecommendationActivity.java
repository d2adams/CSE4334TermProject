package com.example.musicrecommender;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class RecommendationActivity extends AppCompatActivity{
    private ServerConnection server;
    private TextView[] textViewArtist;

    private int DisplayCapacity;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recommendation);

        DisplayCapacity = 5;
        server = new ServerConnection();
        textViewArtist = new TextView[DisplayCapacity];

        textViewArtist[0] = findViewById(R.id.eTRed1);
        textViewArtist[1] = findViewById(R.id.eTRed2);
        textViewArtist[2] = findViewById(R.id.eTRed3);
        textViewArtist[3] = findViewById(R.id.eTRed4);
        textViewArtist[4] = findViewById(R.id.eTRed5);
        sendQuery("demo");
    }

    public void sendQuery(String query) {
        server.SendRecommendationData(query,2,this);
    }

    public void ReceivedQueryResult(JSONObject ReceivedJSonObj){
        DisplayQueryResult(ReceivedJSonObj);
    }
    public void DisplayQueryResult(JSONObject ReceivedJSonObj){
        try {
            JSONArray arrJsonArtist = ReceivedJSonObj.getJSONArray("Artist");
            String arrArtist[] = new String[arrJsonArtist.length()];
            int totalResult = arrJsonArtist.length();
            int loopcount = totalResult;
            if (totalResult > DisplayCapacity){
                loopcount = DisplayCapacity;
            }

            for(int i = 0; i < loopcount; i++) {
                arrArtist[i] = arrJsonArtist.getString(i);
                Log.d("Artist", arrArtist[i]);
                textViewArtist[i].setText(arrArtist[i]);
            }
        } catch (JSONException e) {
            Log.d("No Artist", "check code " );
        }

    }
}
