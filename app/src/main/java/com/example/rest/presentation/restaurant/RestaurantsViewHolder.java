package com.example.rest.presentation.restaurant;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.rest.R;
import com.example.rest.domain.Restaurant;
import com.example.rest.presentation.mainpage.MainPageFragment;

import org.w3c.dom.Text;

public class RestaurantsViewHolder extends RecyclerView.ViewHolder {
    private final TextView title;
    private final TextView addressAndTime;
    private final ImageView image;

    public RestaurantsViewHolder(View itemView) {
        super(itemView);
        title = itemView.findViewById(R.id.title);
        addressAndTime = itemView.findViewById(R.id.address_and_time);
        image = itemView.findViewById(R.id.image_view);
    }

    public void bind(Restaurant restaurant) {
        String addressAndTimeText = restaurant.address + COMMA + SPACE + restaurant.workingTime;
        int resourceId = itemView.getResources().getIdentifier(
                restaurant.imageUrl, "drawable",
                itemView.getContext().getPackageName()
        );
        title.setText(restaurant.name);
        addressAndTime.setText(addressAndTimeText);
        image.setImageResource(resourceId);
    }

    private static final String SPACE = " ";
    private static final String COMMA = ",";

}
