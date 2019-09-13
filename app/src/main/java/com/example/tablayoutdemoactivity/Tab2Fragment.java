package com.example.tablayoutdemoactivity;

import android.content.Context;
import android.net.Uri;
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

/*private RecyclerView recyclerView;
    private ArrayList<CardViewItems> mCardItems;
    private Context context;
    private Tab2Adapter tab2Adapter;

    String[] titleArray = {"Title1","Title2","Title3","Title4","Title5","Title6","Title7","Title8","Title9","Title10"};

    Tab2Adapter adapter;

    public static final String EXTRA_CREATOR="creatorName";*/

    public Tab2Fragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {



        final View view = inflater.inflate(R.layout.fragment_tab2, container, false);


        RecyclerView recList ;
        recList =  view.findViewById(R.id.cardList);
        recList.setHasFixedSize(true);
        LinearLayoutManager llm = new LinearLayoutManager(getContext());
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        recList.setLayoutManager(llm);

        Tab2Adapter ca = new Tab2Adapter(createList(4));
        recList.setAdapter(ca);

        // Inflate the layout for this fragment

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




    private List<FavouritesInfo> createList(int size) {

        List<FavouritesInfo> result = new ArrayList<FavouritesInfo>();
        for (int i=1; i <= size; i++) {
            FavouritesInfo ci = new FavouritesInfo();
            ci.name = FavouritesInfo.NAME_PREFIX + i;
            ci.cuisine = FavouritesInfo.CUISINE_PERFIX + i;
            ci.thumb = FavouritesInfo.THUMB_PREFIX + i + "@test.com";

            result.add(ci);

        }

        return result;
    }
}