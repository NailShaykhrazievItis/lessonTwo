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
import android.view.View;
import android.widget.ImageView;

import com.itis.android.lessontwo.R;
import com.itis.android.lessontwo.ui.characterlist.CharacterListActivity;
import com.itis.android.lessontwo.ui.comicslist.ComicsListActivity;
import com.itis.android.lessontwo.ui.creatorslist.CreatorListActivity;
import com.itis.android.lessontwo.utils.ImageLoadHelper;

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
                case R.id.menu_characters:
                    intent = new Intent(getApplicationContext(), CharacterListActivity.class);
                    startActivity(intent);
                    break;
                case R.id.menu_creators:
                    intent = new Intent(getApplicationContext(), CreatorListActivity.class);
                    startActivity(intent);
                    break;
            }
            return true;
        });
        /*
          Если хотите получить элементы из верхней части бокового меню, то это делается так.
          Example:
          View header = mNavigationView.getHeaderView(0);
          TextView menuText = header.findViewById(R.id.tv_menu);
         */
        View header = mNavigationView.getHeaderView(0);
        ImageView menuCover = header.findViewById(R.id.iv_cover);
        ImageLoadHelper.loadPictureByDrawable(menuCover, R.drawable.image_marvel_logo);
        setActionBar(toolbar);
    }

    private void setActionBar(Toolbar toolbar) {
        ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle(this, mDrawer, toolbar,
                R.string.drawer_open, R.string.drawer_close);
        mDrawer.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();
    }
}
