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
import io.github.burningdzire.lastfm.model.ModelArtists.ModelArtist_Artist;

public class ArtistsAdapter  extends RecyclerView.Adapter<ArtistsAdapter.ArtistsViewHolder>{

    private List<ModelArtist_Artist> mArtists;
    private int rowLayout;
    private Context context;

//    onClickListener Implemented here
    final private ArtistsClickListener mOnClickListener;
    public interface ArtistsClickListener{
        void onListItemClick(String track);
    }

//    Constructor Implemented here
    public ArtistsAdapter(List<ModelArtist_Artist> mArtists, int rowLayout, Context context, ArtistsClickListener mOnClickListener) {
        this.mArtists = mArtists;
        this.rowLayout = rowLayout;
        this.context = context;
        this.mOnClickListener = mOnClickListener;
    }

    @NonNull
    @Override
    public ArtistsAdapter.ArtistsViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(rowLayout, viewGroup, false);
        return new ArtistsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ArtistsAdapter.ArtistsViewHolder artistsViewHolder, int position) {
        String imageUrl = mArtists.get(position).getImage().get(1).getText();
        Glide.with(context).load(imageUrl).into(artistsViewHolder.imageView);

        String artistName = mArtists.get(position).getName();
        artistName = artistName.substring(0,1).toUpperCase() + artistName.substring(1);
        artistsViewHolder.textView.setText(artistName);
    }

    @Override
    public int getItemCount() {
        return mArtists.size();
    }

    public class ArtistsViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        LinearLayout linearLayout;
        ImageView imageView;
        TextView textView;

        public ArtistsViewHolder(@NonNull View itemView) {
            super(itemView);
            linearLayout = (LinearLayout) itemView.findViewById(R.id.detail_layout);
            imageView = (ImageView) itemView.findViewById(R.id.iv_detail_image);
            textView = (TextView) itemView.findViewById(R.id.tv_detail_name);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            int clickedPosition = getAdapterPosition();
            String artist = mArtists.get(clickedPosition).getName();
            mOnClickListener.onListItemClick(artist);
        }
    }
}
