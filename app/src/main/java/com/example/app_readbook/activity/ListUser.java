//package com.example.app_readbook.activity;
//
//import android.annotation.SuppressLint;
//import android.os.Bundle;
//import android.widget.Toast;
//
//import androidx.appcompat.app.AppCompatActivity;
//import androidx.recyclerview.widget.LinearLayoutManager;
//import androidx.recyclerview.widget.RecyclerView;
//
//import com.android.volley.Request;
//import com.android.volley.RequestQueue;
//import com.android.volley.Response;
//import com.android.volley.VolleyError;
//import com.android.volley.toolbox.JsonArrayRequest;
//import com.android.volley.toolbox.Volley;
//import com.example.app_readbook.R;
//
//import org.json.JSONArray;
//import org.json.JSONException;
//import org.json.JSONObject;
//
//import java.util.ArrayList;
//
//public class ListUser extends AppCompatActivity {
//private ArrayList<User> userList;
//private RecyclerView recyclerView;
//private UserAdaptor userAdaptor;
//private static final String  url = "http://192.168.1.6:8888/demo_app/function_user.php";
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_list_user);
//        recyclerView = findViewById(R.id.rcv_user);
//        recyclerView.setHasFixedSize(true);
//        userList = new ArrayList<>();
//        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(ListUser.this);
//        recyclerView.setLayoutManager(linearLayoutManager);
//        loadUser();
//    }
//
//    private void loadUser() {
//        RequestQueue requestQueue = Volley.newRequestQueue(ListUser.this );
////        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
////            @SuppressLint("NotifyDataSetChanged")
////            @Override
////            public void onResponse(String response) {
////                try {
////                    JSONArray internships = new JSONArray(response);
////                    for(int i = 0 ; i < internships.length() ; i++) {
////                        JSONObject c = internships.getJSONObject(i);
////                        int id = c.getInt("ID");
////                        String name = c.getString("username").trim();
////                        String pass = c.getString("password").trim();
////                        String email = c.getString("email").trim();
////                        User user = new User();
////                        user.setID(id);
////                        user.setUsername(name);
////                        user.setPassword(pass);
////                        user.setEmail(email);
////                        userList.add(user);
////                    }
////                } catch (JSONException e) {
////                    e.printStackTrace();
////                }
////                userAdaptor = new UserAdaptor( ListUser.this , userList );
////                recyclerView.setAdapter(userAdaptor);
////                userAdaptor.notifyDataSetChanged();
////            }
////        }, new Response.ErrorListener() {
////            @Override
////            public void onErrorResponse(VolleyError error) {
////                Toast.makeText(ListUser.this, "Error\n"+error.getMessage(), Toast.LENGTH_SHORT).show();
////            }
////        });
//        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null ,  new Response.Listener<JSONArray>() {
//            @SuppressLint("NotifyDataSetChanged")
//            @Override
//            public void onResponse(JSONArray response) {
//                    try {
////                        JSONArray internships = new JSONArray(response);
//                        for(int i = 0 ; i < response.length() ; i++) {
//                            JSONObject c = response.getJSONObject(i);
//                            String id = c.getString("ID");
//                            String name = c.getString("username").trim();
//                            String pass = c.getString("password").trim();
//                            String email = c.getString("email").trim();
//                            User user = new User(id, name , pass, email);
//
//                            userList.add(user);
//                        }
//                    } catch (JSONException e) {
//                        e.printStackTrace();
//                }
//                userAdaptor = new UserAdaptor( ListUser.this , userList );
//                recyclerView.setAdapter(userAdaptor);
//                userAdaptor.notifyDataSetChanged();
//
//            }
//        }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//                Toast.makeText(ListUser.this, "Error\n"+error.toString(), Toast.LENGTH_SHORT).show();
//            }
//        });
//        requestQueue.add(jsonArrayRequest);
//    }
//}