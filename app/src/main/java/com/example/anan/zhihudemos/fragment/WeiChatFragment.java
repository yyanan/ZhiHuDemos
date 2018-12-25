package com.example.anan.zhihudemos.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.anan.zhihudemos.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class WeiChatFragment extends Fragment {


    public WeiChatFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_wei_chat, container, false);
    }

}
