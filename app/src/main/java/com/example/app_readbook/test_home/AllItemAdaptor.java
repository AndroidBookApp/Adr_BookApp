package com.example.app_readbook.test_home;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.app_readbook.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class AllItemAdaptor extends RecyclerView.Adapter<AllItemAdaptor.AllViewHolder>{
    public static final String url = "http://192.168.1.6:8888/demo_app/sach.php";
private List<book_item> bookItemList;
private List<name_item> nameItemList;
private List<all> allItemList;
public static final int TYPE_NAME =1;
public static final int TYPE_BOOK =2;
public OnClickListener iOnClick;
    public interface OnClickListener{
        void IClick(int position);
    }
private Context mContext;
    public void setOnItemClickListener(OnClickListener listener)
    {
        iOnClick =  listener;
    }
    @Override
    public int getItemViewType(int position) {
        return allItemList.get(position).getType();
    }
    public void setData( Context context,List<all> all_items)
{
    this.mContext = context;
    this.allItemList = all_items;
    notifyDataSetChanged();
}
    @NonNull
    @Override
    public AllViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.test_adaptor_main , parent , false);
        return new AllViewHolder(view , iOnClick);
    }


    @Override
    public void onBindViewHolder(@NonNull AllViewHolder holder, int position) {
        all all_item = allItemList.get(position);
        if(all_item == null)
        {
            return;
        }
        if(TYPE_NAME == holder.getItemViewType())
        {
            nameItemList = new ArrayList<>();
            RequestQueue requestQueue = Volley.newRequestQueue(mContext);
            JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.POST, url, null, new Response.Listener<JSONArray>() {
                @Override
                public void onResponse(JSONArray response) {
                    try {
                        for(int i = 0 ; i < response.length() ; i++) {
                            JSONObject jsonObject = response.getJSONObject(i);
//                            list_book listBook = new list_book(jsonObject.getString("idSach") , jsonObject.getString("Tensach") ,
//                                    jsonObject.getString("idDanhmuc") , jsonObject.getString("Tendanhmuc") ,
//                                    jsonObject.getString("Tentacgia") ,jsonObject.getInt("NgayXB") ,jsonObject.getString("TomtatND") ,
//                                    jsonObject.getString("IMGsach")
//                                    , jsonObject.getString("sotrang") , jsonObject.getString("Luotxem")
//                                    ,  jsonObject.getString("slfeedback"), jsonObject.getString("slyeuthich"));
//                            mList.add(listBook);
                            name_item nameItem = new name_item(jsonObject.getString("idDanhmuc") , jsonObject.getString("Tendanhmuc"), bookItemList);
                            nameItemList.add(nameItem);
                        }

                    } catch (JSONException e) {
                        e.printStackTrace();
                        Toast.makeText(mContext, "Lỗi gì đó", Toast.LENGTH_SHORT).show();
                    }
                    LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mContext , LinearLayoutManager.VERTICAL , false);
                    holder.recyclerView.setLayoutManager(linearLayoutManager);
                    holder.recyclerView.setFocusable(false);
                    NameAdaptor nameAdaptor = new NameAdaptor();
                    nameAdaptor.setData(all_item.getName_items());
                    holder.recyclerView.setAdapter(nameAdaptor);
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(mContext, "Lỗi gì đó"+error.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
            requestQueue.add(jsonArrayRequest);

        }else if(TYPE_BOOK == holder.getItemViewType())
        {
            bookItemList = new ArrayList<>();
            RequestQueue requestQueue = Volley.newRequestQueue(mContext);
            JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.POST, url, null, new Response.Listener<JSONArray>() {
                @Override
                public void onResponse(JSONArray response) {
                    try {
                        for(int i = 0 ; i < response.length() ; i++) {
                            JSONObject jsonObject = response.getJSONObject(i);
                            book_item book_item = new book_item(jsonObject.getString("idSach") , jsonObject.getString("Tensach")
                                  , jsonObject.getString("Tentacgia") ,jsonObject.getInt("NgayXB") ,jsonObject.getString("TomtatND") ,
                                    jsonObject.getString("IMGsach")
                                    , jsonObject.getString("sotrang") , jsonObject.getString("Luotxem")
                                    ,  jsonObject.getString("slfeedback"), jsonObject.getString("slyeuthich"));
                            bookItemList.add(book_item);
                        }

                    } catch (JSONException e) {
                        e.printStackTrace();
                        Toast.makeText(mContext, "Lỗi gì đó", Toast.LENGTH_SHORT).show();
                    }
                    LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mContext , LinearLayoutManager.HORIZONTAL , false);
                    holder.recyclerView.setLayoutManager(linearLayoutManager);
                    holder.recyclerView.setFocusable(false);
                    BookAdaptor bookAdaptor = new BookAdaptor();
                    bookAdaptor.setData(all_item.getBookItemList());
                    holder.recyclerView.setAdapter(bookAdaptor);
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(mContext, "Lỗi gì đó"+error.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
            requestQueue.add(jsonArrayRequest);

    }
        }

    private void LoadBooK() {

    }

    private void LoadName() {

    }

    @Override
    public int getItemCount() {
        if (allItemList != null)
        {
            return allItemList.size();
        }
        return 0;
    }

    public static class AllViewHolder extends RecyclerView.ViewHolder{
  private RecyclerView recyclerView;
        public AllViewHolder(@NonNull View itemView , OnClickListener iOnClick) {
            super(itemView);
            recyclerView = itemView.findViewById(R.id.ryc_viewAdaptor_main);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (iOnClick != null) {
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            iOnClick.IClick(position);
                        }
                    }
                }

            });
        }
    }
}
