package com.example.listycitylab3;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity implements AddCityFragment.AddCityDialogueListener, EditCityFragment.EditCityDialogueListener {

    private ArrayList<City> dataList;
    private ListView cityList;
    private CityArrayAdapter cityAdapter;
    private Integer editingPosition = -1;

    @Override
    public void AddCity(City city) {
        cityAdapter.add(city);
        cityAdapter.notifyDataSetChanged();
    }

    @Override
    public void EditCity(City city) {
        dataList.set(editingPosition, city);
        cityAdapter.notifyDataSetChanged();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String[] cities = { "Edmonton", "Vancouver", "Toronto" };
        String[] provinces = { "AB", "BC", "ON" };

        dataList = new ArrayList<>();
        for (int index = 0; index < cities.length; index++) {
            dataList.add(new City(cities[index], provinces[index]));
        }


        cityList = findViewById(R.id.city_list);
        cityAdapter = new CityArrayAdapter(this, dataList);
        cityList.setAdapter(cityAdapter);

        // Adding Cities Button
        FloatingActionButton fab = findViewById(R.id.button_add_city);
        fab.setOnClickListener(v -> {
            new AddCityFragment().show(getSupportFragmentManager(), "Add City");
        });

        // Editing Clicked on Cities
        cityList.setOnItemClickListener((parent, view, position, id) -> {
            editingPosition = position;
            City currentCity = dataList.get(position);
            String currentCityName = currentCity.getName();
            String currentProvinceName = currentCity.getProvince();

            EditCityFragment fragment = EditCityFragment.newInstance(currentCityName, currentProvinceName);
            fragment.show(getSupportFragmentManager(), "Edit City");
        });
    }
}