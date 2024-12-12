package com.example.loginapp;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link fragment_logout#newInstance} factory method to
 * create an instance of this fragment.
 */
public class fragment_logout extends Fragment {
    Button btn1;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public fragment_logout() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment fragment_logout.
     */
    // TODO: Rename and change types and number of parameters
    public static fragment_logout newInstance(String param1, String param2) {
        fragment_logout fragment = new fragment_logout();
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
        View view= inflater.inflate(R.layout.fragment_logout, container, false);
        btn1=view.findViewById(R.id.logout);

        btn1.setOnClickListener(v -> showAlertDialog());
        return view;
    }
    private void showAlertDialog(){
        //create an alertdialog builder
        AlertDialog.Builder builder=new AlertDialog.Builder(requireContext());

        //set the title and message
        builder.setTitle("Alert!!")
                .setMessage("Are you sure want to logout ? ")

                //positive button
                .setPositiveButton("Ok",(dialog, which) -> {
                    Intent i=new Intent(getActivity(),Main1Activity.class);
                    startActivity(i);
                    getActivity().finish();
                })

                //negative button
                .setNegativeButton("Cancel",(dialog, which) -> {
                dialog.dismiss();
                });

                AlertDialog dialog=builder.create();
                dialog.show();
    }
}