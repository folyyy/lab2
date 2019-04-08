package com.example.lab2;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

public class PageFragment extends Fragment {

    int pageNumber;

    static PageFragment newInstance(int page) {
        PageFragment pageFragment = new PageFragment();
        Bundle arguments = new Bundle();
        arguments.putInt("Position", page);
        pageFragment.setArguments(arguments);
        return pageFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        pageNumber = getArguments().getInt("Position");

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment, null);
        SecondActivity obj = new SecondActivity();
        ImageView img = view.findViewById(R.id.img);
        obj.setImg(pageNumber,img);
        String helptext = obj.getHelpText(pageNumber);
        TextView desc = view.findViewById(R.id.desc);
        desc.setText(helptext);

        return view;
    }

}