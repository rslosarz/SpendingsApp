package slosar.srt.spendingsapp.Commons;

import android.text.InputFilter;
import android.text.Spanned;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Rafal on 2016-05-01.
 */
public class DecimalDigitsFilter implements InputFilter {

    Pattern mPattern;

    public DecimalDigitsFilter() {
        mPattern = Pattern.compile("[0-9]{0," + (7) + "}+((\\.[0-9]{0," + 1 + "})?)||(\\.)?");
    }

    @Override
    public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend) {

        Matcher matcher = mPattern.matcher(dest);
        if (!matcher.matches())
            return "";
        return null;
    }

}
