package com.example.imsoyeong.neighbornetwork;

import android.content.Context;
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

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link NoticeFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link NoticeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class NoticeFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER


    private RecyclerView rv;
    private LinearLayoutManager mLinearLayoutManager;
    FloatingActionButton registerNoticeBtn;
    ArrayList<Notice> noticeList;

    SharedPreferences setting;
    SharedPreferences.Editor editor;


    private OnFragmentInteractionListener mListener;

    public NoticeFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment NoticeFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static NoticeFragment newInstance(String param1, String param2) {
        NoticeFragment fragment = new NoticeFragment();
        Bundle args = new Bundle();

        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_notice, container, false);
        final NoticeDBHelper dbHelper = new NoticeDBHelper(getActivity(), "notice.db",null,1);;
        final DBHelper memberdbHelper = new DBHelper(getActivity(), "NNmember.db",null,1);

        setting = this.getActivity().getSharedPreferences("setting", 0);
        String loginInfo = setting.getString("loginInfo","");
        Member loginMember = memberdbHelper.findMemberById(loginInfo);

        registerNoticeBtn = (FloatingActionButton)view.findViewById(R.id.registerNotice);

        if(loginMember.getAdmin().equals("0")){
            registerNoticeBtn.setVisibility(View.VISIBLE);
        }else{
            registerNoticeBtn.setVisibility(View.INVISIBLE);
        }


        noticeList = new ArrayList<Notice>();

        mLinearLayoutManager = new LinearLayoutManager(getActivity());
        mLinearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);

        rv = (RecyclerView)view.findViewById(R.id.recyclerView);
        rv.setHasFixedSize(true);
        rv.setLayoutManager(mLinearLayoutManager);

        try{
            noticeList = dbHelper.getNoticeList();

            NoticeAdapter adapter = new NoticeAdapter(getActivity(), noticeList);
            rv.setAdapter(adapter);
            adapter.notifyDataSetChanged();
        }catch(Exception e){
            e.printStackTrace();
        }

        registerNoticeBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Fragment fragment = new Fragment();
                fragment = new NoticeRegisterFragment();

                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.fragment_container, fragment);
                fragmentTransaction.addToBackStack(null);

                fragmentTransaction.commit();
            }
        });

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
}
