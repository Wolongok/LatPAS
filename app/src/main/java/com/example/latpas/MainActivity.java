package com.example.latpas;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private ArrayList<Model> modelList = new ArrayList<>();
    private static final String BASE_URL = "https://www.thesportsdb.com/api/v1/json/3/";
    private Adapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        adapter = new Adapter(this, modelList);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        fetchData();
    }

    private void fetchData() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        TeamService service = retrofit.create(TeamService.class);
        Call<TeamResponse> call = service.getTeams();
        call.enqueue(new Callback<TeamResponse>() {
            @Override
            public void onResponse(@NonNull Call<TeamResponse> call, @NonNull Response<TeamResponse> response) {
                if (response.isSuccessful()) {
                    TeamResponse teamResponse = response.body();
                    if (teamResponse != null) {
                        List<Team> teams = teamResponse.getTeams();
                        for (int i = 0; i < teams.size(); i++) {
                            modelList.add(new Model(teams.get(i).getStrTeam(), teams.get(i).getStrTeamBadge()));
                        }
                        adapter.notifyDataSetChanged();
                    }
                } else {
                    Toast.makeText(MainActivity.this, "Failed to get response", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(@NonNull Call<TeamResponse> call, @NonNull Throwable t) {
                Toast.makeText(MainActivity.this, "Network Failure: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}