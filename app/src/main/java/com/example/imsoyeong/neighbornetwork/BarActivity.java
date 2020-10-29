package com.example.imsoyeong.neighbornetwork;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;

import com.roughike.bottombar.BottomBar;
import com.roughike.bottombar.OnTabSelectListener;

/**
 * Created by imsoyeong on 2017. 11. 10..
 */

public class BarActivity extends FragmentActivity
        implements TimelineFragment.OnFragmentInteractionListener,
        GroupFragment.OnFragmentInteractionListener,
        NoticeFragment.OnFragmentInteractionListener,
        TimelineRegisterFragment.OnFragmentInteractionListener,
        NoticeRegisterFragment.OnFragmentInteractionListener
{

    private TimelineFragment timelineFragment;
    private GroupFragment groupFragment;
    private NoticeFragment noticeFragment;

    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bar_layout);

        final DBHelper dbHelper = new DBHelper(getApplicationContext(), "NNmember.db",null,1);


        timelineFragment = new TimelineFragment();
        groupFragment = new GroupFragment();
        noticeFragment = new NoticeFragment();



        Intent intent = getIntent();
        final String loginId = intent.getStringExtra("loginId");
        final Member loginMember = dbHelper.findMemberById(loginId);

        Bundle bundle = new Bundle();
        bundle.putString("loginId", loginId);
        timelineFragment.setArguments(bundle);

        initFragment();

        BottomBar bottomBar = (BottomBar) findViewById(R.id.bottomBar);

        bottomBar.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelected(int tabId) {
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                if(tabId == R.id.tab_home){
                    transaction.replace(R.id.fragment_container, timelineFragment).commit();


                }
                else if(tabId == R.id.tab_notice){
                    transaction.replace(R.id.fragment_container, noticeFragment).commit();
                }
                else if(tabId == R.id.tab_group){
                    transaction.replace(R.id.fragment_container, groupFragment).commit();
                }
            }
        });

    }
    public void initFragment(){
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.add(R.id.fragment_container, timelineFragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

}
