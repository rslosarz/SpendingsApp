package slosar.srt.spendingsapp.Screens.CategoriesView;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Spinner;

import org.greenrobot.eventbus.EventBus;

import slosar.srt.spendingsapp.DbModule.Category;
import slosar.srt.spendingsapp.R;
import slosar.srt.spendingsapp.Screens.CategoriesView.SpendingsView.SpendingsViewFragment;
import slosar.srt.spendingsapp.Screens.ManageCategories.ManageCategoriesFragment;
import slosar.srt.spendingsapp.Screens.StatsView.StatsViewFragment;

/**
 * Created by Rafal on 2016-04-23.
 */
public class CategoriesViewFragment extends Fragment implements ICategoriesView {

    private CategoriesViewPresenter presenter;

    private AdapterView.OnItemSelectedListener spinnerOnItemSelectedListener = new AdapterView.OnItemSelectedListener() {
        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            replaceFragment(position);
        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {

        }
    };

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_categories_view, container, false);

        presenter = new CategoriesViewPresenter(this, getActivity());

        Spinner spinner = (Spinner) rootView.findViewById(R.id.spinner);
        spinner.setAdapter(presenter.getCategoriesAdapter());
        spinner.setOnItemSelectedListener(spinnerOnItemSelectedListener);

        return rootView;
    }

    private void replaceFragment(int position) {
        Category category = presenter.getSelectedCategoryObject(position);
        FragmentManager fm = getActivity().getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();

        EventBus.getDefault().postSticky(category);
        ft.replace(R.id.container, new SpendingsViewFragment());
        ft.commit();
    }

    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            if (position == 0) {
                StatsViewFragment fragment = new StatsViewFragment();
                return fragment;
            } else {
                ManageCategoriesFragment fragment = new ManageCategoriesFragment();
                return fragment;
            }
        }

        @Override
        public int getCount() {
            return 8;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return "tab1";
                case 1:
                    return "tab2";
                case 2:
                    return "tab2";
                case 3:
                    return "tab2";
                case 4:
                    return "tab2";
                case 5:
                    return "tab2";
                case 6:
                    return "tab2";
                case 7:
                    return "tab2";
            }
            return null;
        }
    }
}
