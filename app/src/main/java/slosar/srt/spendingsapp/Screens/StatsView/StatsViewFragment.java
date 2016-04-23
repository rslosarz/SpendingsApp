package slosar.srt.spendingsapp.Screens.StatsView;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import slosar.srt.spendingsapp.R;

public class StatsViewFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_stats_view, container, false);
        return rootView;
    }
}
