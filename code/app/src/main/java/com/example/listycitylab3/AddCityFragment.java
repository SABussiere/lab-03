package com.example.listycitylab3;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;

public class AddCityFragment extends DialogFragment {

    interface AddCityDialogueListener {
        void AddCity(City city);
    }

    private AddCityDialogueListener listener;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

        if (context instanceof AddCityDialogueListener) {
            listener = (AddCityDialogueListener) context;
        }
        else {
            throw new RuntimeException(context + "must implement AddCityDialogueListener");
        }
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.fragment_add_city, null);
        EditText addCityName = view.findViewById(R.id.add_text_city_text);
        EditText addProvinceName = view.findViewById(R.id.add_text_province_text);
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        return builder
                .setView(view)
                .setTitle("Add a city")
                .setNegativeButton("Cancel", null)
                .setPositiveButton("Add", (dialog, which) -> {
                    String cityName = addCityName.getText().toString();
                    String provinceName = addProvinceName.getText().toString();
                    listener.AddCity(new City(cityName, provinceName));
                })
                .create();
    }
}
