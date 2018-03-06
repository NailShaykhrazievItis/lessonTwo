package com.itis.android.lessontwo.ui.base;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import com.itis.android.lessontwo.R;
import com.itis.android.lessontwo.ui.comicslist.ComicsListActivity;
import com.itis.android.lessontwo.ui.creatorslist.CreatorsListActivity;

public abstract class BaseActivity extends AppCompatActivity {

    protected DrawerLayout mDrawer;
    protected NavigationView mNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);
        mDrawer = findViewById(R.id.drawer_layout);
        mNavigationView = findViewById(R.id.nav_view);
    }

    @Override
    public void onBackPressed() {
        if (mDrawer.isDrawerOpen(GravityCompat.START)) {
            mDrawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    protected void supportActionBar(Toolbar toolbar) {
        setSupportActionBar(toolbar);
        initNavigationDrawer(toolbar);
    }

    protected void setBackArrow(Toolbar toolbar) {
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
            toolbar.setNavigationOnClickListener(v -> onBackPressed());
        }
    }

    private void initNavigationDrawer(Toolbar toolbar) {
        mNavigationView.setNavigationItemSelectedListener(menuItem -> {
            int id = menuItem.getItemId();
            switch (id) {
                case R.id.menu_comics:
                    Intent intent = new Intent(getApplicationContext(), ComicsListActivity.class);
                    startActivity(intent);
                    break;
                case R.id.menu_creators:
                    Intent creatorsIntent = new Intent(getApplicationContext(), CreatorsListActivity.class);
                    startActivity(creatorsIntent);
                    break;
               // case R.id.menu_characters:
                    //Intent charactersIntent = new Intent(getApplicationContext(), CharactersListActivity.class);
                    //startActivity(charactersIntent);
                   // break;
            }
            return true;
        });
        /*
          Если хотите получить элементы из верхней части бокового меню, то это делается так.
          Example:
          View header = mNavigationView.getHeaderView(0);
          TextView menuText = header.findViewById(R.id.tv_menu);
         */
        setActionBar(toolbar);
    }

    private void setActionBar(Toolbar toolbar) {
        ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle(this, mDrawer, toolbar,
                R.string.drawer_open, R.string.drawer_close);
        mDrawer.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();
    }
}
