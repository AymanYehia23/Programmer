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

import static com.programmer.Helper.COLUMN_TUTORIALS_POSITION;
import static com.programmer.Helper.COLUMN_WEBSITE_POSITION;
import static com.programmer.Helper.TABLE_TUTORIALS;
import static com.programmer.Helper.TABLE_WEBSITES;
import static com.programmer.Helper.toast;

public class TutorialsFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_tutorials, container, false);

        String [] names = getResources().getStringArray(R.array.tutorials_names);

        ArrayList<Item> items = new ArrayList<>();

        for (int i = 0; i < names.length; i++){
            items.add(i, new Item(Helper.tutorialsImages[i], names[i]));
        }

        GridViewAdapter gridViewAdapter = new GridViewAdapter(getContext(), items);
        GridView gridView = view.findViewById(R.id.gridView);

        gridView.setAdapter(gridViewAdapter);

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getContext(), PdfActivity.class);
                intent.putExtra(Helper.EXTRA_POSITION, position);
                startActivity(intent);
            }
        });

        gridView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {
                PopupMenu popup = new PopupMenu(requireContext(), view);
                popup.inflate(R.menu.popup_menu);
                popup.show();
                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        FavoriteSQLite favoriteSQLite = new FavoriteSQLite(getContext());
                        long result = favoriteSQLite.insert(TABLE_TUTORIALS, COLUMN_TUTORIALS_POSITION, String.valueOf(position));
                        if (result == -1) toast(getContext(), "Not inserted");
                        else toast(getContext(), "Inserted");
                        return false;
                    }
                });
                return false;
            }
        });

        return view;
    }
}