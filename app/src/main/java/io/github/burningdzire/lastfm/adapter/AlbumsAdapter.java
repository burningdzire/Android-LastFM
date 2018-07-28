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
import io.github.burningdzire.lastfm.model.ModelAlbums.*;

public class AlbumsAdapter extends RecyclerView.Adapter<AlbumsAdapter.AlbumsViewHolder>{

    private List<ModelAlbum_Album> mAlbums;
    private int rowLayout;
    private Context context;

//    OnClickListener Implemented here
    final private AlbumsClickListener mOnClickListener;
    public interface AlbumsClickListener {
        void onListItemClick(String album);
    }

//    Constructor Implemented here
    public AlbumsAdapter(List<ModelAlbum_Album> mAlbums, int rowLayout, Context context, AlbumsClickListener listener) {
        this.mAlbums = mAlbums;
        this.rowLayout = rowLayout;
        this.context = context;
        mOnClickListener =listener;
    }

    @NonNull
    @Override
    public AlbumsViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(rowLayout, viewGroup, false);
        return new AlbumsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AlbumsViewHolder albumsViewHolder, int position) {
        String imageUrl = mAlbums.get(position).getImage().get(1).getText();
        Glide.with(context).load(imageUrl).into(albumsViewHolder.imageView);
        String albumName = mAlbums.get(position).getName();
        albumName = albumName.substring(0,1).toUpperCase() + albumName.substring(1);
        albumsViewHolder.textView.setText(albumName);
    }

    @Override
    public int getItemCount() {
        return mAlbums.size();
    }

    public class AlbumsViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        LinearLayout linearLayout;
        ImageView imageView;
        TextView textView;

        public AlbumsViewHolder(@NonNull View itemView) {
            super(itemView);
            linearLayout = (LinearLayout) itemView.findViewById(R.id.detail_layout);
            imageView = (ImageView) itemView.findViewById(R.id.iv_detail_image);
            textView = (TextView) itemView.findViewById(R.id.tv_detail_name);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            int clickedPosition = getAdapterPosition();
            String album = mAlbums.get(clickedPosition).getName();
            mOnClickListener.onListItemClick(album);
        }
    }
}
