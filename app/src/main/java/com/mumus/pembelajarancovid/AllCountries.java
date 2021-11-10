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
import com.android.volley.toolbox.Volley;
import com.mumus.pembelajarancovid.Adapter.MyAdapter;
import com.mumus.pembelajarancovid.Model.ListItem;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class AllCountries extends AppCompatActivity implements SearchView.OnQueryTextListener{

    private static final String URL_DATA = "https://disease.sh/v3/covid-19/countries";
    private RecyclerView recyclerView;
    MyAdapter adapter;

    private List<ListItem> listItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_countries);

        recyclerView = findViewById(R.id.recycler);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        listItems = new ArrayList<>();
        adapter = new MyAdapter(listItems, getApplicationContext());

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

                        JSONObject jsonObject = response.getJSONObject(i);

                        ListItem item = new ListItem(
                                jsonObject.getString("country"),
                                jsonObject.getInt("cases"),
                                jsonObject.getInt("todayCases"),
                                jsonObject.getInt("deaths"),
                                jsonObject.getInt("todayDeaths"),
                                jsonObject.getInt("recovered")
                        );
                         listItems.add(item);

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
                Log.d("TAG", "Something went wrong");

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
        List<ListItem> newList = new ArrayList<>();

        for(ListItem name : listItems){
            if(name.getCountry().toLowerCase().contains(user)){
                newList.add(name);
            }
        }
        adapter.updateList(newList);
        return true;
    }
}
