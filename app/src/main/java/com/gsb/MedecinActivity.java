package com.gsb;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.List;

public class MedecinActivity extends AppCompatActivity {

    private String departement;
    private List<String> lesNoms;
    private ListView list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medecin);
        list = findViewById(R.id.list);
        departement = (String) getIntent().getSerializableExtra("departement");
        new LisNoms().execute();
    }
    //boutton retour
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        //nouvelle intention
        Intent intent = new Intent(this, MainActivity.class);
        //on lance l'activité principal
        startActivity(intent);
    }

    //class anonyme
    private class LisNoms extends AsyncTask {
        @Override
        protected Object doInBackground(Object[] params) {
            lesNoms = DAO.getLesNoms(departement);
            return null;
        }
        protected void onPostExecute(Object o) {
            ArrayAdapter<String> adapter = new ArrayAdapter<String>(MedecinActivity.this,
                    android.R.layout.simple_list_item_1, lesNoms);
            list.setAdapter(adapter);
        }
    }

}