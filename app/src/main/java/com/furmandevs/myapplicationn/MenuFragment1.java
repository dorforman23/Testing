package com.furmandevs.myapplicationn;

import android.content.Intent;
import android.graphics.Rect;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.paging.PagedList;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.firebase.ui.firestore.paging.FirestorePagingOptions;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;



public class MenuFragment1 extends Fragment implements FirestoreAdapter2.OnListItemClick2{

    private TextView textView;
    private FirebaseFirestore firebaseFirestore;
    private RecyclerView recyclerView2;
    private FirestoreAdapter2 adapter2;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_menu1, container, false);


    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        recyclerView2 = getView().findViewById(R.id.recyclerViewMenu);
        firebaseFirestore = FirebaseFirestore.getInstance();


        Query query = firebaseFirestore.collection("categories");

        PagedList.Config config = new PagedList.Config.Builder()
                .setInitialLoadSizeHint(12)
                .setPageSize(6)
                .build();

        FirestorePagingOptions<ImageUrl> options = new FirestorePagingOptions.Builder<ImageUrl>()
                .setLifecycleOwner(this)
                .setQuery(query, config, ImageUrl.class)
                .build();


        adapter2 = new FirestoreAdapter2(options,this, getActivity().getApplicationContext());


        recyclerView2.setHasFixedSize(true);
        recyclerView2.setLayoutManager(new GridLayoutManager(getActivity().getApplicationContext(), 2));
        recyclerView2.addItemDecoration(new GridSpacingItemDecoration(2, 15, false));
        recyclerView2.setAdapter(adapter2);

    }

    public void onItemClick(DocumentSnapshot snapshot, int position) {

        Intent goToImageFullScreen = new Intent(getActivity(), ImagesActivity.class);
        goToImageFullScreen.putExtra("menuPickType", snapshot.getString("cat"));
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
