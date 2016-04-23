package slosar.srt.spendingsapp.Screens.CategoriesView;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.List;

import slosar.srt.spendingsapp.DataProviders.DbProvider;
import slosar.srt.spendingsapp.DataProviders.IDbProvider;
import slosar.srt.spendingsapp.DbModule.Category;
import slosar.srt.spendingsapp.R;
import slosar.srt.spendingsapp.Screens.ManageCategories.ManageCategoriesFragment;
import slosar.srt.spendingsapp.Screens.StatsView.StatsViewFragment;

/**
 * Created by Rafal on 2016-04-23.
 */
public class CategoriesViewFragment extends Fragment {

    private List<Category> categoriesList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_categories_view, container, false);

        IDbProvider dbProvider = new DbProvider(getActivity());
        categoriesList = dbProvider.getCategoryList();
        //String[] values = (String[]) categoriesList.toArray();

        String[] val2 = new String[]{
                "Section 1",
                "Section 2",
                "Section 3",
                "Section 1",
                "Section 2",
                "Section 3",
        };

        Spinner spinner = (Spinner) rootView.findViewById(R.id.spinner);
        MySpinnerAdapter adapter = new MySpinnerAdapter(getActivity(), val2);
        spinner.setAdapter(adapter);

        return rootView;
    }

    private void replaceFragment(int position) {

        FragmentManager fm = getActivity().getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();

        ft.replace(R.id.main_activity_fragment, new CategoriesViewFragment());
        ft.commit();
    }

    private static class MySpinnerAdapter extends ArrayAdapter<String> {

        public MySpinnerAdapter(Context context, String[] objects) {
            super(context, android.R.layout.simple_list_item_1, android.R.id.text1, objects);
        }

        @Override
        public View getDropDownView(int position, View convertView, ViewGroup parent) {
            View view = super.getView(position, convertView, parent);
            TextView text = (TextView) view.findViewById(android.R.id.text1);
            // Hack. Use BuildVersion 23 for a better approach.
            text.setTextColor(Color.BLACK);
            text.setBackgroundColor(Color.WHITE);
            return view;
        }
    }

    /*
        @Override
        public void onAttach(Activity activity) {
            super.onAttach(activity);

            if(behavior != null)
                return;

            FrameLayout layout =(FrameLayout) getActivity().findViewById(R.id.main_activity_fragment);
            CoordinatorLayout.LayoutParams params = (CoordinatorLayout.LayoutParams) layout.getLayoutParams();

            behavior = params.getBehavior();
            params.setBehavior(null);

        }

        @Override
        public void onDetach() {
            super.onDetach();
            if(behavior == null)
                return;

            FrameLayout layout =(FrameLayout) getActivity().findViewById(R.id.main_activity_fragment);
            CoordinatorLayout.LayoutParams params = (CoordinatorLayout.LayoutParams) layout.getLayoutParams();

            params.setBehavior(behavior);

            layout.setLayoutParams(params);

            behavior = null;
        }
    */
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
