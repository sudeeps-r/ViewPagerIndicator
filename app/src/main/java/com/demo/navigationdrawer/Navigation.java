package com.demo.navigationdrawer;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.PersistableBundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.RelativeLayout;

public class Navigation extends AppCompatActivity {

    private DrawerLayout mDrawerLayout;
    private RecyclerView mRecyclerView;
    private RecyclerViewAdapter mAdapter;
    private ActionBarDrawerToggle mDrawerToggle;
    private RelativeLayout mRelativeLayout;
    private Toolbar mToolbar;

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation);

        mToolbar = (Toolbar) findViewById(R.id.tool);

        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mRelativeLayout = (RelativeLayout) findViewById(R.id.first_frame);
        initToolbar();
        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        mAdapter = new RecyclerViewAdapter(getApplicationContext());
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.setAdapter(mAdapter);

        mAdapter.setOnTupleClickListener(new RecyclerViewAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(String option) {
                if (option.equals("Search")) {
                    Intent intent = new Intent(Navigation.this, SearchActivity.class);
                    startActivity(intent);
                }
            }
        });

        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, mToolbar, R.string.open_drawer, R.string.close_drawer) {

            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
            }

            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {
                super.onDrawerSlide(drawerView, slideOffset);
                float moveFactor = (mRecyclerView.getWidth() * slideOffset);
                mRelativeLayout.setTranslationX(moveFactor);
            }
        };
        mDrawerLayout.setDrawerListener(mDrawerToggle);
    }

    private void initToolbar() {
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_drawer);
    }

    @Override
    public void onPostCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onPostCreate(savedInstanceState, persistentState);

        //mDrawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        mDrawerToggle.onConfigurationChanged(newConfig);
    }
}
