package com.codeasylums.stockmarket;

import com.google.android.gms.common.api.GoogleApiClient;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager shareDataLayoutManager;
    private RecyclerView.Adapter shareDataAdapter;
    //<class to get the data>
    private List<YourClass> object;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        shareDataLayoutManager=new LinearLayoutManager(this);
        recyclerView.setLayoutManager(shareDataLayoutManager);
        //myadapter is the adapter class
        shareDataAdapter=new myadapter(object);
        recyclerView.setAdapter(shareDataAdapter);


        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

      getShareData();

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        }
        else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        displaySelectedScreen(item.getItemId());
        return true;
    }

    private void displaySelectedScreen(int itemId) {

        //creating fragment object
        Fragment fragment = null;

        //initializing the fragment object which is selected
        switch (itemId) {
            case R.id.stockmarket:
                fragment = new AboutStockMarketFragment();
                break;
            case R.id.mystocks:
                fragment = new MyStocksFragment();
                break;
            case R.id.mytransactions:
                fragment = new MyTransactionsFragment();
                break;
            case R.id.aboutapp:
                fragment = new AboutAppFragment();
                break;
            case R.id.aboutus:
                fragment = new AboutUsFragment();
                break;
        }

        //replacing the fragment
        if (fragment != null) {
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.content_frame, fragment);
            ft.commit();
        }
        /*LayoutInflater inflater = getLayoutInflater();
        RelativeLayout container = (RelativeLayout) findViewById(R.id.content_frame);
        inflater.inflate(R.layout.activity_main, container);*/

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
    }


    public void getShareData(){

      final List<SharesData> shareDataList=new ArrayList<>();

      new GetStockData().getStockData("GOOGL", new ShareRateCallBack() {
        @Override
        public void onSuccess(String shareRate) {
shareDataList.get(0).setShareRate(shareRate);
        }
      });

      new GetStockData().getStockData("MSFT", new ShareRateCallBack() {
        @Override
        public void onSuccess(String shareRate) {

        }
      });
      new GetStockData().getStockData("AAPL", new ShareRateCallBack() {
        @Override
        public void onSuccess(String shareRate) {

        }
      });
      new GetStockData().getStockData("FB", new ShareRateCallBack() {
        @Override
        public void onSuccess(String shareRate) {

        }
      });


    }

}