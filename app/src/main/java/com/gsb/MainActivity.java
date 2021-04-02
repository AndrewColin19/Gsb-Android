package com.gsb;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private List<String> lesNoms;
    private ListView list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.list = findViewById(R.id.list);
        //execute une tache en fond ici une requete HTTP
        //qui va recupérer les depardements
        new LisDepratements().execute();
    }

    //class anonyme
    private class LisDepratements extends AsyncTask {
        //code excuter en fond
        @Override
        protected Object doInBackground(Object[] params) {
            lesNoms = DAO.getLesDepartements();
            return null;
        }
        //une fois cela fais affiche les departements
        protected void onPostExecute(Object o) {
            ArrayAdapter<String> adapter = new ArrayAdapter<String>(MainActivity.this,
                    android.R.layout.simple_list_item_1, lesNoms);
            list.setAdapter(adapter);
            //listenner d'une pression sur un item dans la liste qui contient les
            //departements
            list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    //recupere le num du departement
                    String dep = (String) parent.getItemAtPosition(position);
                    //cree une nouvelle intention
                    Intent intent = new Intent(MainActivity.this, MedecinActivity.class);
                    //on ajoute le num du departement
                    intent.putExtra("departement", dep);
                    //on lance une nouvelle activité
                    startActivity(intent);
                }
            });
        }
    }
}