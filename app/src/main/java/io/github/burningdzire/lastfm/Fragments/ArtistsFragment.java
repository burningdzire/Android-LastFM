package io.github.burningdzire.lastfm.Fragments;

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

import java.util.List;

import io.github.burningdzire.lastfm.R;
import io.github.burningdzire.lastfm.activities.DetailActivity;
import io.github.burningdzire.lastfm.adapter.AlbumsAdapter;
import io.github.burningdzire.lastfm.adapter.ArtistsAdapter;
import io.github.burningdzire.lastfm.model.ModelArtists.ModelArtist_Artist;
import io.github.burningdzire.lastfm.model.ModelArtists.ModelArtist_Root;
import io.github.burningdzire.lastfm.rest.NetworkClient;
import io.github.burningdzire.lastfm.rest.NetworkInterface;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ArtistsFragment extends Fragment implements ArtistsAdapter.ArtistsClickListener {

    private static final String LOG_TAG = ArtistsFragment.class.getSimpleName();
    private final String API_KEY = "YOUR_API_KEY";
    ArtistsAdapter mAdapter;
    RecyclerView recyclerView;
    private String mTag = DetailActivity.getTag();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.list_detail, container, false);

        NetworkInterface networkInterface = NetworkClient.getClient().create(NetworkInterface.class);
        recyclerView = (RecyclerView) rootView.findViewById(R.id.rv_detail);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2));

        Call<ModelArtist_Root> call = networkInterface.getTopArtists("tag.getTopArtists", mTag, API_KEY,"json");
        call.enqueue(new Callback<ModelArtist_Root>() {
            @Override
            public void onResponse(Call<ModelArtist_Root> call, Response<ModelArtist_Root> response) {
                List<ModelArtist_Artist> mArtist = response.body().getTopartists().getArtist();
                mAdapter = new ArtistsAdapter(mArtist, R.layout.list_item_detail, getContext(), ArtistsFragment.this);
                recyclerView.setAdapter(mAdapter);
            }

            @Override
            public void onFailure(Call<ModelArtist_Root> call, Throwable t) {
                Log.e(LOG_TAG, t.toString());
            }
        });
        return rootView;
    }

    @Override
    public void onListItemClick(String track) {

    }
}
