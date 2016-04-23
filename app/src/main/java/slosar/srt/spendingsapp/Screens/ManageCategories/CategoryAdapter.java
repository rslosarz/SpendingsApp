package slosar.srt.spendingsapp.Screens.ManageCategories;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import slosar.srt.spendingsapp.DbModule.Category;
import slosar.srt.spendingsapp.R;

/**
 * Created by Rafal on 2016-04-24.
 */
public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder> {

    private List<Category> categoriesList;
    private CategoryItemClickListener mListener;

    public CategoryAdapter(List<Category> categories, CategoryItemClickListener listener) {
        this.categoriesList = categories;
        this.mListener = listener;
    }


    @Override
    public CategoryAdapter.CategoryViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_row_category, parent, false);

        return new CategoryViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(CategoryAdapter.CategoryViewHolder holder, int position) {
        Category category = categoriesList.get(position);
        holder.bind(category, mListener);
    }

    @Override
    public int getItemCount() {
        return categoriesList.size();
    }


    public class CategoryViewHolder extends RecyclerView.ViewHolder {
        public TextView name;

        public CategoryViewHolder(View view) {
            super(view);
            name = (TextView) view.findViewById(R.id.tv_category_name);
        }

        public void bind(final Category item, final CategoryItemClickListener listener) {
            name.setText(item.getName());
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onItemClick(item);
                }
            });
        }
    }
}
