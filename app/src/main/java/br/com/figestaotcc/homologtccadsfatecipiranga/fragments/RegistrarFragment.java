package br.com.figestaotcc.homologtccadsfatecipiranga.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import br.com.figestaotcc.homologtccadsfatecipiranga.R;

public class RegistrarFragment extends Fragment {


    View viewRegistrar;

    public RegistrarFragment(){

    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        getActivity().setTitle(getString(R.string.menu_registrar));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        viewRegistrar = inflater.inflate(R.layout.fragment_registrar, container, false);
        return viewRegistrar;
    }
}
