package com.twitt4droid.app.fragment;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.twitt4droid.app.R;
import com.twitt4droid.fragment.FixedQueryTimelineFragment;
import com.twitt4droid.widget.TweetDialog;

public class CustomFixedQueryTimelineFragment extends FixedQueryTimelineFragment {

    public static CustomFixedQueryTimelineFragment newInstance(String query) {
        CustomFixedQueryTimelineFragment fragment = new CustomFixedQueryTimelineFragment();
        Bundle args = new Bundle();
        args.putString(QUERY_ARG, query);
        args.putBoolean(ENABLE_DARK_THEME_ARG, false);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
        setHasOptionsMenu(true);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.fragment_timelines, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.new_tweet_item:
                new TweetDialog(getActivity()).show();
                return true;
            default: return super.onOptionsItemSelected(item);
        }
    }
}