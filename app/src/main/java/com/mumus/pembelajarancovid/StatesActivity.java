package com.mumus.pembelajarancovid;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.mumus.pembelajarancovid.Adapter.StateAdapter;
import com.mumus.pembelajarancovid.Model.AllStates;
import com.mumus.pembelajarancovid.Model.ListItem;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class StatesActivity extends AppCompatActivity implements SearchView.OnQueryTextListener{

    private static final String URL_DATA = "https://api.kawalcorona.com/indonesia/provinsi";
    private RecyclerView recyclerView;
    StateAdapter adapter;

    private List<AllStates> listStateItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_states);


            recyclerView = findViewById(R.id.recyclerState);
            recyclerView.setHasFixedSize(true);
            recyclerView.setLayoutManager(new LinearLayoutManager(this));

            listStateItems = new ArrayList<>();
            adapter = new StateAdapter(listStateItems, getApplicationContext());

        loadRecyclerViewData();

        }

        private void loadRecyclerViewData() {
            final ProgressDialog progressDialog = new ProgressDialog(this);
            progressDialog.setMessage("Loading Data...");
            progressDialog.show();


            JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, URL_DATA, null,  new Response.Listener<JSONArray>() {
                @Override
                public void onResponse(JSONArray response) {
                    try {
                        for(int i=0; i<response.length(); i++){

                            JSONObject data = response.getJSONObject(i);

                            AllStates item = new AllStates(
                                    data.getString("Kode Provi"),
                                    data.getInt("Provinsi"),
                                    data.getInt("Kasus_Posi"),
                                    data.getInt("Kasus_Semb"),
                                    data.getInt("Kasus_Meni")
                            );

                            listStateItems.add(item);

                        }

                        recyclerView.setAdapter(adapter);
                        progressDialog.dismiss();

                    }
                    catch (JSONException e) {
                        e.printStackTrace();
                    }

                }


            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    progressDialog.dismiss();
                    Log.d("TAG", error.getMessage());

                }
            });
            RequestQueue requestQueue = Volley.newRequestQueue(this);

            requestQueue.add(jsonArrayRequest);


        }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.search, menu);
        MenuItem item = menu.findItem(R.id.app_bar_search);

        SearchView search = (SearchView) item.getActionView();
        search.setOnQueryTextListener(this);

        return super.onCreateOptionsMenu(menu);
    }


    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        String user = newText.toLowerCase();
        List<AllStates> newList = new ArrayList<>();

        for(AllStates name : listStateItems){
            if(name.getState().toLowerCase().contains(user)){
                newList.add(name);
            }
        }
        adapter.updateList(newList);
        return true;    }
}
