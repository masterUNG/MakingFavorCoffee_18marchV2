package rmutsb.mook.chatchon.makingfavorcoffee;

import android.content.res.Configuration;
import android.support.annotation.Nullable;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import rmutsb.mook.chatchon.makingfavorcoffee.fragment.AmericanoFragment;
import rmutsb.mook.chatchon.makingfavorcoffee.fragment.CappucchinoFragment;
import rmutsb.mook.chatchon.makingfavorcoffee.fragment.EspressoFragment;
import rmutsb.mook.chatchon.makingfavorcoffee.fragment.HomeScoreFragment;
import rmutsb.mook.chatchon.makingfavorcoffee.fragment.LatteFragment;
import rmutsb.mook.chatchon.makingfavorcoffee.fragment.MochaFragment;
import rmutsb.mook.chatchon.makingfavorcoffee.fragment.ShowOrderFragment;
import rmutsb.mook.chatchon.makingfavorcoffee.ultility.MyManager;

public class CoffeeActivity extends AppCompatActivity implements View.OnClickListener {

    //    Explicit
    private DrawerLayout drawerLayout;
    private Toolbar toolbar;
    private ActionBarDrawerToggle actionBarDrawerToggle;
    private String[] loginStrings;
    private TextView mochaTextView, espressoTextView,
            latteTextView, cappucchinoTextView,
            americanoTextView, homeTextView;
    private MyManager myManager;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coffee);

        myManager = new MyManager(CoffeeActivity.this);

//        Test Add
//        myManager.addValueToSQLite("Test2", "Test3",
//                "Test4", "Test5", "Test6",
//                "Test7", "Test8", "Test9");



//        Initial View And Setcontroler
        InitialControler();


        //Create Toolbar
        createToolbar();

//        Add Fragment to Activity
        addFragment(savedInstanceState);

//        Show Order
        showOrder();

    } // Main Method

    private void showOrder() {

        TextView textView = findViewById(R.id.txtShowListOrder);

        Calendar calendar = Calendar.getInstance();
        DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        final String dateString = dateFormat.format(calendar.getTime());


        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.contentFragmentCoffee,
                                ShowOrderFragment.showOrderInstance(loginStrings, dateString))
                        .commit();
                drawerLayout.closeDrawers();

            }
        });
    }

    private void addFragment(Bundle savedInstanceState) {
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.contentFragmentCoffee, HomeScoreFragment.homeScoreInstance(loginStrings))
                    .commit();
        }
    }

    private void InitialControler() {

//        Initial
        homeTextView = findViewById(R.id.txtHome);
        mochaTextView = (TextView) findViewById(R.id.txtMocha);
        espressoTextView = (TextView) findViewById(R.id.txtEspresso);
        latteTextView = (TextView) findViewById(R.id.txtLatte);
        cappucchinoTextView = (TextView) findViewById(R.id.txtCappucchino);
        americanoTextView = (TextView) findViewById(R.id.txtAmericano);

        //    Controler

        homeTextView.setOnClickListener(CoffeeActivity.this);
        mochaTextView.setOnClickListener(CoffeeActivity.this);
        espressoTextView.setOnClickListener(CoffeeActivity.this);
        latteTextView.setOnClickListener(CoffeeActivity.this);
        cappucchinoTextView.setOnClickListener(CoffeeActivity.this);
        americanoTextView.setOnClickListener(CoffeeActivity.this);

    }


    private void createToolbar() {
        loginStrings = getIntent().getStringArrayExtra("Login");

        toolbar = (Toolbar) findViewById(R.id.toolbarCoffee);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(loginStrings[1] + " " + loginStrings[2]);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        drawerLayout = (DrawerLayout) findViewById(R.id.drawerMenu);
        actionBarDrawerToggle = new ActionBarDrawerToggle(CoffeeActivity.this,
                drawerLayout, R.string.open, R.string.close);
        drawerLayout.setDrawerListener(actionBarDrawerToggle);


    } // CreateToolbar

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        actionBarDrawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);

        actionBarDrawerToggle.onConfigurationChanged(newConfig);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (actionBarDrawerToggle.onOptionsItemSelected(item)) {
            return true;

        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.txtHome:

                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.contentFragmentCoffee, HomeScoreFragment.homeScoreInstance(loginStrings))
                        .commit();
                break;


            case R.id.txtMocha:

                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.contentFragmentCoffee, MochaFragment.mochaInstance(loginStrings))
                        .commit();
                break;
            case R.id.txtEspresso:
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.contentFragmentCoffee, EspressoFragment.espressoInstance(loginStrings))
                        .commit();
                break;
            case R.id.txtLatte:

                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.contentFragmentCoffee, LatteFragment.latteInstance(loginStrings))
                        .commit();
                break;
            case R.id.txtCappucchino:

                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.contentFragmentCoffee, CappucchinoFragment.cappucchinoInstance(loginStrings))
                        .commit();
                break;
            case R.id.txtAmericano:

                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.contentFragmentCoffee, AmericanoFragment.americanoInstance(loginStrings))
                        .commit();
                break;


        }//switch

        drawerLayout.closeDrawers();

    }//onClick
} // Main Class
