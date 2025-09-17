package com.example.listycitylab3;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;

public class EditCityFragment extends DialogFragment {

    interface EditCityDialogueListener {
        void EditCity(City city);
    }

    private EditCityFragment.EditCityDialogueListener listener;

    public static EditCityFragment newInstance(String cityName, String provinceName) {
        EditCityFragment fragment = new EditCityFragment();
        Bundle args = new Bundle();

        args.putString("city_name", cityName);
        args.putString("province_name", provinceName);

        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

        if (context instanceof EditCityFragment.EditCityDialogueListener) {
            listener = (EditCityFragment.EditCityDialogueListener) context;
        }
        else {
            throw new RuntimeException(context + "must implement EditCityDialogueListener");
        }
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.fragment_edit_city, null);
        EditText editCityName = view.findViewById(R.id.edit_text_city_text);
        EditText editProvinceName = view.findViewById(R.id.edit_text_province_text);

        Bundle args = getArguments();
        if (args != null) {
            String cityName = args.getString("city_name", "");
            String provinceName = args.getString("province_name", "");
            editCityName.setText(cityName);
            editProvinceName.setText(provinceName);
        }

        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        return builder
                .setView(view)
                .setTitle("Edit city")
                .setNegativeButton("Cancel", null)
                .setPositiveButton("Save", (dialog, which) -> {
                    String cityName = editCityName.getText().toString().trim();
                    String provinceName = editProvinceName.getText().toString().trim();

                    if (!cityName.isEmpty() && !provinceName.isEmpty()) {
                        listener.EditCity(new City(cityName, provinceName));
                    }
                    else {
                        Toast.makeText(getContext(), "City and/or Province cannot be empty", Toast.LENGTH_SHORT).show();
                    }
                })
                .create();
    }
}
