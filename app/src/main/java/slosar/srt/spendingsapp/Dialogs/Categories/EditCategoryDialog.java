package slosar.srt.spendingsapp.Dialogs.Categories;

import android.content.Context;
import android.widget.EditText;
import android.widget.TextView;

import slosar.srt.spendingsapp.DbModule.Category;
import slosar.srt.spendingsapp.Dialogs.CustomDialog;
import slosar.srt.spendingsapp.R;

/**
 * Created by Rafal on 2016-04-24.
 */
public class EditCategoryDialog extends CustomDialog {

    private String oldName;
    private EditText mName;
    private CategoryDialogListener mListener;

    public EditCategoryDialog(Context context, CategoryDialogListener listener, Category item) {
        super(context, R.layout.dialog_category);
        TextView info = (TextView) findViewById(R.id.info);
        info.setText(context.getResources().getString(R.string.edit_category));

        mListener = listener;
        mName = (EditText) findViewById(R.id.et_name);
        oldName = item.getName();
        mName.setText(oldName);
    }

    @Override
    protected void okAction() {
        mListener.editCategory(oldName, mName.getText().toString());
    }
}
