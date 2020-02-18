package com.example.epiapp.views.fragments;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.example.epiapp.R;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.example.epiapp.R.array.buildings;

/**
 * A simple {@link Fragment} subclass.
 */
public class HouseFragment extends Fragment {

    @BindView(R.id.spinner_buildings)
    Spinner spinnerBuldings;
    @BindView(R.id.edittext_other)
    EditText editTextOther;


    public HouseFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_house, container, false);
        ButterKnife.bind(this, view);
        ArrayAdapter<String> numAdapter = new ArrayAdapter<>(container.getContext(),
                android.R.layout.simple_spinner_dropdown_item, getResources().getStringArray(buildings));
        spinnerBuldings.setAdapter(numAdapter);


        spinnerBuldings.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (i == 5) {
                    editTextOther.setVisibility(View.VISIBLE);
                } else {
                    editTextOther.setVisibility(View.GONE);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        return view;
    }

}
