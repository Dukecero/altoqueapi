package com.example.consumirapirest;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.consumirapirest.adapters.ListaAdapter;
import com.example.consumirapirest.interfaces.JsonPlaceHolderApi;
import com.example.consumirapirest.model.Insumo;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link InsumosFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link InsumosFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class InsumosFragment extends Fragment {

    RecyclerView recyclerview;
    Button btnVerCarro;
    private ArrayList<Insumo> listInsumo = new ArrayList<>();
    private ArrayList<Insumo> carroCompras = new ArrayList<>();
    private ListaAdapter lAdapter;
    View vista;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public InsumosFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment InsumosFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static InsumosFragment newInstance(String param1, String param2) {
        InsumosFragment fragment = new InsumosFragment();
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
        vista = inflater.inflate(R.layout.fragment_insumos, container, false);
        btnVerCarro = vista.findViewById(R.id.btnVerCarro);
        recyclerview = vista.findViewById(R.id.recyclerViewInsumo);
        recyclerview.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        getPosts();
        return vista;
    }

    private void getPosts(){

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.1.217:3030/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        JsonPlaceHolderApi jsonPlaceHolderApi = retrofit.create(JsonPlaceHolderApi.class);
        Call<List<Insumo>> call = jsonPlaceHolderApi.getPost();
        call.enqueue(new Callback<List<Insumo>>() {
            @Override
            public void onResponse(Call<List<Insumo>> call, Response<List<Insumo>> response) {
                if(!response.isSuccessful()){
////                    mJsonTxtView.setText("Codigo:"+response.code());
                    Toast.makeText(getContext(),"Exitoso", Toast.LENGTH_SHORT);
                    return;
                }

                List<Insumo> postsList = response.body();
                listInsumo.clear();
                for(Insumo posts: postsList){
                    String numero = posts.getIns_codigo();
                    String nombre = posts.getIns_nombre();
                    String precio = posts.getIns_pre_uni();
                    String categoria = posts.getCat_nombre();
                    String image = posts.getIns_imagen();
                    listInsumo.add(new Insumo(numero,nombre,precio,categoria,image));
                }
                lAdapter = new ListaAdapter(getContext(),btnVerCarro,listInsumo,carroCompras);
                recyclerview.setAdapter(lAdapter);
            }

            @Override
            public void onFailure(Call<List<Insumo>> call, Throwable t) {

//                mJsonTxtView.setText(t.getMessage());
                Toast.makeText(getContext(),"Fallo", Toast.LENGTH_SHORT);
            }
        });

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
