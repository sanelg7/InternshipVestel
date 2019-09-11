package com.example.tablayoutdemoactivity;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.IntentCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.tabs.TabLayout;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link Tab1Fragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 */
public class Tab1Fragment extends Fragment implements
        GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener,
        LocationListener {

    private OnFragmentInteractionListener mListener;
    private final int REQUEST_CODE = 2;
    private Context context;
    private GoogleApiClient googleApiClient;
    private static final int Request_User_Location_Code = 99;
    private GoogleMap mMap;





    //api
    private String tag = "MainActivity";
    private String lat = "lat=38.456";
    private String lon = "lon=27.654";

    private void startApiRequest() {

        String url = "https://developers.zomato.com/api/v2.1/search?" + lat + "&" + lon + "&radius=1000&count=2&sort=real_distance";
        RequestQueue queue = Volley.newRequestQueue(context);

        JsonObjectRequest req = new JsonObjectRequest(Request.Method.GET,url,
                null, new Response.Listener<JSONObject>() {

            @Override
            public void onResponse(JSONObject response) {
                Log.d(tag, response.toString());
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d(tag, "Error: " + error.getMessage());
                Log.e(tag, "Site Info Error: " + error.getMessage());

            }
        }) {

            /**
             * Passing some request headers
             */
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String, String> headers = new HashMap<String, String>();
                //headers.put("Content-Type", "application/json");
                headers.put("user-key", "54fbdee9095012d3a2c61affb85b2f26");
                return headers;
            }
        };

        //şu an buradasın
        try {
            JSONObject reader = new JSONObject(queue.toString());
            JSONObject restaurant = reader.getJSONObject("restaurant");
            String name = restaurant.getString("name");
            System.out.println(name);

        } catch (JSONException e) {
            e.printStackTrace();
        }
        queue.add(req);
        System.out.println((queue.toString()));
        //Marker restMarkers ;
        //for(int i=0;i<r)
    }

    public Tab1Fragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.fragment_tab1, container, false);
         context = getContext();
         Button button = view.findViewById(R.id.setMarkersButton);
         button.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 startApiRequest();
             }
         });

        final SupportMapFragment mapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.frg);  //use SuppoprtMapFragment for using in fragment instead of activity  MapFragment = activity   SupportMapFragment = fragment
        assert mapFragment != null;

        mapFragment.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(GoogleMap googleMap) {

                mMap = googleMap;



                mMap.setOnInfoWindowClickListener(new GoogleMap.OnInfoWindowClickListener() {

                    public void onInfoWindowClick(Marker marker) {


                        Intent i = new Intent(context, DetailsActivity.class);
                        startActivityForResult(i, REQUEST_CODE);

                    }
                });

              /*  mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);


                /*Json Arraydaki Location bilgilerini tek tek çekecek bir loop yazılcak
                buraya da position, title, snippet yeterli bilgi olarak
                pos: lat long
                title: title
                snippet : bakcez


                mMap.clear(); //clear old markers

                mMap.addMarker(new MarkerOptions()
                        .position(new LatLng(37.4629101,42))
                        .title("TR")
                        .snippet("Cehenneme HG"));

                mMap.setOnInfoWindowClickListener(new GoogleMap.OnInfoWindowClickListener() {

                    public void onInfoWindowClick(Marker marker) {

                        Intent i = new Intent(context, DetailsActivity.class);
                        startActivityForResult(i, REQUEST_CODE);

                    }
                });




*/

                if (ContextCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {


                    buildGoogleApiClient();


                    mMap.setMyLocationEnabled(true);

                }
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    checkUserLocationPermission();
                }
            }
            });
        startApiRequest();
        return view;







        }
                public boolean checkUserLocationPermission(){
                if(ContextCompat.checkSelfPermission(context,Manifest.permission.ACCESS_FINE_LOCATION)
                        != PackageManager.PERMISSION_GRANTED){
                    if(ActivityCompat.shouldShowRequestPermissionRationale(getActivity(),Manifest.permission.ACCESS_FINE_LOCATION)){
                        ActivityCompat.requestPermissions(getActivity(),new String[]{Manifest.permission.ACCESS_FINE_LOCATION},Request_User_Location_Code);
                    }else{
                        ActivityCompat.requestPermissions(getActivity(),new String[]{Manifest.permission.ACCESS_FINE_LOCATION},Request_User_Location_Code);
                    }
                    return false;
                }else{
                    return true;
                }
            }

            public void onRequestPermissionResult(int requestcode,@NonNull String[] permissions,@NonNull int[] grantResults){
                switch (requestcode){
                    case Request_User_Location_Code:
                        if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                            if(ContextCompat.checkSelfPermission(getContext(),Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED){
                                if(googleApiClient==null) {
                                    buildGoogleApiClient();

                                }
                                mMap.setMyLocationEnabled(true);
                            }
                        }else{
                            Toast.makeText(context, "Permission Denied... ", Toast.LENGTH_SHORT).show();

                        }return;
                }
            }
            protected synchronized void buildGoogleApiClient(){
                googleApiClient =new GoogleApiClient.Builder(context)
                        .addConnectionCallbacks(Tab1Fragment.this)
                        .addOnConnectionFailedListener(Tab1Fragment.this)
                        .addApi(LocationServices.API)
                        .build();

                googleApiClient.connect();
            }
    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }
    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }
    @Override
    public void onConnected(@Nullable Bundle bundle) {
        LocationRequest locationRequest = new LocationRequest();
        locationRequest.setInterval(1100);
        locationRequest.setFastestInterval(1100);
        locationRequest.setPriority(LocationRequest.PRIORITY_BALANCED_POWER_ACCURACY);

        if(ContextCompat.checkSelfPermission(context,Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED)
        {
            LocationServices.FusedLocationApi.requestLocationUpdates(googleApiClient, locationRequest,this);

        }

    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

    @Override
    public void onLocationChanged(Location location) {


        Marker currentUserLocationMarker = mMap.addMarker(new MarkerOptions()
                .position(new LatLng(100, 100)));

        if(currentUserLocationMarker != null){
            currentUserLocationMarker.remove();
        }


        LatLng latLng = new LatLng(location.getLatitude(), location.getLongitude());
        MarkerOptions markerOptions = new MarkerOptions();
        markerOptions.title("User Current Location");
        markerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_CYAN));

        //latlon yeri
        String lati = String.valueOf(location.getLatitude());
        String longi = String.valueOf(location.getLongitude());
        lati = "lat=" + lati;
        longi = "lon=" + longi;
        lat = lati;
        lon = longi;

       //currentUserLocationMarker = mMap.addMarker(markerOptions);

        mMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
        mMap.animateCamera(CameraUpdateFactory.zoomBy(14));

        if(googleApiClient != null)
        {
            LocationServices.FusedLocationApi.removeLocationUpdates(googleApiClient,this);
        }

    }


    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }


}
