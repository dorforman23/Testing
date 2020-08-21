package com.furmandevs.myapplicationn;

import android.content.Context;
import android.graphics.BlendModeColorFilter;
import android.graphics.Color;
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

public class FirestoreAdapter extends FirestorePagingAdapter<ImageUrl, FirestoreAdapter.ImagesViewHolder> {

    private OnListItemClick onListItemClick;
    private Context context;



    public FirestoreAdapter(@NonNull FirestorePagingOptions<ImageUrl> options, OnListItemClick onListItemClick, Context context) {
        super(options);
        this.onListItemClick = onListItemClick;
        this.context = context;
    }

    @Override
    protected void onBindViewHolder(@NonNull ImagesViewHolder holder, int position, @NonNull ImageUrl model) {

        final String url = model.getUrl();

        CircularProgressDrawable circularProgressDrawable2 = new CircularProgressDrawable(context);
        circularProgressDrawable2.setStrokeWidth(10f);
        circularProgressDrawable2.setCenterRadius(20f);
        circularProgressDrawable2.start();

        Glide
                .with(holder.itemView.getContext())
                .load(url)
                .placeholder(circularProgressDrawable2)
                .centerCrop()
                .into(holder.imageView);

    }

    @NonNull
    @Override
    public ImagesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview,parent,false);
         return new ImagesViewHolder(view);
    }

    public class ImagesViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private ImageView imageView;

        public ImagesViewHolder(@NonNull View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.cardViewImages);
            itemView.setOnClickListener(this);

        }

        @Override
        public void onClick(View v) {

            onListItemClick.onItemClick(getItem(getAdapterPosition()), getAdapterPosition());

        }
    }

    public interface OnListItemClick {

        void onItemClick (DocumentSnapshot snapshot, int position);


    }
}
