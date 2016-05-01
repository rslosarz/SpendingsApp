package slosar.srt.spendingsapp.Dialogs;

import android.content.Context;
import android.widget.EditText;

import slosar.srt.spendingsapp.R;

/**
 * Created by Rafal on 2016-04-24.
 */
public class AddCategoryDialog extends CustomDialog {

    private EditText mName;
    private CategoryDialogListener mListener;

    public AddCategoryDialog(Context context, CategoryDialogListener listener) {
        super(context, R.layout.dialog_add_category);
        mListener = listener;
        mName = (EditText) findViewById(R.id.et_name);
    }

    @Override
    protected void okAction() {
        mListener.addCategory(mName.getText().toString());
        dismiss();
    }
}
