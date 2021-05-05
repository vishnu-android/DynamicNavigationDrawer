package com.example.dynamicnavigationdrawer;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.example.dynamicnavigationdrawer.ui.gallery.LocationsFragment;
import com.example.dynamicnavigationdrawer.ui.home.HomeFragment;
import com.example.dynamicnavigationdrawer.ui.slideshow.SettingsFragment;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity {
    DrawerLayout drawer;
    NavigationView navigationView;
    MenuItem menuItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        drawer = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);
        menuItem = navigationView.getMenu().findItem(R.id.locations);
        addCityToMenu("London");
        addCityToMenu("New York");
        addCityToMenu("Milan");
        addCityToMenu("Paris");
        addCityToMenu("Monaco");

        ActionBarDrawerToggle drawerToggle = new ActionBarDrawerToggle(this, drawer, toolbar,
                R.string.navigation_drawer_open,
                R.string.navigation_drawer_close);
        //set menu icon
        drawerToggle.setHomeAsUpIndicator(R.drawable.ic_baseline_menu_24);
        drawerToggle.setDrawerIndicatorEnabled(false);
        drawerToggle.setToolbarNavigationClickListener(view -> drawer.openDrawer(
                GravityCompat.START));

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.nav_host_fragment,
                    new HomeFragment()).commit();
        }

        navigationView.setNavigationItemSelectedListener(item -> {
            switch (item.getItemId()) {
                case R.id.nav_locations:
                    getSupportFragmentManager().beginTransaction().replace(R.id.nav_host_fragment,
                            new LocationsFragment()).commit();
                    drawer.closeDrawers();
                    break;
                case R.id.nav_settings:
                    getSupportFragmentManager().beginTransaction().replace(R.id.nav_host_fragment,
                            new SettingsFragment()).commit();
                    drawer.closeDrawers();
                    break;
                default:
                    break;
            }
            return true;
        });
    }

    private void addCityToMenu(String city) {
        menuItem.getSubMenu().add(Menu.NONE, R.id.nav_home, 1, city)
                .setOnMenuItemClickListener(item -> loadCity(city));
    }

    public boolean loadCity(String city) {
        Bundle bundle = new Bundle();
        bundle.putString("cityName", city);
        HomeFragment fragment = new HomeFragment();
        fragment.setArguments(bundle);
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.nav_host_fragment, fragment)
                .commit();
        drawer.closeDrawers();
        return true;
    }
}