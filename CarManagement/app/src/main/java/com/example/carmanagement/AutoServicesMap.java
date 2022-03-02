package com.example.carmanagement;

import androidx.fragment.app.FragmentActivity;

import android.graphics.Color;
import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;

public class AutoServicesMap extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auto_services_map);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        mMap.setMinZoomPreference(14);

        LatLng CapitalServiceAuto = new LatLng(44.378059873406926, 26.102716490529115);
        mMap.addMarker(new MarkerOptions().position(CapitalServiceAuto).title("Marker at Capital Service Auto"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(CapitalServiceAuto));

        LatLng BoschCarService = new LatLng(44.46469119471834, 26.073190268774642);
        mMap.addMarker(new MarkerOptions().position(BoschCarService).title("Marker at Bosch Car Service"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(BoschCarService));

        LatLng ContactAutoService = new LatLng(44.41616118279034, 26.090834155585885);
        mMap.addMarker(new MarkerOptions().position(ContactAutoService).title("Marker at Contact Auto Service"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(ContactAutoService));

        LatLng DiamondAutoService = new LatLng(44.4860875963318, 26.113538284424234);
        mMap.addMarker(new MarkerOptions().position(DiamondAutoService).title("Marker at Diamond Auto Service"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(DiamondAutoService));

        MarkerOptions markerOptions = new MarkerOptions();
        markerOptions.position(DiamondAutoService).title("Diamond Auto Service").icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_VIOLET));

        PolylineOptions plo = new PolylineOptions();
        plo.add(CapitalServiceAuto);
        plo.add(BoschCarService);
        plo.add(ContactAutoService);
        plo.add(DiamondAutoService);
        plo.color(Color.BLUE);
        plo.width(15);

        mMap.addPolyline(plo);

        Marker m = mMap.addMarker(markerOptions);
        m.showInfoWindow();
    }
}