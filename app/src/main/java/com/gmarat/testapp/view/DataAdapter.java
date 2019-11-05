package com.gmarat.testapp.view;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.gmarat.testapp.R;
import com.gmarat.testapp.model.DataItem;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class DataAdapter extends RecyclerView.Adapter<DataAdapter.ItemViewHolder> {

    public interface OnDataItemSelectListener{
        void onItemSelected(@NonNull DataItem dataItem);
    }
    private final List<DataItem> mItems;
    private final OnDataItemSelectListener mListener;

    public DataAdapter(@NonNull OnDataItemSelectListener listener) {
        mItems = new ArrayList<>();
        mListener = listener;
    }

    public void setData(List<DataItem> items) {
        if (items != null) {
            mItems.clear();
            mItems.addAll(items);
            notifyDataSetChanged();
        }
    }

    @NonNull
    @Override
    public DataAdapter.ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ItemViewHolder(
                LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.item, parent, false)

        );
    }

    @Override
    public void onBindViewHolder(@NonNull DataAdapter.ItemViewHolder holder, int position) {
        holder.bind(mItems.get(position));
        holder.itemView.setOnClickListener(v -> mListener.onItemSelected(mItems.get(position)));
    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }

    class ItemViewHolder extends RecyclerView.ViewHolder {

        ImageView image;
        TextView title;

        ItemViewHolder(@NonNull View itemView) {
            super(itemView);

            image = itemView.findViewById(R.id.img);
            title = itemView.findViewById(R.id.title);
        }

        void bind(DataItem dataItem) {
            Picasso.get().load(dataItem.getUrl()).into(image);
            title.setText(dataItem.getTitle());
        }
    }
}
