package com.myproject.ziffytech.fragments;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.myproject.ziffytech.R;
import com.myproject.ziffytech.adapters.HomeAdapter;
import com.myproject.ziffytech.models.City;
import com.myproject.ziffytech.models.HomeModel;
import com.myproject.ziffytech.models.List;
import com.myproject.ziffytech.utils.ApiHelper;
import com.myproject.ziffytech.utils.VolleyManager;

import java.util.ArrayList;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


public class HomeFragment extends Fragment {

    private Context mContext;
    private TextView mTvTitle;
    private RecyclerView mRecycleView;

    public static String POSITION = "POSITION";
    public static String CITY_NAME = "CITY_NAME";
    public static String ITEM = "ITEM";
    private ProgressDialog progressDialog;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.home_fragment, container, false);
        init(rootView);
        return rootView;
    }

    private void init(View rootView) {
        mContext = getActivity();

        mTvTitle = rootView.findViewById(R.id.home_title);
        mRecycleView = rootView.findViewById(R.id.home_recyclerView);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(
                mContext, LinearLayoutManager.VERTICAL, false);
        mRecycleView.setLayoutManager(linearLayoutManager);


        loadData();

    }

    private void loadData() {
        progressDialog = ProgressDialog.show(mContext, "Loading", "Please Wait");
        VolleyManager volleyManager = new VolleyManager(mContext, ApiHelper.HOME_API);
        volleyManager.ResponseListener(new ForecastResponse());

    }

    private class ForecastResponse implements VolleyManager.OnResponseListener {
        @Override
        public void onResponseReceived(String json) {
            progressDialog.dismiss();
            GsonBuilder gsonBuilder = new GsonBuilder();
            Gson gson = gsonBuilder.create();

            HomeModel models = gson.fromJson(json, HomeModel.class);
            City cities = (City) models.getCity();
            mTvTitle.setText(cities.getName());

            ArrayList<List> mArrayList = (ArrayList<List>) models.getList();
            HomeAdapter mHomeAdapter = new HomeAdapter(mArrayList);
            mHomeAdapter.SetOnItemClick(new OnItemClickListner());
            mRecycleView.setAdapter(mHomeAdapter);

        }
    }

    private  class OnItemClickListner implements HomeAdapter.OnItemViewClickListner {
        @Override
        public void onItemClick(int position, List item) {

            Bundle bundle = new Bundle();
            bundle.putInt(POSITION,position);
            bundle.putString(CITY_NAME,mTvTitle.getText().toString());
            bundle.putParcelable(ITEM,item);

            DetailsFragment detailsFragment = new DetailsFragment();
            detailsFragment.setArguments(bundle);

            FragmentManager fragmentManager = getFragmentManager();
            if (fragmentManager!= null ) {
                fragmentManager.beginTransaction().replace(R.id.base_container, detailsFragment)
                        .addToBackStack("")
                        .commit();
            }
        }
    }

}