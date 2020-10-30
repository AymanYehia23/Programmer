package com.programmer;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;
import java.util.Objects;

import static com.programmer.Helper.*;

public class MainActivity extends AppCompatActivity {

    private DrawerLayout drawer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                drawer.closeDrawer(GravityCompat.START);
                switch (item.getItemId()){
                    case R.id.websites : setFragment(new WebsitesFragment(), "Websites"); break;
                    case R.id.tutorials : setFragment(new TutorialsFragment(), "Tutorials"); break;
                    case R.id.favorites : setFragment(new FavoritesFragment(), "Favorites"); break;
                    case R.id.share_the_app :
                        Intent shareIntent = new Intent(Intent.ACTION_SEND);
                        shareIntent.setType("text/plain");
                        String url = "https://play.google.com/store/apps/details?id=com.programmer";
                        shareIntent.putExtra(Intent.EXTRA_TEXT, url);
                        startActivity(shareIntent);
                        break;
                    case R.id.rate_the_app :
                        Intent rateIntent = new Intent(Intent.ACTION_VIEW);
                        rateIntent.setData(Uri.parse("https://play.google.com/store/apps/details?id=com.programmer"));
                        startActivity(rateIntent);
                        break;
                }
                return true;
            }
        });

        setFragment(new WebsitesFragment(), "Websites");
    }

    private void setFragment(Fragment fragment, String title){
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.coordinator_layout, fragment);
        Objects.requireNonNull(getSupportActionBar()).setTitle(title);
        fragmentTransaction.commit();
    }

    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) drawer.closeDrawer(GravityCompat.START);
        else super.onBackPressed();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.favorites : setFragment(new FavoritesFragment(), "Favorites"); break;
            case R.id.share_the_app :
                Intent shareIntent = new Intent(Intent.ACTION_SEND);
                shareIntent.setType("text/plain");
                String url = "https://play.google.com/store/apps/details?id=com.programmer";
                shareIntent.putExtra(Intent.EXTRA_TEXT, url);
                startActivity(shareIntent);
                break;
            case R.id.rate_the_app :
                Intent rateIntent = new Intent(Intent.ACTION_VIEW);
                rateIntent.setData(Uri.parse("https://play.google.com/store/apps/details?id=com.programmer"));
                startActivity(rateIntent);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

}