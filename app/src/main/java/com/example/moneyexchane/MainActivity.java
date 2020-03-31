package com.example.moneyexchane;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ObservableField;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.moneyexchane.databinding.ActivityMainBinding;
import com.example.moneyexchane.databinding.ItemChipBinding;
import com.google.android.material.chip.Chip;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    List<ItemChipViewModel> listitem = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        final ActivityMainBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        LayoutInflater inflater = LayoutInflater.from(this);
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        lp.setMargins(5, 5, 5, 5);

        for (int i = 0; i < 14; i++) {
            ItemChipViewModel itemChipViewModel = new ItemChipViewModel();
            itemChipViewModel.position.set(i);
            listitem.add(itemChipViewModel);
        }

        for (int i = 0; i < 14; i++) {
            ItemChipBinding chipbindiing = DataBindingUtil.inflate(inflater, R.layout.item_chip, null, false);
            listitem.get(i).text.set(listitem.get(i).position.get().toString());
            chipbindiing.setViewModel(listitem.get(i));
            Chip chip = chipbindiing.chipText;
            chip.setLayoutParams(lp);
            binding.flexbox.addView(chip);

            chip.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    binding.flexbox.removeView(view);
                    for (int i = 0; i < listitem.size(); i++) {
                        if (listitem.get(i).position.get() > Integer.parseInt(((Chip) view).getText().toString())) {
                            listitem.get(i).position.set(listitem.get(i).position.get()-1);
                            listitem.get(i).text.set(listitem.get(i).position.get().toString());
                        }
                    }
                }
            });
        }

//        recyclerView = findViewById(R.id.rc);
//        List<String> strings = new ArrayList<>();
//
//        for (int i = 0; i < 15; i++) {
//            strings.add(i + "");
//        }
//
//        recyclerView.setLayoutManager(new LinearLayoutManager(this));
//        recyclerView.setAdapter(new RcAdapter(strings));
    }
}
