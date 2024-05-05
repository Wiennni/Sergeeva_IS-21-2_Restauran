package com.example.rest.presentation.restaurant;

import android.util.Pair;
import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.rest.R;

public class SlotViewHolder extends RecyclerView.ViewHolder {

    private final TextView slotTime;

    public SlotViewHolder(View itemView) {
        super(itemView);
        slotTime = itemView.findViewById(R.id.slot_time);
    }
    public void bind(Pair<String, Boolean> slot) {
        slotTime.setText(slot.first);
    }
}
