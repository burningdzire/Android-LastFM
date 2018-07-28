package io.github.burningdzire.lastfm.Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.List;

import io.github.burningdzire.lastfm.InfoActivity;
import io.github.burningdzire.lastfm.MainActivity;
import io.github.burningdzire.lastfm.R;
import io.github.burningdzire.lastfm.activities.DetailActivity;
import io.github.burningdzire.lastfm.adapter.AlbumsAdapter;
import io.github.burningdzire.lastfm.model.ModelAlbums.ModelAlbum_Album;
import io.github.burningdzire.lastfm.model.ModelAlbums.ModelAlbums_Root;
import io.github.burningdzire.lastfm.rest.NetworkClient;
import io.github.burningdzire.lastfm.rest.NetworkInterface;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AlbumsFragment extends Fragment implements AlbumsAdapter.AlbumsClickListener {
    
    private static final String LOG_TAG = AlbumsFragment.class.getSimpleName();
    private final String API_KEY = "YOUR_API_KEY";
    AlbumsAdapter mAdapter;
    RecyclerView recyclerView;
    private String mTag = DetailActivity.getTag();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.list_detail, container, false);

        NetworkInterface networkInterface = NetworkClient.getClient().create(NetworkInterface.class);
        recyclerView = (RecyclerView) rootView.findViewById(R.id.rv_detail);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2));
        Call<ModelAlbums_Root>call = networkInterface.getTopAlbums("tag.getTopAlbums", mTag, API_KEY,"json");
        call.enqueue(new Callback<ModelAlbums_Root>() {
            @Override
            public void onResponse(Call<ModelAlbums_Root> call, Response<ModelAlbums_Root> response) {
                List<io.github.burningdzire.lastfm.model.ModelAlbums.ModelAlbum_Album> mAlbums
                        = response.body().getAlbums().getAlbum();
                mAdapter = new AlbumsAdapter(mAlbums, R.layout.list_item_detail, getContext(), AlbumsFragment.this);
                recyclerView.setAdapter(mAdapter);
            }

            @Override
            public void onFailure(Call<ModelAlbums_Root> call, Throwable t) {
                Log.e(LOG_TAG, t.toString());
            }
        });
        return rootView;
    }

    @Override
    public void onListItemClick(String album) {
    }
}
