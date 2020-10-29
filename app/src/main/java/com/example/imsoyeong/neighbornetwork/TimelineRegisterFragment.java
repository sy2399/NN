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
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link TimelineRegisterFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link TimelineRegisterFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class TimelineRegisterFragment extends Fragment {

    Button timelineRegisterBtn;
    RadioGroup categoryRadio;
    RadioGroup timelineRadio;



    RadioButton categoryrb;
    RadioButton trangerb;

    EditText timelineTitle;
    EditText timelineContents;

    String category;
    String timelineRange;
    String timeline_title;
    String timeline_contents;

    SharedPreferences setting;
    SharedPreferences.Editor editor;


    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public TimelineRegisterFragment() {
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
    public static TimelineRegisterFragment newInstance(String param1, String param2) {
        TimelineRegisterFragment fragment = new TimelineRegisterFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_timeline_register, container, false);
        //View view = inflater.inflate(R.layout.fragment_timeline, parent, false);

        final TimeLineDBHelper dbHelper = new TimeLineDBHelper(container.getContext(), "timeline.db",null,1);;
        final DBHelper memberdbHelper = new DBHelper(getActivity(), "NNmember.db",null,1);

        setting = this.getActivity().getSharedPreferences("setting", 0);
        final String loginInfo = setting.getString("loginInfo","");
        Member loginMember = memberdbHelper.findMemberById(loginInfo);


        timelineRegisterBtn = (Button)view.findViewById(R.id.registerTimeLineBtn);
        categoryRadio = (RadioGroup) view.findViewById(R.id.categoryRadio);
        timelineRadio = (RadioGroup) view.findViewById(R.id.timelineRadio);


        timelineTitle = (EditText) view.findViewById(R.id.timelineTitle);
        timelineContents = (EditText) view.findViewById(R.id.timelineContents);

        categoryrb = (RadioButton) view.findViewById(categoryRadio.getCheckedRadioButtonId());
        trangerb = (RadioButton)view.findViewById(timelineRadio.getCheckedRadioButtonId());

        categoryRadio.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId){
                    case R.id.categoryOption1:
                        category = "0";
                        break;
                    case R.id.categoryOption2:
                        category = "1";
                        break;
                    case R.id.categoryOption3:
                        category="2";
                        break;
                }
            }
        });

        timelineRadio.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.timelineRangeOption1:
                        timelineRange = "0";
                        break;
                    case R.id.timelineRangeOption2:
                        timelineRange = "1";
                        break;

                }
            }
        });

        timelineRegisterBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TimeLine tl = new TimeLine();

                timeline_title = timelineTitle.getText().toString();
                timeline_contents = timelineContents.getText().toString();

                tl.setCategoryNo(category);
                tl.setRange(timelineRange);
                tl.setTitle(timeline_title);
                tl.setContents(timeline_contents);
                tl.setWriterId(loginInfo);
                tl.setHits(0);

                Toast.makeText(getContext(), "등록중", Toast.LENGTH_SHORT).show();
                dbHelper.insertTimeLine(tl);
                Toast.makeText(getContext(), "등록 성공", Toast.LENGTH_LONG).show();

                Fragment fragment = new Fragment();
                fragment = new TimelineFragment();


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
