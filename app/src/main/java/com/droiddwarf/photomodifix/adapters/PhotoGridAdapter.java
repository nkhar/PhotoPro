package com.droiddwarf.photomodifix.adapters;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.droiddwarf.photomodifix.R;
import com.droiddwarf.photomodifix.data.Photo;
import com.droiddwarf.photomodifix.data.PhotoHolder;
import com.droiddwarf.photomodifix.ui.MainActivity;

import java.io.File;
import java.util.ArrayList;

import butterknife.ButterKnife;

public class PhotoGridAdapter extends RecyclerView.Adapter<PhotoGridAdapter.PhotoViewHolder> implements View.OnClickListener  {


    public PhotoGridAdapter(MainActivity context) {
        mContext = context;
        mSelectedIndices = new ArrayList<>();
    }

    private MainActivity mContext;
    private Photo[] mPhotos;
    private ArrayList<Integer> mSelectedIndices;

    public void saveInstanceState(Bundle out) {
        if (mPhotos != null)
            out.putSerializable("photos", new PhotoHolder(mPhotos));
        out.putSerializable("selected_indices", mSelectedIndices);
    }

    public void restoreInstanceState(Bundle in) {
        if (in != null) {
            if (in.containsKey("selected_indices")) {
                //noinspection unchecked
                mSelectedIndices = (ArrayList<Integer>) in.getSerializable("selected_indices");
                if (mSelectedIndices == null) mSelectedIndices = new ArrayList<>();
            }
            if (in.containsKey("photos")) {
                PhotoHolder ph = (PhotoHolder) in.getSerializable("photos");
                if (ph != null) setPhotos(ph.photos);
            }
        }
    }

    public void setPhotos(Photo[] photos) {
        mPhotos = photos;
        notifyDataSetChanged();
    }

    public void toggleSelected(int index) {
        if (mSelectedIndices.contains(index)) {
            mSelectedIndices.remove((Integer) index);
        } else {
            mSelectedIndices.add(index);
        }
        notifyItemChanged(index);
        if (mContext != null)
            mContext.onSelectionChanged(mSelectedIndices.size());
    }

    public void clearSelected() {
        mSelectedIndices.clear();
        notifyDataSetChanged();
        if (mContext != null)
            mContext.onSelectionChanged(0);
    }

    public int getSelectedCount() {
        return mSelectedIndices.size();
    }

    public Photo[] getSelectedPhotos() {
        ArrayList<Photo> selected = new ArrayList<>();
        for (Integer index : mSelectedIndices)
            selected.add(mPhotos[index]);
        return selected.toArray(new Photo[selected.size()]);
    }

    @Override
    public PhotoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.griditem_photo, parent, false);
        return new PhotoViewHolder(v);
    }

    @Override
    public void onBindViewHolder(PhotoViewHolder holder, int position) {
        Glide.with(mContext)
                .load(new File(mPhotos[position]._data))
                .into(holder.image);
        if (mSelectedIndices.contains(position)) {
            holder.check.setVisibility(View.VISIBLE);
            holder.circle.setActivated(true);
            holder.image.setActivated(true);
        } else {
            holder.check.setVisibility(View.GONE);
            holder.circle.setActivated(false);
            holder.image.setActivated(false);
        }

        holder.itemView.setTag(position);
        holder.itemView.setOnClickListener(this);
    }

    @Override
    public int getItemCount() {
        return mPhotos != null ? mPhotos.length : 0;
    }

    @Override
    public void onClick(View v) {
        if (v.getTag() != null) {
            int index = (Integer) v.getTag();
            toggleSelected(index);
        }
    }


    public class PhotoViewHolder extends RecyclerView.ViewHolder {

        final ImageView image;
        final View check;
        final View circle;

        public PhotoViewHolder(View itemView) {
            super(itemView);
            image = ButterKnife.findById(itemView, R.id.image);
            check = itemView.findViewById(R.id.check);
            circle = itemView.findViewById(R.id.circle);
        }
    }


}
