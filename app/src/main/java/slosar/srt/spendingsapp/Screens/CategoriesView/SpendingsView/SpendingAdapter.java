package slosar.srt.spendingsapp.Screens.CategoriesView.SpendingsView;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import slosar.srt.spendingsapp.Commons.Commons;
import slosar.srt.spendingsapp.DbModule.Spending;
import slosar.srt.spendingsapp.R;

/**
 * Created by Rafal on 2016-04-24.
 */
public class SpendingAdapter extends RecyclerView.Adapter<SpendingAdapter.SpendingViewHolder> {

    private List<Spending> spendingsList;
    private SpendingItemClickListener mListener;

    public SpendingAdapter(List<Spending> spendings, SpendingItemClickListener listener) {
        this.spendingsList = spendings;
        this.mListener = listener;
    }


    @Override
    public SpendingAdapter.SpendingViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_row_spending, parent, false);

        return new SpendingViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(SpendingAdapter.SpendingViewHolder holder, int position) {
        Spending spending = spendingsList.get(position);
        holder.bind(spending, mListener);
    }

    @Override
    public int getItemCount() {
        return spendingsList.size();
    }


    public class SpendingViewHolder extends RecyclerView.ViewHolder {
        public TextView mCategory;
        public TextView mTitle;
        public TextView mValue;
        public TextView mDate;

        public SpendingViewHolder(View view) {
            super(view);
            mCategory = (TextView) view.findViewById(R.id.tv_category_name);
            mTitle = (TextView) view.findViewById(R.id.tv_title);
            mValue = (TextView) view.findViewById(R.id.tv_value);
            mDate = (TextView) view.findViewById(R.id.tv_date);
        }

        public void bind(final Spending item, final SpendingItemClickListener listener) {
            mCategory.setText(item.getCategory().getName());
            mTitle.setText(item.getTitle());
            mValue.setText(String.valueOf(item.getValue()));
            mDate.setText(Commons.dateFormat.format(item.getDate()));

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onItemClick(item);
                }
            });
        }
    }
}
