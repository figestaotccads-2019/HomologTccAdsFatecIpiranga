package br.com.figestaotcc.homologtccadsfatecipiranga.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import br.com.figestaotcc.homologtccadsfatecipiranga.R;

public class Home2Fragment extends Fragment {

        View view;


        public Home2Fragment(){

        }

        @Override
        public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
            super.onViewCreated(view, savedInstanceState);

            getActivity().setTitle(getString(R.string.menu_feed));


        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {

            view = inflater.inflate(R.layout.fragment_home2, container, false);
            return view;
        }

    }


