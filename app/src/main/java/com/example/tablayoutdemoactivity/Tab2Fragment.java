package com.example.tablayoutdemoactivity;

import android.content.Context;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link Tab2Fragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 */
public class Tab2Fragment extends Fragment {

    private OnFragmentInteractionListener mListener;
    RecyclerView recList ;
    private Context context = getContext();

    public Tab2Fragment() {}


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {



        final View view = inflater.inflate(R.layout.fragment_tab2, container, false);


        recList=view.findViewById(R.id.cardList);
        recList.setHasFixedSize(true);

        recList.setLayoutManager(new LinearLayoutManager(this.getContext()));

        Tab2Adapter tab2Adapter = new Tab2Adapter(context);
        recList.setAdapter(tab2Adapter);
        recList.setLayoutManager(new LinearLayoutManager(context));


      /*  recList =  view.findViewById(R.id.cardList);
        LinearLayoutManager llm = new LinearLayoutManager(getContext());
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        recList.setLayoutManager(llm);

        Tab2Adapter ca = new Tab2Adapter(createList(4));
        recList.setAdapter(ca);*/

        // Inflate the layout for this fragment
        getRestDb();

        return view;





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




    /*private List<RestaurantObjectDb> createList(int size) {

        List<RestaurantObjectDb> result = new ArrayList<RestaurantObjectDb>();
        for (int i=1; i <= size; i++) {
            RestaurantObjectDb ci = new RestaurantObjectDb();
            ci.getName() = RestaurantObjectDb.NAME_PREFIX + i;
            ci.getCuisines() = RestaurantObjectDb.CUISINE_PERFIX + i;
            ci.getThumb() = RestaurantObjectDb.THUMB_PREFIX + i + "@test.com";

            result.add(ci);

        }

        return result;*/


    private void getRestDb(){
        class GetRestDb extends AsyncTask<Void,Void,List<RestaurantObjectDb>>{
            @Override
            protected List<RestaurantObjectDb> doInBackground(Void... voids){
                List<RestaurantObjectDb> restaurantObjectDbList = DatabaseClient
                        .getInstance(getContext().getApplicationContext())
                        .getAppDatabase()
                        .restaurantObjectDbDao()
                        .getAll();
                return restaurantObjectDbList;
            }
            @Override
            protected void onPostExecute(List<RestaurantObjectDb> restaurantObjectDbList) {
                super.onPostExecute(restaurantObjectDbList);
                Tab2Adapter adapter = new Tab2Adapter(getContext());
                recList.setAdapter(adapter);
            }
        }

        GetRestDb gt = new GetRestDb();
        gt.execute();
        }
    }
