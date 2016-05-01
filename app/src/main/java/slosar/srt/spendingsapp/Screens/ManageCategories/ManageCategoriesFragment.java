package slosar.srt.spendingsapp.Screens.ManageCategories;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import org.greenrobot.eventbus.EventBus;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import slosar.srt.spendingsapp.Dialogs.Categories.AddCategoryDialog;
import slosar.srt.spendingsapp.Dialogs.Categories.CategoryDialogListener;
import slosar.srt.spendingsapp.Exceptions.CategoryNameExistsException;
import slosar.srt.spendingsapp.Exceptions.ExceptionEvent;
import slosar.srt.spendingsapp.R;


public class ManageCategoriesFragment extends Fragment implements CategoryDialogListener {

    @Bind(R.id.recycler_view)
    RecyclerView mRecyclerView;

    @Bind(R.id.bt_add_category)
    Button mBtAddCategory;

    private ManageCategoriesPresenter mPresenter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_manage_categories, container, false);

        ButterKnife.bind(this, rootView);
        mPresenter = new ManageCategoriesPresenter(getActivity());

        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.setAdapter(mPresenter.getDataAdapter());

        return rootView;
    }

    @OnClick(R.id.bt_add_category)
    public void onAddCategory() {
        new AddCategoryDialog(getActivity(), this).show();
    }

    @Override
    public void addCategory(String categoryName) {
        try {
            mPresenter.addCategory(categoryName);
            fragmentRedraw();
        } catch (CategoryNameExistsException e) {
            e.printStackTrace();
            EventBus.getDefault().postSticky(new ExceptionEvent(e));
        }
        Toast.makeText(getActivity(), getResources().getString(R.string.category_added), Toast.LENGTH_SHORT).show();
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
