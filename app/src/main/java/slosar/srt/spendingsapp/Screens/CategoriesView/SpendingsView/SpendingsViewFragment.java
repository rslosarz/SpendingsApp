package slosar.srt.spendingsapp.Screens.CategoriesView.SpendingsView;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.greenrobot.eventbus.EventBus;

import butterknife.Bind;
import butterknife.ButterKnife;
import slosar.srt.spendingsapp.DbModule.Category;
import slosar.srt.spendingsapp.DbModule.Spending;
import slosar.srt.spendingsapp.R;

public class SpendingsViewFragment extends Fragment implements ISpendingsView, SpendingItemClickListener {

    @Bind(R.id.recycler_view)
    RecyclerView mRecyclerView;

    private Category viewedCategory;
    private SpendingsViewPresenter mPresenter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_spendings_view, container, false);

        ButterKnife.bind(this, rootView);

        viewedCategory = EventBus.getDefault().removeStickyEvent(Category.class);

        mPresenter = new SpendingsViewPresenter(this, this, getActivity(), viewedCategory);

        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.setAdapter(mPresenter.getDataAdapter());

        return rootView;
    }

    @Override
    public void onItemClick(Spending item) {

    }
}
