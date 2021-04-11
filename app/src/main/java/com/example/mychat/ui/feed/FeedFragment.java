package com.example.mychat.ui.feed;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mychat.R;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class FeedFragment extends Fragment implements FeedRecyclerViewAdapter.ItemClickListener{
    private static final String TAG = "FeedFragment";
    private ArrayList<FeedViewModel> list_feed = new ArrayList<>();
    private DividerItemDecoration mDividerItemDecoration;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_feed, container, false);
        initImageBitmaps_feed();
        initRecyclerView_feed(root);
        return root;
    }

    private void initRecyclerView_feed(@NotNull View view) {

        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.recyclerview_feed);
        recyclerView.setHasFixedSize(true);
        FeedRecyclerViewAdapter mAdapter_feed = new FeedRecyclerViewAdapter(list_feed, this, this);
        recyclerView.setAdapter(mAdapter_feed);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        mDividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(), layoutManager.getOrientation());
        recyclerView.addItemDecoration(mDividerItemDecoration);
    }

    private void initImageBitmaps_feed() {
        Log.d(TAG, "initImageBitMaps: preparing bitmaps.");


        list_feed.add(new FeedViewModel("Krishna", "https://homepages.cae.wisc.edu/~ece533/images/girl.png","https://homepages.cae.wisc.edu/~ece533/images/airplane.png"));
        list_feed.add(new FeedViewModel("Krish", "https://homepages.cae.wisc.edu/~ece533/images/arctichare.png", "https://homepages.cae.wisc.edu/~ece533/images/tulips.png"));
        list_feed.add(new FeedViewModel("vamsee", "https://homepages.cae.wisc.edu/~ece533/images/boat.png", "https://homepages.cae.wisc.edu/~ece533/images/frymire.png"));

    }

    @Override
    public void onItemClick(FeedViewModel feedViewModel) {

    }
}