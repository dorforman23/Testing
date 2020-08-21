package com.furmandevs.myapplicationn;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.CircularProgressDrawable;

import com.bumptech.glide.Glide;
import com.firebase.ui.firestore.paging.FirestorePagingAdapter;
import com.firebase.ui.firestore.paging.FirestorePagingOptions;
import com.google.firebase.firestore.DocumentSnapshot;

public class FirebaseAdapter3 extends FirestorePagingAdapter<ImageUrl, FirebaseAdapter3.ImagesViewHolder3> {

    private OnListItemClick3 onListItemClick3;
    private Context context;


    public FirebaseAdapter3(@NonNull FirestorePagingOptions<ImageUrl> options, OnListItemClick3 onListItemClick3, Context context) {
        super(options);
        this.onListItemClick3 = onListItemClick3;
        this.context = context;
    }

    @Override
    protected void onBindViewHolder(@NonNull ImagesViewHolder3 holder, int position, @NonNull ImageUrl model) {

        final String url = model.getUrl();

        CircularProgressDrawable circularProgressDrawable = new CircularProgressDrawable(context);
        circularProgressDrawable.setStrokeWidth(10f);
        circularProgressDrawable.setCenterRadius(20f);
        circularProgressDrawable.start();

        Glide
                .with(holder.itemView.getContext())
                .load(url)
                .placeholder(circularProgressDrawable)
                .centerCrop()
                .into(holder.imageView);

    }

    @NonNull
    @Override
    public ImagesViewHolder3 onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view2 = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_cardview,parent,false);
        return new FirebaseAdapter3.ImagesViewHolder3(view2);
    }


    public class ImagesViewHolder3 extends RecyclerView.ViewHolder implements View.OnClickListener {

        private ImageView imageView;

        public ImagesViewHolder3(@NonNull View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.fragmentImageView);
            itemView.setOnClickListener(this);

        }

        @Override
        public void onClick(View v) {

            onListItemClick3.onItemClick(getItem(getAdapterPosition()), getAdapterPosition());

        }
    }

    public interface OnListItemClick3 {

        void onItemClick (DocumentSnapshot snapshot, int position);


    }


}
