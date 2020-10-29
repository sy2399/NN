package com.example.imsoyeong.neighbornetwork;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link NoticeRegisterFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link NoticeRegisterFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class NoticeRegisterFragment extends Fragment {

    Button noticeRegisterBtn;

    EditText noticeTitle;
    EditText noticeContents;


    String notice_title;
    String notice_contents;

    SharedPreferences setting;
    SharedPreferences.Editor editor;

    private OnFragmentInteractionListener mListener;

    public NoticeRegisterFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment TimelineRegisterFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static NoticeRegisterFragment newInstance(String param1, String param2) {
        NoticeRegisterFragment fragment = new NoticeRegisterFragment();
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
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_notice_register, container, false);
        //View view = inflater.inflate(R.layout.fragment_timeline, parent, false);

        final NoticeDBHelper dbHelper = new NoticeDBHelper(container.getContext(), "notice.db",null,1);;
        final DBHelper memberdbHelper = new DBHelper(getActivity(), "NNmember.db",null,1);

        setting = this.getActivity().getSharedPreferences("setting", 0);
        final String loginInfo = setting.getString("loginInfo","");
        Member loginMember = memberdbHelper.findMemberById(loginInfo);


        noticeRegisterBtn = (Button)view.findViewById(R.id.registerTimeLineBtn);

        noticeTitle = (EditText) view.findViewById(R.id.timelineTitle);
        noticeContents = (EditText) view.findViewById(R.id.timelineContents);


        noticeRegisterBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Notice n = new Notice();

                notice_title = noticeTitle.getText().toString();
                notice_contents = noticeContents.getText().toString();

                n.setTitle(notice_title);
                n.setContents(notice_contents);
                n.setWriterId(loginInfo);
                n.setHits(0);

                Toast.makeText(getContext(), "등록중", Toast.LENGTH_SHORT).show();
                dbHelper.insertNotice(n);
                Toast.makeText(getContext(), "등록 성공", Toast.LENGTH_LONG).show();

                Fragment fragment = new Fragment();
                fragment = new NoticeFragment();


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
