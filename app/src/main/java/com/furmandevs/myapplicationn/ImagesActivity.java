package com.furmandevs.myapplicationn;

import androidx.appcompat.app.AppCompatActivity;
import androidx.paging.PagedList;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.content.Intent;
import android.graphics.Rect;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import com.firebase.ui.firestore.paging.FirestorePagingOptions;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;


public class ImagesActivity extends AppCompatActivity implements FirestoreAdapter.OnListItemClick {

    RecyclerView recyclerView;
    FirebaseFirestore firebaseFirestore;
    FirestoreAdapter adapter;
    String categoryDecider;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_images);

        recyclerView = findViewById(R.id.recyclerView);
        firebaseFirestore = FirebaseFirestore.getInstance();

        final String categoryType = getIntent().getExtras().getString("menuPickType");

        Query query = firebaseFirestore.collection(categoryType);

        PagedList.Config config = new PagedList.Config.Builder()
                .setInitialLoadSizeHint(12)
                .setPageSize(6)
                .build();

        FirestorePagingOptions<ImageUrl> options = new FirestorePagingOptions.Builder<ImageUrl>()
                .setLifecycleOwner(this)
                .setQuery(query, config, ImageUrl.class)
                .build();


        adapter = new FirestoreAdapter(options, this, this);


        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        recyclerView.addItemDecoration(new ImagesActivity.GridSpacingItemDecoration(2, 15, false));
        recyclerView.setAdapter(adapter);

    }

    @Override
    public void onItemClick(DocumentSnapshot snapshot, int position) {

        Intent goToImageFullScreen = new Intent(ImagesActivity.this, ImageFullScreen.class);
        goToImageFullScreen.putExtra("url", snapshot.getString("url"));
        startActivity(goToImageFullScreen);

    }

    public class GridSpacingItemDecoration extends RecyclerView.ItemDecoration {

        private int spanCount;
        private int spacing;
        private boolean includeEdge;

        public GridSpacingItemDecoration(int spanCount, int spacing, boolean includeEdge) {
            this.spanCount = spanCount;
            this.spacing = spacing;
            this.includeEdge = includeEdge;
        }

        @Override
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
            int position = parent.getChildAdapterPosition(view); // item position
            int column = position % spanCount; // item column

            if (includeEdge) {
                outRect.left = spacing - column * spacing / spanCount; // spacing - column * ((1f / spanCount) * spacing)
                outRect.right = (column + 1) * spacing / spanCount; // (column + 1) * ((1f / spanCount) * spacing)

                if (position < spanCount) { // top edge
                    outRect.top = spacing;
                }
                outRect.bottom = spacing; // item bottom
            } else {
                outRect.left = column * spacing / spanCount; // column * ((1f / spanCount) * spacing)
                outRect.right = spacing - (column + 1) * spacing / spanCount; // spacing - (column + 1) * ((1f /    spanCount) * spacing)
                if (position >= spanCount) {
                    outRect.top = spacing; // item top
                }
            }
        }
    }
}
