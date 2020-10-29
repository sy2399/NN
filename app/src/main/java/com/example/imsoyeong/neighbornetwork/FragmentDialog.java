package com.example.imsoyeong.neighbornetwork;

import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;

/**
 * Created by imsoyeong on 2017. 11. 25..
 */

public class FragmentDialog extends android.support.v4.app.DialogFragment{
    int[] data = new int[2];
    int rangeType = 0;//0,1,2
    int categoryType = 0;//0,1,2,3



    private DialogInterface.OnDismissListener dismissListener;
    FragmentDialogListener fragmentDialogListener;
    public interface FragmentDialogListener{
        public void onFragmentDialogClick(FragmentDialog dialog,int[] data);


    }

    public FragmentDialog(){}


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try{
            fragmentDialogListener = (FragmentDialogListener) getTargetFragment();
        }catch(Exception e){
            e.printStackTrace();
        }
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_filter,container);

        getDialog().getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));


        Button okBtn = (Button) view.findViewById(R.id.okBtn);
        final RadioGroup rangeRadio = (RadioGroup) view.findViewById(R.id.rangeRadio);
        final RadioGroup categoryRadio = (RadioGroup) view.findViewById(R.id.categoryRadio);
        okBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                int rangeChecked = rangeRadio.getCheckedRadioButtonId();
                int categoryChecked = categoryRadio.getCheckedRadioButtonId();

                RadioButton rangeRadioBtn = (RadioButton) v.findViewById(rangeChecked);
                RadioButton categoryRadioBtn = (RadioButton) v.findViewById(categoryChecked);

                if (rangeRadioBtn != null) {
                    if (rangeRadioBtn.getText().equals("전체 글 보기")) {
                        rangeType = 0;
                    } else if (rangeRadioBtn.getText().equals("우리 동 사람이 올린 글만 보기")) {
                        rangeType = 1;
                    } else if (rangeRadioBtn.getText().equals("내 이웃이 올린 글만 보기")) {
                        rangeType = 2;
                    }
                }

                if (categoryRadioBtn != null) {
                    if (categoryRadioBtn.getText().equals("안전")) {
                        categoryType = 1;
                    } else if (categoryRadioBtn.getText().equals("물건 공유")) {
                        categoryType = 2;
                    } else if (categoryRadioBtn.getText().equals("정보 공유")) {
                        categoryType = 3;
                    }
                }
                data[0] = rangeType;
                data[1] = categoryType;


                dismiss();
            }
        });


        return view;
    }



    public void setOnDismissListener(DialogInterface.OnDismissListener $listener){
        dismissListener = $listener;
    }
    public int[] getData(){
        return data;
    }

//    @Override
//    public void onAttach(Context context) {
//        super.onAttach(context);
//
//        try{
//            fragmentDialogListener = (FragmentDialogListener) getActivity();
//        }catch(ClassCastException e){
//            e.printStackTrace();
//        }
//    }
//
//    public void someAction(){
//        data[0] = rangeType;
//        data[1] = categoryType;
//        fragmentDialogListener.onFragmentDialogClick(FragmentDialog.this, data);
//    }


}
