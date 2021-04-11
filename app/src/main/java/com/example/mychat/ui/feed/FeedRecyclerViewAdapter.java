package com.example.mychat.ui.feed;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.mychat.R;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class FeedRecyclerViewAdapter extends RecyclerView.Adapter<FeedRecyclerViewAdapter.ViewHolder> {

    private List<FeedViewModel> listFeed;
    private ItemClickListener itemClickListener;
    private FeedFragment mContext;

    public FeedRecyclerViewAdapter(List<FeedViewModel> listFeed, ItemClickListener itemClickListener, FeedFragment mContext) {
        this.listFeed = listFeed;
        this.itemClickListener = itemClickListener;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public FeedRecyclerViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_recycle_feed, parent, false);
        ViewHolder holder_feed = new ViewHolder(view);
        return holder_feed;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder_feed, int position) {
        Glide.with(mContext).asBitmap().load(listFeed.get(position).getProfile_feed()).into(holder_feed.image_feed);
        Glide.with(mContext).asBitmap().load(listFeed.get(position).getPost_feed()).into(holder_feed.post_feed);
        holder_feed.name_feed.setText(listFeed.get(position).getName_feed());
        holder_feed.name_feed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mContext.getContext(), listFeed.get(position).getName_feed(), Toast.LENGTH_SHORT).show();
                itemClickListener.onItemClick(listFeed.get(position));
            }
        });
    }

    @Override
    public int getItemCount() {
        return listFeed.size();
    }

    public interface ItemClickListener {

        void onItemClick(FeedViewModel feedViewModel);
    }


    public class ViewHolder extends RecyclerView.ViewHolder{
        CircleImageView image_feed;
        TextView name_feed;
        ImageView post_feed;
        TextView Likes_feed;
        RelativeLayout parentLayout_feed;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            image_feed = (itemView).findViewById(R.id.feed_profile);
            name_feed = (itemView).findViewById(R.id.feed_name);
            post_feed = (itemView).findViewById(R.id.feed_post);
            parentLayout_feed = (itemView).findViewById(R.id.Layout_feed);
        }
    }
}
