package com.example.rest.presentation.mainpage;

import static androidx.core.os.BundleKt.bundleOf;

import static com.example.rest.presentation.restaurant.RestaurantFragment.ARG_ID;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.example.rest.R;
import com.example.rest.domain.Restaurant;

import java.util.List;

public class MainPageFragment extends Fragment {
    private RecyclerView recyclerView;
    private final MainPageViewModel viewModel = new MainPageViewModel();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_main_page, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView = view.findViewById(R.id.recyclerView);
        observeViewModel();
        viewModel.start();
    }

    private void observeViewModel() {
        viewModel.getRestaurantsData().observe(getViewLifecycleOwner(), this::applyData);
    }

    private void applyData(List<Restaurant> restaurantsList) {
        recyclerView.setAdapter(new RestaurantsAdapter(restaurantsList, restaurant -> navigateToRestaurant(restaurant.id)));
    }

    private void navigateToRestaurant(int restaurantId) {
        if (getView() != null) {
            Bundle bundle = bundleOf(ARG_ID, restaurantId);
            Navigation.findNavController(getView()).navigate(R.id.navigate_to_restaurant_fragment, bundle);
        }
    }
    private Bundle bundleOf(String argId, int restaurantId) {
        Bundle bundle = new Bundle();
        bundle.putInt(ARG_ID, restaurantId);
        return bundle;
    }
}
