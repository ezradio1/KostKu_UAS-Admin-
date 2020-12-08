package com.ezraaudivano.adminkostku.Adapters;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.ezraaudivano.adminkostku.API.KostAPI;
import com.ezraaudivano.adminkostku.Model.User;
import com.ezraaudivano.adminkostku.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import static com.android.volley.Request.Method.GET;

public class AdapterUser extends RecyclerView.Adapter<AdapterUser.adapterUserViewHolder> {
    private List<User> userList;
    private List<User> userListFiltered;

    private Context context;
    private View view;
    private int jumlahKost = 0;
    private String emailFB;

    public AdapterUser(Context context, List<User> userList) {
        this.context=context;
        this.userList = userList;
        this.userListFiltered = userList;
    }
    @NonNull
    @Override
    public adapterUserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        view = layoutInflater.inflate(R.layout.adapter_user, parent, false);

        return new adapterUserViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterUser.adapterUserViewHolder holder, int position) {
        final User user = userListFiltered.get(position);

        holder.txtEmail.setText(user.getEmail());
        holder.txtNama.setText(user.getFullname());
        holder.txtJumlahKost.setText(String.valueOf(user.getJumlah()));

    }

    @Override
    public int getItemCount() {
        return (userListFiltered != null) ? userListFiltered.size() : 0;
    }

    public class adapterUserViewHolder extends RecyclerView.ViewHolder {
        private TextView txtNama, txtEmail, txtJumlahKost;

        public adapterUserViewHolder(@NonNull View itemView) {
            super(itemView);
            txtNama          = (TextView) itemView.findViewById(R.id.txtEmail);
            txtEmail         = (TextView) itemView.findViewById(R.id.txtNama);
            txtJumlahKost        = (TextView) itemView.findViewById(R.id.txtJumlahKost);
        }
    }


}
