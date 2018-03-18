package rmutsb.mook.chatchon.makingfavorcoffee.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import rmutsb.mook.chatchon.makingfavorcoffee.R;

/**
 * Created by masterung on 4/1/2018 AD.
 */

public class HomeScoreFragment extends Fragment{




    public static HomeScoreFragment homeScoreInstance(String[] loginStrings) {

        HomeScoreFragment homeScoreFragment = new HomeScoreFragment();
        Bundle bundle = new Bundle();
        bundle.putStringArray("Login", loginStrings);
        homeScoreFragment.setArguments(bundle);
        return homeScoreFragment;

    }



    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home_score, container, false);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);




    }// method



}   // Main Class
