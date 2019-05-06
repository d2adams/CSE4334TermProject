package com.example.musicrecommender;

import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v4.text.HtmlCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Context;
import android.content.Intent;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class SearchResultActivity extends AppCompatActivity {
    private ServerConnection server;
    private TextView[] textViewArtist;
    private TextView[] textViewArtistId;
    private int Capacity;
    private String[] token;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_result);
        Capacity = 3;
        server = new ServerConnection();
        textViewArtistId = new TextView[Capacity];
        textViewArtist = new TextView[Capacity];

        textViewArtistId[0] = findViewById(R.id.edTxtArtistName1);
        textViewArtistId[1] = findViewById(R.id.edTxtArtistName2);
        textViewArtistId[2] = findViewById(R.id.edTxtArtistName3);
        textViewArtistId[0] = findViewById(R.id.edTxtArtistDes1);
        textViewArtistId[1] = findViewById(R.id.edTxtArtistDes2);
        textViewArtistId[2] = findViewById(R.id.edTxtArtistDes3);
        Intent intent = getIntent();
        String message = intent.getStringExtra(SearchActivity.EXTRA_MESSAGE);
        token = message.split(" ");
        sendQuery(message);
    }

    public void sendQuery(String query) {
        server.SendSearchData(query, 0, this);
    }

    public void ReceivedQueryResult(JSONObject ReceivedJSONObj) {
        DisplayQueryResult(ReceivedJSONObj);
    }

    public void DisplayQueryResult(JSONObject ReceivedJSONObj) {
        try {
            JSONArray arrJsonArtist = ReceivedJSONObj.getJSONArray("Artist");
            String ArtistArr[] = new String[arrJsonArtist.length()];
            JSONArray arrJsonArtistId = ReceivedJSONObj.getJSONArray("Id");
            String ArtistIdArr[] = new String[arrJsonArtistId.length()];

            int totalRes = arrJsonArtist.length();
            int count = totalRes;
            if(totalRes > Capacity) {
                count = Capacity;
            }
            for(int i = 0; i < count; i++) {
                ArtistArr[i] = arrJsonArtist.getString(i);
                ArtistIdArr[i] = arrJsonArtistId.getString(i);
                Log.d("Artist", ArtistArr[i]);
                Log.d("Id", ArtistIdArr[i]);
                textViewArtist[i].setText(ArtistArr[i]);
                textViewArtistId[i].setText(ArtistIdArr[i]);
            }
        } catch(JSONException e) {
            Log.d("No Artist", "check code ");
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public void HighlightQueryTerm(TextView HighlightText) {
        for(int index = 0; index < token.length; index++) {
            Log.d("Highlight Term", token[index]);
            String replacedWith = "<font color ='red'>" + token[index].toLowerCase() + "</font>";
            String Original = HighlightText.getText().toString();
            String Modified = Original.replaceAll(token[index].toLowerCase(), replacedWith.toLowerCase());
            HighlightText.setText(Html.fromHtml(Modified, HtmlCompat.FROM_HTML_MODE_LEGACY));
        }
    }
}
