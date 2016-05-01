package slosar.srt.spendingsapp.Screens.CategoriesView;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.Spinner;

import org.greenrobot.eventbus.EventBus;

import java.util.Date;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import slosar.srt.spendingsapp.DbModule.Category;
import slosar.srt.spendingsapp.Dialogs.Spendings.AddSpendingDialog;
import slosar.srt.spendingsapp.Dialogs.Spendings.AddSpendingDialogListener;
import slosar.srt.spendingsapp.R;
import slosar.srt.spendingsapp.Screens.CategoriesView.SpendingsView.SpendingsViewFragment;

/**
 * Created by Rafal on 2016-04-23.
 */
public class CategoriesViewFragment extends Fragment implements ICategoriesView, AddSpendingDialogListener {

    @Bind(R.id.bt_add_spending)
    Button mBtAddSpending;
    @Bind(R.id.spinner)
    Spinner mSpinner;
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

        ButterKnife.bind(this, rootView);

        presenter = new CategoriesViewPresenter(this, getActivity());

        mSpinner.setAdapter(presenter.getCategoriesAdapter());
        mSpinner.setOnItemSelectedListener(spinnerOnItemSelectedListener);

        return rootView;
    }

    private void replaceFragment(int position) {
        Category category = presenter.getSelectedCategoryObject(position);
        FragmentManager fm = getActivity().getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();

        EventBus.getDefault().postSticky(category);
        ft.replace(R.id.spendings_view_fragment, new SpendingsViewFragment());
        ft.commit();
    }

    @OnClick(R.id.bt_add_spending)
    public void onAddSpendingClick() {
        new AddSpendingDialog(getActivity(), this).show();
    }

    @Override
    public void addSpending(float value, String title, Date date) {
        int categoryIndex = mSpinner.getSelectedItemPosition();
        presenter.addSpending(value, title, date, categoryIndex);
        fragmentRedraw();
    }

    private void fragmentRedraw() {
        //remember that this destroys fragment back stack!
        getActivity().getSupportFragmentManager()
                .beginTransaction()
                .detach(this)
                .attach(this)
                .commit();
    }
}
