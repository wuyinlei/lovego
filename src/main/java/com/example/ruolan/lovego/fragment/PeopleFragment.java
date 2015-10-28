package com.example.ruolan.lovego.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.ruolan.lovego.R;
import com.example.ruolan.lovego.activity.LoginActivity;

public class PeopleFragment extends Fragment implements View.OnClickListener {

    private View mView;
    public PeopleFragment(){

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (mView == null){
            mView = inflater.inflate(R.layout.fragment_people,container,false);
            mView.findViewById(R.id.login_layout).setOnClickListener(this);
        } else {
            ViewGroup parent = (ViewGroup) mView.getParent();
           // parent.removeView(mView);
        }
        return mView;
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.login_layout:
                startActivity(new Intent(getActivity(), LoginActivity.class));
                break;
        }
    }
}
