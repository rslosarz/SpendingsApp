package slosar.srt.spendingsapp.Screens.MainScreen;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.Toast;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import slosar.srt.spendingsapp.Exceptions.CategoryNameEmptyException;
import slosar.srt.spendingsapp.Exceptions.CategoryNameExistsException;
import slosar.srt.spendingsapp.Exceptions.DateFieldEmptyException;
import slosar.srt.spendingsapp.Exceptions.DateFromFutureException;
import slosar.srt.spendingsapp.Exceptions.ExceptionEvent;
import slosar.srt.spendingsapp.Exceptions.TitleTooShortException;
import slosar.srt.spendingsapp.Exceptions.ValueEmptyException;
import slosar.srt.spendingsapp.R;
import slosar.srt.spendingsapp.Screens.CategoriesView.CategoriesViewFragment;
import slosar.srt.spendingsapp.Screens.ManageCategories.ManageCategoriesFragment;
import slosar.srt.spendingsapp.Screens.StatsView.StatsViewFragment;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, IMainView {

    private MainPresenter mPresenter;
    private boolean isDbEmpty = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mPresenter = new MainPresenter(this, this);
        EventBus.getDefault().register(this);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        mPresenter.showData();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.nav_view_categories) {
            pushCategoriesViewFragment();
        } else if (id == R.id.nav_views_spendings_stats) {
            pushStatsViewFragment();
        } else if (id == R.id.nav_settings_manage_category) {
            pushManageCategoriesFragment();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void pushCategoriesViewFragment() {
        if (isDbEmpty) {
            isDbEmpty = mPresenter.isDbEmpty();
            Toast.makeText(this, getResources().getString(R.string.no_categories_defined), Toast.LENGTH_SHORT).show();
        } else {
            FragmentManager fm = getSupportFragmentManager();
            FragmentTransaction ft = fm.beginTransaction();

            ft.replace(R.id.main_activity_fragment, new CategoriesViewFragment());
            ft.commit();
        }
    }

    private void pushStatsViewFragment() {
        if (isDbEmpty) {
            isDbEmpty = mPresenter.isDbEmpty();
            Toast.makeText(this, getResources().getString(R.string.no_categories_defined), Toast.LENGTH_SHORT).show();
        } else {
            FragmentManager fm = getSupportFragmentManager();
            FragmentTransaction ft = fm.beginTransaction();

            ft.replace(R.id.main_activity_fragment, new StatsViewFragment());
            ft.commit();
        }
    }

    private void pushManageCategoriesFragment() {
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();

        ft.replace(R.id.main_activity_fragment, new ManageCategoriesFragment());
        ft.commit();
    }

    @Override
    public void showDbEmpty() {
        isDbEmpty = true;
        pushManageCategoriesFragment();
    }

    @Override
    public void showDbEntry() {
        isDbEmpty = false;
        pushCategoriesViewFragment();
    }

    @Subscribe
    public void handleException(ExceptionEvent e) {
        if (e.cause instanceof CategoryNameExistsException) {
            Toast.makeText(this, getResources().getString(R.string.exception_category_name_exists), Toast.LENGTH_SHORT).show();
        } else if (e.cause instanceof DateFieldEmptyException) {
            Toast.makeText(this, getResources().getString(R.string.exception_date_field_empty), Toast.LENGTH_SHORT).show();
        } else if (e.cause instanceof DateFromFutureException) {
            Toast.makeText(this, getResources().getString(R.string.exception_date_fom_future), Toast.LENGTH_SHORT).show();
        } else if (e.cause instanceof TitleTooShortException) {
            Toast.makeText(this, getResources().getString(R.string.exception_title_too_short), Toast.LENGTH_SHORT).show();
        } else if (e.cause instanceof CategoryNameEmptyException) {
            Toast.makeText(this, getResources().getString(R.string.exception_category_name_empty), Toast.LENGTH_SHORT).show();
        } else if (e.cause instanceof ValueEmptyException) {
            Toast.makeText(this, getResources().getString(R.string.exception_value_empty), Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, getResources().getString(R.string.exception_other), Toast.LENGTH_SHORT).show();
        }
    }
}