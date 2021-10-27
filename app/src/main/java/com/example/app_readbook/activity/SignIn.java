package com.example.app_readbook.activity;

import androidx.appcompat.app.AppCompatActivity;

public class SignIn extends AppCompatActivity {
//ApiInterface apiInterface;
//    @SuppressLint("NonConstantResourceId")
//    @BindView(R.id.user) EditText txt_username;
//    @SuppressLint("NonConstantResourceId")
//    @BindView(R.id.txt_pass) EditText txt_password;
//    private int dpHeight;
//    private int dpWidth;
//    private float dDensity = 375;
//    private int designWidth = 360;
//    private int designHeight  = 715;
//    @Override
//
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.login);
//        requestWindowFeature(Window.FEATURE_NO_TITLE);
//        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN , WindowManager.LayoutParams.FLAG_FULLSCREEN);
//        DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
//        dpHeight = (displayMetrics.heightPixels);
//        dpWidth = (displayMetrics.widthPixels);
//        dDensity = (displayMetrics.scaledDensity);
//        ImageView background = (ImageView) findViewById(R.id.img_1) ;
//        RelativeLayout relativeLayout = (RelativeLayout) findViewById(R.id.rlt);
//        Button signup = (Button) findViewById(R.id.dangky);
//        Button signin = (Button) findViewById(R.id.btn_login);
//        TextView name = (TextView) findViewById(R.id.txt) ;
//        @SuppressLint("WrongViewCast") LinearLayout layout  = (LinearLayout) findViewById(R.id.lo2);
//        @SuppressLint("WrongViewCast") LinearLayout layout1  = (LinearLayout) findViewById(R.id.lo3);
//        ViewGroup.LayoutParams backgroundn = (ViewGroup.MarginLayoutParams) background.getLayoutParams();
//        backgroundn.height = calcHeight(707);
//        relativeLayout.setPadding(0 , calcHeight(20) , 0 , 0);
//        ViewGroup.LayoutParams btn_signup = (ViewGroup.MarginLayoutParams) background.getLayoutParams();
//
//
//        ButterKnife.bind(this);
//        apiInterface = ApiClient.getApiclient().create(ApiInterface.class);
//
//    }
//
//    private int calcHeight(int i) {
//        return (int) (dpHeight*(i/designHeight));
//    }
//
//    public void btn_login(View view)
//    {
//        Call<ApiReponse> apiReponseCall = apiInterface.reponseCallLogin(txt_username.getText().toString() , txt_password.getText().toString());
//        apiReponseCall.enqueue(new Callback<ApiReponse>() {
//            @Override
//            public void onResponse(Call<ApiReponse> call, Response<ApiReponse> response) {
//                    if (response.body()!= null)
//                    {
//                        ApiReponse apiReponse = response.body();
//                        if(apiReponse.isSuccess())
//                        {
//                            Toast.makeText(SignIn.this  , "Login successful" , Toast.LENGTH_LONG).show();
//                            Intent intent = new Intent(SignIn.this , home.class);
//                            startActivity(intent);
//                        }
//                        else{
//                            Toast.makeText(SignIn.this  , "User not found "+apiReponse.getMassage(), Toast.LENGTH_LONG).show();
//                            txt_username.setText("");
//                            txt_password.setText("");
//                        }
//                    }
//            }
//
//            @Override
//            public void onFailure(Call<ApiReponse> call, Throwable t) {
//                Toast.makeText(SignIn.this  , "Error, could not connect " , Toast.LENGTH_LONG).show();
//
//            }
//        });
//    }
//    public void btn_signup(View view)
//    {
//        Intent intent = new Intent(SignIn.this , Register.class);
//        startActivity(intent);
//    }
}