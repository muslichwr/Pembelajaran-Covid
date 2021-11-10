package com.mumus.pembelajarancovid.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.mumus.pembelajarancovid.Model.AllStates;
import com.mumus.pembelajarancovid.R;

import java.util.ArrayList;
import java.util.List;

public class StateAdapter extends RecyclerView.Adapter<StateAdapter.ViewHolder>  {
     List<AllStates> listStateItems;
     Context context;
    // List<AllStates> listStatesAll;


    public StateAdapter(List<AllStates> listStateItems, Context context) {
        this.listStateItems = listStateItems;
        this.context = context;
       // listStatesAll = new ArrayList<>(listStateItems);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_state, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        AllStates stateAdapter = listStateItems.get(position);
        holder.stateName.setText(stateAdapter.getState());
        holder.nriState.setText(Integer.toString(stateAdapter.getNri()));
        holder.casesState.setText(Integer.toString(stateAdapter.getCases()));
        holder.curedState.setText(Integer.toString(stateAdapter.getDeath()));
        holder.deathState.setText(Integer.toString(stateAdapter.getRecover()));
    }

    @Override
    public int getItemCount() {
        return listStateItems.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView stateName, casesState, nriState, curedState, deathState;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            stateName = itemView.findViewById(R.id.state_name);
            casesState = itemView.findViewById(R.id.casesState);
            nriState = itemView.findViewById(R.id.nriState);
            curedState = itemView.findViewById(R.id.recoverState);
            deathState = itemView.findViewById(R.id.deathState);


        }


    }

    public void updateList(List<AllStates> newList) {
        listStateItems = new ArrayList<>();
        listStateItems.addAll(newList);
        notifyDataSetChanged();


    }



}
