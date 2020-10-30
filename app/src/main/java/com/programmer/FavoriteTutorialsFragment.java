package com.programmer;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.widget.PopupMenu;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import java.util.ArrayList;
import static com.programmer.Helper.*;

public class FavoriteTutorialsFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_favorite_tutorials, container, false);

        final String [] names = getResources().getStringArray(R.array.tutorials_names);

        final FavoriteSQLite favoriteSQLite = new FavoriteSQLite(getContext());
        final ArrayList<String> favoritePositions = favoriteSQLite.getData(TABLE_TUTORIALS, COLUMN_TUTORIALS_POSITION);

        ArrayList<Item> items = new ArrayList<>();

        for (int i = 0; i < favoritePositions.size(); i++){
            int position = Integer.parseInt(favoritePositions.get(i));
            items.add(i, new Item(tutorialsImages[position], names[position]));
        }

        GridViewAdapter gridViewAdapter = new GridViewAdapter(getContext(), items);
        final GridView gridView = view.findViewById(R.id.gridView);

        gridView.setAdapter(gridViewAdapter);

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getContext(), PdfActivity.class);
                int actualPosition = Integer.parseInt(favoritePositions.get(position));
                intent.putExtra(EXTRA_POSITION, actualPosition);
                startActivity(intent);
            }
        });

        gridView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, final View view, final int position, long id) {
                PopupMenu popup = new PopupMenu(requireContext(), view);
                popup.inflate(R.menu.popup_favorite);
                popup.show();
                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        String actualPosition = favoritePositions.get(position);
                        favoriteSQLite.delete(TABLE_TUTORIALS, COLUMN_TUTORIALS_POSITION, actualPosition);

                        ArrayList<String> favoritePositions = favoriteSQLite.getData(TABLE_TUTORIALS, COLUMN_TUTORIALS_POSITION);

                        ArrayList<Item> items = new ArrayList<>();

                        for (int i = 0; i < favoritePositions.size(); i++){
                            int position = Integer.parseInt(favoritePositions.get(i));
                            items.add(i, new Item(tutorialsImages[position], names[position]));
                        }

                        GridViewAdapter gridViewAdapter = new GridViewAdapter(getContext(), items);
                        gridView.setAdapter(gridViewAdapter);

                        return false;
                    }
                });
                return false;
            }
        });



        return view;
    }
}