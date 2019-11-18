package net.dika.masjid.activity;

import androidx.fragment.app.FragmentActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import net.dika.masjid.R;
import net.dika.masjid.constants.PlacesConstant;
import net.dika.masjid.models.Results;

import java.util.ArrayList;
import java.util.List;

public class ShowPlacesOnMapActivity extends FragmentActivity implements OnMapReadyCallback {

    List<Results> results = new ArrayList<Results>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_places_on_map);

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        results = PlacesConstant.results;
        Toast.makeText(this, String.valueOf(results.size()), Toast.LENGTH_LONG).show();
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        for (int i = 0; i < results.size(); i++) {
            MarkerOptions markerOptions = new MarkerOptions();
            Results googlePlace = results.get(i);
            double lat = Double.parseDouble(googlePlace.getGeometry().getLocation().getLat());
            double lng = Double.parseDouble(googlePlace.getGeometry().getLocation().getLng());
            String placeName = googlePlace.getName();
            String vicinity = googlePlace.getVicinity();
            LatLng latLng = new LatLng(lat, lng);
            markerOptions.position(latLng);
            markerOptions.title(placeName);
            markerOptions.icon(BitmapDescriptorFactory.fromResource(R.drawable.masjid));
            // add marker to map
            googleMap.addMarker(markerOptions).showInfoWindow();;
            // move camera
            googleMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
            //googleMap.animateCamera(CameraUpdateFactory.zoomTo(15));
            googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 10));
            googleMap.getUiSettings().setMyLocationButtonEnabled(true);
            googleMap.setMyLocationEnabled(true);
            googleMap.setTrafficEnabled(true);
            googleMap.getUiSettings().setCompassEnabled(true);
            googleMap.getUiSettings().setZoomControlsEnabled(true);
        }
    }
}
