package com.gmarat.testapp.view;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.gmarat.testapp.DataViewModel;
import com.gmarat.testapp.R;
import com.gmarat.testapp.model.DataItem;
import com.gmarat.testapp.view.DataAdapter;
import com.google.android.material.tabs.TabLayout;

import org.jetbrains.annotations.NotNull;


public class TabsFragment extends Fragment implements DataAdapter.OnDataItemSelectListener {

    public interface OnTabItemSelectListener {
        void onTabItemSelected();
    }

    private OnTabItemSelectListener mListener;
    private DataViewModel mViewModel;

    public TabsFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_tabs, container, false);

        mViewModel = ViewModelProviders.of(getActivity()).get(DataViewModel.class);

        RecyclerView recyclerView = view.findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        DataAdapter adapter = new DataAdapter(this);
        recyclerView.setAdapter(adapter);

        TabLayout tabLayout = view.findViewById(R.id.tab_layout);
        tabLayout.addTab(tabLayout.newTab().setText("Tab 1"));
        tabLayout.addTab(tabLayout.newTab().setText("Tab 2"));
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                mViewModel.setCurrentTab(tab.getPosition());
                mViewModel.getTabData(tab.getPosition()).observe(getActivity(), adapter::setData);
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
                mViewModel.setCurrentTab(tab.getPosition());
                mViewModel.getTabData(tab.getPosition()).observe(getActivity(), adapter::setData);
            }
        });
        tabLayout.getTabAt(mViewModel.getCurrentTabPosition()).select();
        return view;
    }

    @Override
    public void onAttach(@NotNull Context context) {
        super.onAttach(context);
        if (context instanceof OnTabItemSelectListener) {
            mListener = (OnTabItemSelectListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onItemSelected(@NonNull DataItem dataItem) {
        mViewModel.select(dataItem);
        if (mListener != null) {
            mListener.onTabItemSelected();
        }
    }
}
