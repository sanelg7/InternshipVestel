package com.example.tablayoutdemoactivity;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.room.Query;
import androidx.room.Room;

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
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;

import static com.android.volley.VolleyLog.TAG;


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
    private LocationManager locationManager;

    //api
    String currentLocationLat;
    String currentLocationLong;
    private String tag = "MainActivity";
    private String lat;
    private String lon;


    int db_size;


    public ArrayList<restaurantObject> restaurantObjects = new ArrayList<restaurantObject>();


    private void startApiRequest() {

        final ArrayList<RestaurantObjectDb> restaurantObjectDbList = new ArrayList<RestaurantObjectDb>();


        lat = "lat=" + currentLocationLat;
        lon = "lon=" + currentLocationLong;

        System.out.println(lat);

        String url = "https://developers.zomato.com/api/v2.1/search?" + lat + "&" + lon + "&radius=1&count=3&sort=real_distance";


        System.out.println(lat + "AAA" + lon);


        RequestQueue queue = Volley.newRequestQueue(context);

        Log.d(tag, url);


        JsonObjectRequest req = new JsonObjectRequest(Request.Method.GET, url,
                null, new Response.Listener<JSONObject>() {

            @Override
            public void onResponse(JSONObject response) {

                final AppDatabase appDatabase = Room.databaseBuilder(context, AppDatabase.class, "database")
                        .allowMainThreadQueries()
                        .enableMultiInstanceInvalidation()
                        .build();
                Log.d(tag, response.toString());
                try {
                    JSONArray jsonArray = response.getJSONArray("restaurants");


                    //making new Json with the attributes we need
                    //putting restaurant objects into arraylist
                    restaurantObjectDbList.clear();

                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        JSONObject restaurant = jsonObject.getJSONObject("restaurant");


                        int id = restaurant.getInt("id");
                        String name = restaurant.getString("name");
                        String cuisines = restaurant.getString("cuisines");
                        int average_cost_for_two = restaurant.getInt("average_cost_for_two");
                        String thumb = restaurant.getString("thumb");

                        JSONObject user_rating = restaurant.getJSONObject("user_rating");

                        int aggregate_rating = user_rating.getInt("aggregate_rating");

                        int votes = user_rating.getInt("votes");

                        JSONObject location = restaurant.getJSONObject("location");

                        String city = location.getString("city");
                        String address = location.getString("address");
                        double latitude = location.getDouble("latitude");
                        double longitude = location.getDouble("longitude");

                        restaurantObject restaurantObject = new restaurantObject
                                (id, false, name, cuisines, average_cost_for_two, thumb, aggregate_rating, votes
                                        , address, city, latitude, longitude);


                        restaurantObjectDbDao rest = appDatabase.restaurantObjectDbDao();


                        RestaurantObjectDb restaurantObjectDb =
                                new RestaurantObjectDb
                                        (id, false, name, cuisines, average_cost_for_two, aggregate_rating, votes,
                                                address, city, latitude, longitude, thumb);

                        restaurantObjectDbList.add(restaurantObjectDb);
                        System.out.println("SADSADSA" + restaurantObjectDb.getName());
                        System.out.println(restaurantObjectDbList.size());

                        appDatabase.restaurantObjectDbDao().insert(restaurantObjectDb);
                        System.out.println("ENEGN");
                        System.out.println(appDatabase.restaurantObjectDbDao().getAll().get(0).getName());

                        db_size = restaurantObjectDbList.size();
                        for (int k = 0; k < db_size; k++) {
                            if (restaurantObjectDbList.get(k).getId() == appDatabase.restaurantObjectDbDao().getAll().get(k).getId()) {
                                appDatabase.restaurantObjectDbDao().update(restaurantObjectDb);
                            } else {
                                System.out.println("insert");
                                appDatabase.restaurantObjectDbDao().insert(restaurantObjectDb);

                            }


                        }


                        //Add new objects to arraylist
                        restaurantObjects.add(restaurantObject);

                        System.out.println("TTTTTTTTTTTT" + (restaurantObjects.size()));
                        ArrayList<Double> latList = new ArrayList<Double>();
                        ArrayList<Double> lonList = new ArrayList<Double>();
                        ArrayList<String> nameList = new ArrayList<String>();
                        ArrayList<String> cuisineList = new ArrayList<String>();
                        for (int w = 0; w < restaurantObjects.size(); w++) {
                            System.out.println(restaurantObjects.get(w).getName());

                            latList.add(restaurantObjects.get(w).latitude);
                            lonList.add(restaurantObjects.get(w).longitude);
                            nameList.add(restaurantObjects.get(w).name);
                            cuisineList.add(restaurantObjects.get(w).cuisines);

                        }
                        //erasing earlier markers
                        mMap.clear();

                        //setting up the markers
                        for (int j = 0; j < latList.size(); j++) {

                            Marker restMarker = mMap.addMarker(new MarkerOptions()
                                    .title(nameList.get(j))
                                    .snippet(cuisineList.get(j))
                                    .position(new LatLng
                                            (latList.get(j),
                                                    lonList.get(j))));

                        }

                        for (int a = 0; a < latList.size(); a++) {

                            latList.remove(a);
                            lonList.remove(a);
                        }
                    }
                    restaurantObjects.clear();

                    System.out.println(restaurantObjectDbList.size() + "2.deneme yeri");


                    mMap.setOnInfoWindowClickListener(new GoogleMap.OnInfoWindowClickListener() {

                        public void onInfoWindowClick(Marker marker) {


                            Intent intentDetails = new Intent(context, DetailsActivity.class);

                            String detailsName;
                            String detailsCuisine;
                            int detailsVotes;
                            String detailsThumb;
                            String detailsCity;
                            int detailsCost;


                            for (int b = 0; b < restaurantObjectDbList.size(); b++) {

                                Log.d(TAG, "marker.fj()");
                                System.out.println(marker.getPosition());
                                System.out.println(restaurantObjectDbList.get(0).getLatitude());
                                System.out.println(marker.getPosition().latitude);
                                if (restaurantObjectDbList.get(b).getLatitude() == marker.getPosition().latitude && restaurantObjectDbList.get(b).getLongitude() == marker.getPosition().longitude) {
                                    Log.d(TAG, "marker.getPosition()");

                                    detailsName = restaurantObjectDbList.get(b).getName();
                                    System.out.println(detailsName);
                                    detailsCuisine = restaurantObjectDbList.get(b).getCuisines();
                                    detailsVotes = restaurantObjectDbList.get(b).getVotes();
                                    detailsThumb = restaurantObjectDbList.get(b).getThumb();
                                    detailsCity = restaurantObjectDbList.get(b).getCity();
                                    detailsCost = restaurantObjectDbList.get(b).getAverage_cost_for_two();

                                    intentDetails.putExtra("Title", detailsName);
                                    intentDetails.putExtra("Cuisine", detailsCuisine);
                                    intentDetails.putExtra("Votes", detailsVotes);
                                    intentDetails.putExtra("Thumb", detailsThumb);
                                    intentDetails.putExtra("City", detailsCity);
                                    intentDetails.putExtra("Cost", detailsCost);

                                    Log.d(TAG, "onInfoWindowClick: " + detailsCity + detailsName);
                                }

                            }
                            startActivity(intentDetails);


                        }
                    });


                } catch (JSONException e) {
                    e.printStackTrace();
                }
                Log.d(TAG, "Marker list count should be 0 now... ");
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e(tag, "Site Info Error: " + error);

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

        queue.add(req);
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

                mMap.clear();
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

    public boolean checkUserLocationPermission() {
        if (ContextCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(getActivity(), Manifest.permission.ACCESS_FINE_LOCATION)) {
                ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, Request_User_Location_Code);
            } else {
                ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, Request_User_Location_Code);
            }
            return false;
        } else {
            return true;
        }
    }

    public void onRequestPermissionResult(int requestcode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestcode) {
            case Request_User_Location_Code:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    if (ContextCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                        if (googleApiClient == null) {
                            buildGoogleApiClient();

                        }
                        mMap.setMyLocationEnabled(true);
                    }
                } else {
                    Toast.makeText(context, "Permission Denied... ", Toast.LENGTH_SHORT).show();

                }
                return;
        }
    }

    protected synchronized void buildGoogleApiClient() {
        googleApiClient = new GoogleApiClient.Builder(context)
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

        if (ContextCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            LocationServices.FusedLocationApi.requestLocationUpdates(googleApiClient, locationRequest, this);

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

        String Tag = tag;
        String lati = String.valueOf(location.getLatitude());
        String longi = String.valueOf(location.getLongitude());

        currentLocationLat = String.valueOf(location.getLatitude());
        currentLocationLong = String.valueOf(location.getLongitude());
        System.out.println(currentLocationLat);
        String latiStr = lati;
        String longiStr = longi;

        System.out.println(latiStr);

        Double latiDouble = Double.valueOf(latiStr);
        Double longiDouble = Double.valueOf(longiStr);


        LatLng initialLoc = new LatLng(latiDouble, longiDouble);
        Marker currentUserLocationMarker = mMap.addMarker(new MarkerOptions()
                .position(initialLoc));
        if (currentUserLocationMarker != null) {
            currentUserLocationMarker.remove();
        }

        LatLng latLng = new LatLng(latiDouble, longiDouble);
        MarkerOptions markerOptions = new MarkerOptions();
        markerOptions.title("User Current Location");
        markerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_CYAN));

        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 10));

        if (googleApiClient != null) {
            LocationServices.FusedLocationApi.removeLocationUpdates(googleApiClient, this);
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


