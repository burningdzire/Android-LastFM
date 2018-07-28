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
import io.github.burningdzire.lastfm.adapter.TracksAdapter;
import io.github.burningdzire.lastfm.model.ModelTracks.ModelTrack_Artist;
import io.github.burningdzire.lastfm.model.ModelTracks.ModelTrack_Root;
import io.github.burningdzire.lastfm.model.ModelTracks.ModelTrack_Track;
import io.github.burningdzire.lastfm.rest.NetworkClient;
import io.github.burningdzire.lastfm.rest.NetworkInterface;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TracksFragment extends Fragment implements TracksAdapter.TracksClickListener{

    private static final String LOG_TAG = TracksFragment.class.getSimpleName();
    private final String API_KEY = "YOUR_API_KEY";
    TracksAdapter mAdapter;
    RecyclerView recyclerView;
    private String mTag = DetailActivity.getTag();


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.list_detail, container, false);

        NetworkInterface networkInterface = NetworkClient.getClient().create(NetworkInterface.class);
        recyclerView = (RecyclerView) rootView.findViewById(R.id.rv_detail);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2));

        Call<ModelTrack_Root> call = networkInterface.getTopTracks("tag.getTopTracks", mTag, API_KEY,"json");
        call.enqueue(new Callback<ModelTrack_Root>() {
            @Override
            public void onResponse(Call<ModelTrack_Root> call, Response<ModelTrack_Root> response) {
                List<ModelTrack_Track> mTracks = response.body().getTracks().getTrack();
                mAdapter  = new TracksAdapter(mTracks, R.layout.list_item_detail, getContext(), TracksFragment.this);
                recyclerView.setAdapter(mAdapter);
            }

            @Override
            public void onFailure(Call<ModelTrack_Root> call, Throwable t) {
                Log.e(LOG_TAG, t.toString());
            }
        });
        return rootView;
    }

    @Override
    public void onListItemClick(String track) {

    }
}
