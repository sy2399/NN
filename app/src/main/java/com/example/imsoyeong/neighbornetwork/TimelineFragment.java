package com.example.imsoyeong.neighbornetwork;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link TimelineFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link TimelineFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class TimelineFragment extends Fragment implements FragmentDialog.FragmentDialogListener{
    // TODO: Rename parameter arguments, choose names that match

    TimeLineAdapter adapter;

    FloatingActionButton registerTimelineBtn;
    ImageButton neighborBtn;
    ImageButton settingBtn;
    ImageButton filterBtn;
    ImageButton searchKeywordBtn;
    ImageView category_img;

    EditText keywordsearchView;
    private RecyclerView rv;
    private LinearLayoutManager mLinearLayoutManager;
    ArrayList<TimeLine> timeLineList;
    String logindId;

    SharedPreferences setting;
    SharedPreferences.Editor editor;


    int rangeType = 0;
    int categoryType = 0;

    // TODO: Rename and change types of parameters


    private OnFragmentInteractionListener mListener;

    public TimelineFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment TimelineFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static TimelineFragment newInstance(String param1, String param2) {
        TimelineFragment fragment = new TimelineFragment();
        Bundle args = new Bundle();

        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = this.getArguments();
        if (bundle != null) {
           logindId = bundle.getString("loginId");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_timeline, container, false);

        final TimeLineDBHelper dbHelper = new TimeLineDBHelper(getActivity(), "timeline.db",null,1);;
        final DBHelper memberdbHelper = new DBHelper(getActivity(), "NNmember.db",null,1);
        setting = this.getActivity().getSharedPreferences("setting", 0);
        String loginInfo = setting.getString("loginInfo","");
        Member loginMember = memberdbHelper.findMemberById(loginInfo);

        


        registerTimelineBtn = (FloatingActionButton) view.findViewById(R.id.registerTimeline);
        neighborBtn = (ImageButton)view.findViewById(R.id.neighborBtn);
        settingBtn = (ImageButton)view.findViewById(R.id.settingBtn);
        filterBtn = (ImageButton) view.findViewById(R.id.filterBtn);
        keywordsearchView = (EditText) view.findViewById(R.id.keywordsearchView);
        searchKeywordBtn = (ImageButton) view.findViewById(R.id.searchKeywordBtn);

        timeLineList = new ArrayList<TimeLine>();
        mLinearLayoutManager = new LinearLayoutManager(getActivity());
        mLinearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);

        rv = (RecyclerView)view.findViewById(R.id.recyclerView);
        rv.setHasFixedSize(true);
        rv.setLayoutManager(mLinearLayoutManager);

        try{
            timeLineList = dbHelper.getTimelineList();
            adapter = new TimeLineAdapter(getActivity(), timeLineList);
            rv.setAdapter(adapter);
            adapter.notifyDataSetChanged();
        }catch(Exception e){
            e.printStackTrace();
        }


        searchKeywordBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String keyword = keywordsearchView.getText().toString();
                timeLineList = dbHelper.getTimelineListByKeyword(keyword);
                adapter = new TimeLineAdapter(getActivity(), timeLineList);
                rv.setAdapter(adapter);
                adapter.notifyDataSetChanged();
            }
        });
        registerTimelineBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment fragment = new Fragment();
                fragment = new TimelineRegisterFragment();


                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.fragment_container, fragment);
                fragmentTransaction.addToBackStack(null);

                fragmentTransaction.commit();

            }
        });

        settingBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getActivity(),ProfileSettingActivity.class);
                intent.putExtra("loginId", logindId);
                getActivity().startActivity(intent);
            }
        });


        filterBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                FragmentManager fm = getFragmentManager();
                FragmentDialog dialogFragment = new FragmentDialog();

                dialogFragment.setOnDismissListener(new DialogInterface.OnDismissListener() {
                    @Override
                    public void onDismiss(DialogInterface dialog) {

                    }
                });
                dialogFragment.show(fm,"filter");

            }


        });

        return view;
    }



    @Override
    public void onFragmentDialogClick(FragmentDialog dialog, int[] data) {
        rangeType = data[0];
        categoryType = data[1];

        final TimeLineDBHelper dbHelper = new TimeLineDBHelper(getActivity(), "timeline.db",null,1);;


            if(rangeType ==0 && categoryType ==0){
                timeLineList = dbHelper.getTimelineList();
            }else if(rangeType == 1){
                //timeLineList = dbHelper.getTimeLineList1();
            }else if(rangeType == 2){
                //timeLineList = dbHelper.getTimeLineList2();
            }else if(categoryType ==1){
                timeLineList = dbHelper.getTimeLineListByCategory("0");
            }else if(categoryType==2){
                timeLineList = dbHelper.getTimeLineListByCategory("1");
            }else if(categoryType ==3){
                timeLineList = dbHelper.getTimeLineListByCategory("2");
            }

        mLinearLayoutManager = new LinearLayoutManager(getActivity());
        mLinearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);


        rv.setHasFixedSize(true);
        rv.setLayoutManager(mLinearLayoutManager);

        TimeLineAdapter adapter = new TimeLineAdapter(getActivity(), timeLineList);
        rv.setAdapter(adapter);
        adapter.notifyDataSetChanged();
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
}
