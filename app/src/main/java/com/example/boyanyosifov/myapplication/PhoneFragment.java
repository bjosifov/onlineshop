package com.example.boyanyosifov.myapplication;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.boyanyosifov.myapplication.com.online.shop.adapters.ShopRecyclerViewAdapter;
import com.example.boyanyosifov.myapplication.com.online.shop.business.logic.ManagerFactory;
import com.example.boyanyosifov.myapplication.com.online.shop.repository.Product;
import com.example.boyanyosifov.myapplication.com.online.shop.utils.SpacesItemDecoration;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class PhoneFragment extends Fragment {

    RecyclerView recyclerView;

    public PhoneFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_phone, container, false);
        //setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        recyclerView = view.findViewById(R.id.product_list_phoneFragment);
        GridLayoutManager mGrid = new GridLayoutManager(getActivity(), 2);
        recyclerView.setLayoutManager(mGrid);
        recyclerView.setHasFixedSize(true);
        recyclerView.addItemDecoration(new SpacesItemDecoration(2, 12, false));

        ShopRecyclerViewAdapter shopAdapter = new ShopRecyclerViewAdapter(getActivity(), getAllProductsOnSale());
        recyclerView.setAdapter(shopAdapter);

        return view;
    }


    private List<Product> getAllProductsOnSale(){
        List<Product> products = new ArrayList<>();
        products.addAll(ManagerFactory.getPhonesManager(getActivity()).getAll());
        //products.addAll(ManagerFactory.getLaptopManager(getActivity()).getAll());
        return products;
    }
}
