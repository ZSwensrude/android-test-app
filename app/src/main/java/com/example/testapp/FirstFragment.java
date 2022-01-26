package com.example.testapp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.example.testapp.databinding.FragmentFirstBinding;

public class FirstFragment extends Fragment {

    private FragmentFirstBinding binding;
    Integer count;
    TextView main_num;


    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        binding = FragmentFirstBinding.inflate(inflater, container, false);

        return binding.getRoot();

        //inflate the layout for this fragment
        //View fragmentFirstLayout = inflater.inflate(R.layout.fragment_first, container, false);
        //get the count text view
        //showCountTextView = fragmentFirstLayout.findViewById(R.id.textview_first);

        //return fragmentFirstLayout;
    }

    private void countMe(View view){
        main_num = (TextView) getView().findViewById(R.id.textview_first);

        //get the value of text view
        String countString = main_num.getText().toString();

        //convert value to integer and increment it
        count = Integer.parseInt(countString);
        count+=2;

        //display the new value in the text view
        main_num.setText(count.toString());

    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        count = 0;

        binding.buttonScream.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast myToast = Toast.makeText(getActivity(), "AAAAAAAAAAAAAAAAAAAAAAA", Toast.LENGTH_SHORT);
                myToast.show();
            }
        });

        binding.buttonCounter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                countMe(view);
            }
        });

        binding.buttonRandom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (count > 0) {
                    int currentCount = Integer.parseInt(main_num.getText().toString());
                    FirstFragmentDirections.ActionFirstFragmentToSecondFragment action = FirstFragmentDirections.actionFirstFragmentToSecondFragment(currentCount);
                    NavHostFragment.findNavController(FirstFragment.this).navigate(action);
                }

                else{
                    NavHostFragment.findNavController(FirstFragment.this)
                            .navigate(R.id.action_FirstFragment_to_SecondFragment);
                }
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}