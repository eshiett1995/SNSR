package com.seamsnstitches.snsr;

import android.app.Dialog;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.seamsnstitches.snsr.databinding.DialogFilterSearchBinding;
import com.seamsnstitches.snsr.fragments.OrdersFragment;
import com.seamsnstitches.snsr.fragments.UserSearchDialogFragment;
import com.seamsnstitches.snsr.models.FashionBrand;
import com.seamsnstitches.snsr.models.api.response.EmployeeModel;
import com.seamsnstitches.snsr.utility.AppConstants;
import com.seamsnstitches.snsr.utility.GetLetterImageResource;
import com.seamsnstitches.snsr.utility.SNSR_SharedPreferences.LoginStatusPreference;

import de.hdodenhof.circleimageview.CircleImageView;

public class HomeActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private static final String TAG = HomeActivity.class.getSimpleName();
    private MenuItem searchMenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Toolbar toolbar = findViewById(R.id.toolbar);
        //Hides the search menu and only shows it in OrderFragment
        //toolbar.getMenu().findItem(R.id.action_search).getActionView().setVisibility(View.INVISIBLE);
        setSupportActionBar(toolbar);


        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        if (getEmployeeDetails() != null) {
            String name = getEmployeeDetails().getEmployee().getFirstName() + " " + getEmployeeDetails().getEmployee().getLastName();
            ((TextView) navigationView.getHeaderView(0).findViewById(R.id.nav_header_employee_name))
                    .setText(name);
            ((TextView) navigationView.getHeaderView(0).findViewById(R.id.nav_header_employee_email))
                    .setText(getEmployeeDetails().getEmployee().getEmail());
            ((CircleImageView) navigationView.getHeaderView(0).findViewById(R.id.nav_header_image_view))
                    .setImageResource(getResources().getIdentifier(GetLetterImageResource
                                    .getLetterImageResource(name.toLowerCase().charAt(0)), "drawable",
                            "com.seamsnstitches.snsr"));

            Log.e(TAG, "From getLetterImageResource -- " + GetLetterImageResource
                    .getLetterImageResource(name.toLowerCase().charAt(0))
                    + " ---- actual resource id -----" + R.drawable.letter_o);
        }

        findViewById(R.id.log_out).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(HomeActivity.this, "Logging out", Toast.LENGTH_SHORT).show();
                LoginStatusPreference.initLoginStatusPreference(getApplicationContext());
                LoginStatusPreference.setIsLoggedIn(false);
                startActivity(new Intent(HomeActivity.this, LoginActivity.class));
                finish();
            }
        });
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    private EmployeeModel getEmployeeDetails() {
        if (getIntent() != null && getIntent().hasExtra(AppConstants.EMPLOYEE_DETAILS)) {
            Log.e(TAG, " getIntent() NOT NULL.. AND HAS KEY");
//            Log.e(TAG, " Fashion Brand ID from getIntent() ------ " +
//                    ((EmployeeModel)getIntent().getParcelableExtra(AppConstants.EMPLOYEE_DETAILS))
//            .getEmployee().getFashionBrand().getId());
            return ((EmployeeModel) getIntent().getParcelableExtra(AppConstants.EMPLOYEE_DETAILS));
        }
        Log.e(TAG, " getIntent() IS NULL OOOOOOOH.. ");
        return null;
    }

    private FashionBrand getFashionBrand() {
        Intent intent = getIntent();
        if (intent != null && intent.hasExtra(AppConstants.FASHION_BRAND)) {
            intent.setExtrasClassLoader(FashionBrand.class.getClassLoader());
            return ((FashionBrand) intent.getParcelableExtra(AppConstants.FASHION_BRAND));
        }
        return null;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.home, menu);
        searchMenu = menu.findItem(R.id.action_search);
        searchMenu.setVisible(false);
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

            //TODO: Remove the lines below
            Dialog dialog = new Dialog(HomeActivity.this);
            dialog.setContentView(R.layout.dialog_filter_search);
            dialog.setTitle("Title...");
            dialog.show();

            return true;
        } else if (id == R.id.action_search) {
            Dialog dialog = new Dialog(HomeActivity.this);
            dialog.setContentView(R.layout.dialog_filter_search);
            dialog.setTitle("Title...");
            dialog.show();
            DialogFilterSearchBinding searchBinding =
                    DataBindingUtil.bind(dialog.getCurrentFocus());
            searchBinding.btnCancel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                }
            });
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_orders) {

            FragmentTransaction fragmentTransaction =
                    getSupportFragmentManager().beginTransaction();
            if (getSupportFragmentManager().findFragmentByTag(
                    AppConstants.ORDERS_FRAGMENT_TAG) == null) {
                fragmentTransaction.replace(R.id.fragment_container,
                        OrdersFragment.newInstance(getOrderBundle()),
                        AppConstants.ORDERS_FRAGMENT_TAG).commit();
                fragmentTransaction.addToBackStack(null);
            }
            Log.e(TAG, "orders clicked !!!!!!!!!!!!!!!!!!");
        }
//        } else if (id == R.id.nav_gallery) {
//
//        } else if (id == R.id.nav_slideshow) {
//
//        } else if (id == R.id.nav_manage) {

        //}
        else if (id == R.id.nav_search) {
            UserSearchDialogFragment fragment = new UserSearchDialogFragment();
            if (getSupportFragmentManager().findFragmentByTag(
                    AppConstants.USER_SEARCH_DIALOG_FRAGMENT_TAG) == null) {
                fragment.show(getSupportFragmentManager(),
                        AppConstants.USER_SEARCH_DIALOG_FRAGMENT_TAG);
            }
//            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
//                    new UserSearchDialogFragment()).commit();
        }
//        else if (id == R.id.nav_send) {
//
//        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private Bundle getOrderBundle() {
        if (getFashionBrand() == null) return null;
        Bundle bundle = new Bundle();
        bundle.putParcelable(AppConstants.ORDERS_FRAGMENT_FASHION_BRAND, getFashionBrand());
        return bundle;
    }

    public void hideSearchMenuItem() {
        invalidateOptionsMenu();
        if (searchMenu == null) return;
        searchMenu.setVisible(false);
    }

    public void showSearchMenuItem() {
        invalidateOptionsMenu();
        if (searchMenu == null) return;
        searchMenu.setVisible(true);
    }
}
