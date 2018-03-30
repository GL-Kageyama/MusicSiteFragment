package com.swift.android.musicsitefragment;

import android.app.Fragment;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class Fragment4Activity extends Fragment {

    private Fragment4Activity.onFragmentInteractionListener mListener;

    //空のコンストラクタ
    public Fragment4Activity(){

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState){
        return inflater.inflate(R.layout.activity_fragment4, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState){
        super.onViewCreated(view, savedInstanceState);
        view.findViewById(R.id.frag_question4).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mListener != null){
                    mListener.onFragmentInteraction();
                }
            }
        });
    }

    @Override
    public void onAttach(Context context){
        super.onAttach(context);
        if(context instanceof Fragment4Activity.onFragmentInteractionListener){
            mListener = (Fragment4Activity.onFragmentInteractionListener)context;
        }else{
            throw  new RuntimeException(context.toString()
                    + "OnFragmentInteractionListenerを実装してください" );
        }
    }

    @Override
    public void onDetach(){
        super.onDetach();
        mListener = null;
    }

    /**
     * アクテビティとの連携のためのインターフェース
     */
    public interface onFragmentInteractionListener{
        void onFragmentInteraction();
    }
}
