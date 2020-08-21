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

public class FirestoreAdapter2 extends FirestorePagingAdapter<ImageUrl, FirestoreAdapter2.ImagesViewHolder2> {

    private OnListItemClick2 onListItemClick2;
    private Context context;


    public FirestoreAdapter2(@NonNull FirestorePagingOptions<ImageUrl> options, OnListItemClick2 onListItemClick2, Context context) {
        super(options);
        this.onListItemClick2 = onListItemClick2;
        this.context = context;
    }

    @Override
    protected void onBindViewHolder(@NonNull ImagesViewHolder2 holder, int position, @NonNull ImageUrl model) {

        final String url = model.getUrl();

        CircularProgressDrawable circularProgressDrawable = new CircularProgressDrawable(context);
        circularProgressDrawable.setStrokeWidth(10f);
        circularProgressDrawable.setCenterRadius(20f);
        circularProgressDrawable.start();


        Glide
                .with(holder.itemView.getContext())
                .load(url)
                .placeholder(circularProgressDrawable)
                .skipMemoryCache(true)
                .centerCrop()
                .into(holder.imageView);

    }

    @NonNull
    @Override
    public ImagesViewHolder2 onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view2 = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_menu,parent,false);
        return new ImagesViewHolder2(view2);
    }

    public class ImagesViewHolder2 extends RecyclerView.ViewHolder implements View.OnClickListener {

        private ImageView imageView;

        public ImagesViewHolder2(@NonNull View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.cardViewMenu);
            itemView.setOnClickListener(this);

        }

        @Override
        public void onClick(View v) {

            onListItemClick2.onItemClick(getItem(getAdapterPosition()), getAdapterPosition());

        }
    }

    public interface OnListItemClick2 {

        void onItemClick (DocumentSnapshot snapshot, int position);


    }
}
