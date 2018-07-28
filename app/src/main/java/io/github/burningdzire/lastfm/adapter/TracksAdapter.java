package io.github.burningdzire.lastfm.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import io.github.burningdzire.lastfm.R;
import io.github.burningdzire.lastfm.model.ModelTracks.ModelTrack_Track;

public class TracksAdapter extends RecyclerView.Adapter<TracksAdapter.TracksViewHolder> {

    private List<ModelTrack_Track> mTracks;
    private int rowLayout;
    private Context context;

//    onClickListener Implemented here
    final private TracksClickListener mOnClickListener;
    public interface TracksClickListener{
        void onListItemClick(String track);
    }

    public TracksAdapter(List<ModelTrack_Track> tracks, int rowLayout, Context context, TracksClickListener listener) {
        this.mTracks = tracks;
        this.rowLayout = rowLayout;
        this.context = context;
        mOnClickListener = listener;
    }

    @NonNull
    @Override
    public TracksViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(rowLayout, viewGroup, false);
        return new TracksViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TracksViewHolder tracksViewHolder, int position) {
        String imageUrl = mTracks.get(position).getImage().get(1).getText();
        Glide.with(context).load(imageUrl).into(tracksViewHolder.imageView);

        String trackName = mTracks.get(position).getName();
        trackName = trackName.substring(0,1).toUpperCase() + trackName.substring(1);
        tracksViewHolder.textView.setText(trackName);
    }

    @Override
    public int getItemCount() {
        return mTracks.size();
    }

    public class TracksViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        LinearLayout linearLayout;
        ImageView imageView;
        TextView textView;
        public TracksViewHolder(@NonNull View itemView) {
            super(itemView);
            linearLayout = (LinearLayout) itemView.findViewById(R.id.detail_layout);
            imageView = (ImageView) itemView.findViewById(R.id.iv_detail_image);
            textView = (TextView) itemView.findViewById(R.id.tv_detail_name);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            int clickedPosition = getAdapterPosition();
            String track = mTracks.get(clickedPosition).getName();
            mOnClickListener.onListItemClick(track);
        }
    }
}
