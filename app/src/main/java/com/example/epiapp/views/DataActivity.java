package com.example.epiapp.views;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.example.epiapp.R;
import com.example.epiapp.TabAdapter;
import com.example.epiapp.views.fragments.ContainerFragment;
import com.example.epiapp.views.fragments.HouseFragment;
import com.google.android.material.tabs.TabLayout;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DataActivity extends AppCompatActivity {

    public static final String KEY_LAT = "latitude";
    public static final String KEY_LONG = "longitude";


    @BindView(R.id.viewPager)
    ViewPager viewPager;
    @BindView(R.id.tabLayout)
    TabLayout tabLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data);

        ButterKnife.bind(this);

        TabAdapter adapter = new TabAdapter(getSupportFragmentManager());
        adapter.addFragment(new HouseFragment(), "Lugar");
        adapter.addFragment(new ContainerFragment(), "Peri Domiciliario");
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);
    }
}

