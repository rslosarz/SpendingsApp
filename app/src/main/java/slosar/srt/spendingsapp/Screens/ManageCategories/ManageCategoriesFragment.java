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

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import slosar.srt.spendingsapp.DataProviders.DbProvider;
import slosar.srt.spendingsapp.DataProviders.IDbProvider;
import slosar.srt.spendingsapp.DbModule.Category;
import slosar.srt.spendingsapp.R;


public class ManageCategoriesFragment extends Fragment implements IManageCategoriesView, CategoryItemClickListener {

    @Bind(R.id.recycler_view)
    RecyclerView mRecyclerView;

    @Bind(R.id.bt_add_category)
    Button mBtAddCategory;

    private CategoryAdapter mAdapter;
    private IDbProvider mDbProvider;
    private ManageCategoriesPresenter mPresenter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_manage_categories, container, false);

        ButterKnife.bind(this, rootView);
        mPresenter = new ManageCategoriesPresenter(this);

        mDbProvider = new DbProvider(getActivity());
        List<Category> categories = mDbProvider.getCategoryList();
        mAdapter = new CategoryAdapter(categories, this);

        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.setAdapter(mAdapter);

        mAdapter.notifyDataSetChanged();

        return rootView;
    }

    @OnClick(R.id.bt_add_category)
    private void onAddCategory() {
        mPresenter.addCategory();
    }

    @Override
    public void onItemClick(Category item) {
        mPresenter.editCategory();
    }
}
