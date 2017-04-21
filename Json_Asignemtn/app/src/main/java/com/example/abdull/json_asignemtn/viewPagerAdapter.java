package com.example.abdull.json_asignemtn;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

/**
 * Created by abdull on 4/14/17.
 */

public class viewPagerAdapter extends FragmentStatePagerAdapter {
    public viewPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new EarthQuick();
            case 1:
                return new Population();
        }
        return null;
    }

    @Override
    public int getCount() {
        return 2;
    }
}
