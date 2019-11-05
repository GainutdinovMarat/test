package com.gmarat.testapp.view;


import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.gmarat.testapp.DataViewModel;
import com.gmarat.testapp.R;
import com.squareup.picasso.Picasso;

public class DetailsFragment extends Fragment {

    public DetailsFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.item_details, container, false);

        ImageView img = view.findViewById(R.id.img);
        TextView title = view.findViewById(R.id.title);

        ViewModelProviders.of(getActivity()).get(DataViewModel.class)
                .getSelected()
                .observe(this, dataItem -> {
                    Picasso.get().load(dataItem.getUrl()).into(img);
                    title.setText(dataItem.getTitle());
                });
        return view;
    }

}
