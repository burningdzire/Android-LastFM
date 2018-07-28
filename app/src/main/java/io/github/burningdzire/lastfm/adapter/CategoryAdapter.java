package io.github.burningdzire.lastfm.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import io.github.burningdzire.lastfm.Fragments.AlbumsFragment;
import io.github.burningdzire.lastfm.Fragments.ArtistsFragment;
import io.github.burningdzire.lastfm.Fragments.TracksFragment;
import io.github.burningdzire.lastfm.R;

public class CategoryAdapter extends FragmentPagerAdapter {

    private Context mContext;

    public CategoryAdapter(Context context, FragmentManager fm) {
        super(fm);
        mContext = context;
    }

    @Override
    public Fragment getItem(int position) {
        if (position == 0) {
            return new TracksFragment();
        } else if (position == 1) {
            return new AlbumsFragment();
        } else {
            return new ArtistsFragment();
        }
    }

    @Override
    public int getCount() {
        return 3;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        if (position == 0) {
            return mContext.getString(R.string.fragment_tracks);
        } else if (position == 1)
            return mContext.getString(R.string.fragment_albums);
        else
            return mContext.getString(R.string.fragment_artists);
    }
}
