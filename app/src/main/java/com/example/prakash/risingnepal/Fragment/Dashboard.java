package com.example.prakash.risingnepal.Fragment;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.androidadvance.topsnackbar.TSnackbar;
import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.TextSliderView;
import com.example.prakash.risingnepal.Data.Channel;
import com.example.prakash.risingnepal.Data.Item;
import com.example.prakash.risingnepal.Pojo.Imageslider_data;
import com.example.prakash.risingnepal.R;
import com.example.prakash.risingnepal.service.WeatherServiceCallback;
import com.example.prakash.risingnepal.service.YahooWeatherService;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Method;
import java.util.ArrayList;

public class Dashboard extends Fragment implements WeatherServiceCallback {
    View mainView;
    SliderLayout sliderLayout;
    LinearLayout lineartop;
    ImageView webhome, destination, companyinfo, activities, tour, trekking, holyday, contactus;
    TextView Tnepal, Tdestination, Tactivity, Ttour, Ttrekking, Tholyday, Tcontact, Tcompanyinfo;
    Typeface typeface;
    TextView temperature;
    TextView condition;
    TextView weather_location;
    LinearLayout weather;
    YahooWeatherService service;
    ArrayList<Imageslider_data> data;



    String url = "https://rising.nuzatech.com/slider";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        mainView = inflater.inflate(R.layout.dashboard_layout, container, false);
        sliderLayout = (SliderLayout) mainView.findViewById(R.id.dashboard_slider);

        lineartop = (LinearLayout) mainView.findViewById(R.id.linearapt);

        webhome = (ImageView) mainView.findViewById(R.id.web_home);
        destination = (ImageView) mainView.findViewById(R.id.web_destination);
        companyinfo = (ImageView) mainView.findViewById(R.id.web_companyinfo);
        activities = (ImageView) mainView.findViewById(R.id.web_activities);
        tour = (ImageView) mainView.findViewById(R.id.web_tour);
        trekking = (ImageView) mainView.findViewById(R.id.web_trekking);
        holyday = (ImageView) mainView.findViewById(R.id.web_holyday);
        contactus = (ImageView) mainView.findViewById(R.id.web_contact);


        temperature = (TextView) mainView.findViewById(R.id.degree_id);
        condition = (TextView) mainView.findViewById(R.id.condition_id);
        weather_location = (TextView) mainView.findViewById(R.id.place_id);
        weather = (LinearLayout) mainView.findViewById(R.id.linear_weather);




        Tnepal = (TextView) mainView.findViewById(R.id.txt_nepal);
        Tdestination = (TextView) mainView.findViewById(R.id.txt_destination);
        Tcompanyinfo = (TextView) mainView.findViewById(R.id.txt_companyinfo);
        Tactivity = (TextView) mainView.findViewById(R.id.txt_activities);
        Ttrekking = (TextView) mainView.findViewById(R.id.txt_trekking);
        Ttour = (TextView) mainView.findViewById(R.id.txt_tour);
        Tholyday = (TextView) mainView.findViewById(R.id.txt_holyday);
        Tcontact = (TextView) mainView.findViewById(R.id.txt_contactus);




        typeface = Typeface.createFromAsset(getContext().getAssets(), "fonts/Oswald-Regular.ttf");
        Tnepal.setTypeface(typeface);
        Tdestination.setTypeface(typeface);
        Tcompanyinfo.setTypeface(typeface);
        Tactivity.setTypeface(typeface);
        Ttour.setTypeface(typeface);
        Ttrekking.setTypeface(typeface);
        Tholyday.setTypeface(typeface);
        Tcontact.setTypeface(typeface);
        temperature.setTypeface(typeface);
        condition.setTypeface(typeface);
        weather_location.setTypeface(typeface);


        service = new YahooWeatherService((WeatherServiceCallback) this);


        if (!isOnline()) {


            TSnackbar snackbar = TSnackbar.make(lineartop, "No Internet connection", TSnackbar.LENGTH_LONG);
            snackbar.setActionTextColor(Color.WHITE);
            View snackbarView = snackbar.getView();
            snackbarView.setBackgroundColor(Color.parseColor("#FF0000"));
            TextView textView = (TextView) snackbarView.findViewById(com.androidadvance.topsnackbar.R.id.snackbar_text);
            textView.setTextColor(Color.WHITE);
            snackbar.show();


        }


        webhome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                FragmentTransaction transaction = getFragmentManager().beginTransaction();

                Bundle bundle = new Bundle();
                String myMessage = "https://rising.nuzatech.com/destination/nepal";
                bundle.putString("message", myMessage);
                String title = "Nepal";
                bundle.putString("message", myMessage);
                bundle.putString("title", title);

                Webview_activity web = new Webview_activity();
                web.setArguments(bundle);
                transaction.replace(R.id.mainFragment, web);
                transaction.addToBackStack("apartment");
                transaction.commit();


            }
        });
        destination.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentTransaction transaction = getFragmentManager().beginTransaction();

                Bundle bundle = new Bundle();
                String myMessage = "https://rising.nuzatech.com/destination";
                String title = "Destination";
                bundle.putString("message", myMessage);
                bundle.putString("title", title);
                Webview_activity web = new Webview_activity();
                web.setArguments(bundle);
                transaction.replace(R.id.mainFragment, web);
                transaction.addToBackStack("home page");
                transaction.commit();


            }
        });


        companyinfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentTransaction transaction = getFragmentManager().beginTransaction();

                Bundle bundle = new Bundle();
                String myMessage = "https://rising.nuzatech.com/company-info";
                String title = "Company  info";
                bundle.putString("message", myMessage);
                bundle.putString("title", title);
                Webview_activity web = new Webview_activity();
                web.setArguments(bundle);
                transaction.replace(R.id.mainFragment, web);
                transaction.addToBackStack("home page");
                transaction.commit();


            }
        });


        activities.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentTransaction transaction = getFragmentManager().beginTransaction();

                Bundle bundle = new Bundle();
                String myMessage = "https://rising.nuzatech.com/activities";
                String title = "Activities";
                bundle.putString("message", myMessage);
                bundle.putString("title", title);
                Webview_activity web = new Webview_activity();
                web.setArguments(bundle);
                transaction.replace(R.id.mainFragment, web);
                transaction.addToBackStack("home page");
                transaction.commit();


            }
        });


        trekking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentTransaction transaction = getFragmentManager().beginTransaction();

                Bundle bundle = new Bundle();
                String myMessage = "https://rising.nuzatech.com/trekking";
                String title = "Trekking";
                bundle.putString("message", myMessage);
                bundle.putString("title", title);
                Webview_activity web = new Webview_activity();
                web.setArguments(bundle);
                transaction.replace(R.id.mainFragment, web);
                transaction.addToBackStack("home page");
                transaction.commit();


            }
        });


        tour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentTransaction transaction = getFragmentManager().beginTransaction();

                Bundle bundle = new Bundle();
                String myMessage = "https://rising.nuzatech.com/tours";
                String title = "tour";
                bundle.putString("message", myMessage);
                bundle.putString("title", title);
                Webview_activity web = new Webview_activity();
                web.setArguments(bundle);
                transaction.replace(R.id.mainFragment, web);
                transaction.addToBackStack("home page");
                transaction.commit();


            }
        });


        holyday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentTransaction transaction = getFragmentManager().beginTransaction();

                Bundle bundle = new Bundle();
                String myMessage = "https://rising.nuzatech.com/holiday-special";
                String title = "Holiday special";
                bundle.putString("message", myMessage);
                bundle.putString("title", title);
                Webview_activity web = new Webview_activity();
                web.setArguments(bundle);
                transaction.replace(R.id.mainFragment, web);
                transaction.addToBackStack("home page");
                transaction.commit();


            }
        });

        contactus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentTransaction transaction = getFragmentManager().beginTransaction();

                Bundle bundle = new Bundle();
                String myMessage = "https://rising.nuzatech.com/contact";
                String title = "Contact us";
                bundle.putString("message", myMessage);
                bundle.putString("title", title);
                Webview_activity web = new Webview_activity();
                web.setArguments(bundle);
                transaction.replace(R.id.mainFragment, web);
                transaction.addToBackStack("home page");
                transaction.commit();


            }
        });


        if (!isOnline()) {


            TSnackbar snackbar = TSnackbar.make(lineartop, "No Internet connection", TSnackbar.LENGTH_LONG);
            snackbar.setActionTextColor(Color.WHITE);
            View snackbarView = snackbar.getView();
            snackbarView.setBackgroundColor(Color.parseColor("#FF0000"));
            TextView textView = (TextView) snackbarView.findViewById(com.androidadvance.topsnackbar.R.id.snackbar_text);
            textView.setTextColor(Color.WHITE);
            snackbar.show();


        } else {
            service.refreshWeather("Kathmandu");

        }


        getdata();


        return mainView;
    }

    void getdata() {
        final JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {


                Toast.makeText(getContext(), "You are in the right place", Toast.LENGTH_SHORT).show();

                data = new ArrayList<>();

                for (int i = 0; i < response.length(); i++) {
                    try {
                        JSONObject object = response.getJSONObject(i);

                        Imageslider_data slider = new Imageslider_data();


                        String title = object.getString("title");
                        String image = object.getString("field_slider_image");
                        String nid = object.getString("nid");


                        slider.setId(nid);
                        slider.setTitle(title);
                        slider.setImage(image);

                        data.add(slider);


                        final TextSliderView textSliderView = new TextSliderView(getContext());
                        textSliderView
                                .description(title)
                                .image("https://rising.nuzatech.com/" + image);


                        sliderLayout.setPresetTransformer(SliderLayout.Transformer.Fade);


                        sliderLayout.addSlider(textSliderView);


                    } catch (JSONException e) {
                        e.printStackTrace();
                        Toast.makeText(getContext(), "" + e, Toast.LENGTH_SHORT).show();
                    }


                }

            }
        },
                new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                if (isOnline()) {
                    Toast.makeText(getContext(), "Server error", Toast.LENGTH_SHORT).show();
                }

            }
        });


        request.setRetryPolicy(new DefaultRetryPolicy(
                5000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));

        RequestQueue queue = Volley.newRequestQueue(getContext());
        queue.add(request);

    }


    protected boolean isOnline() {
        ConnectivityManager cm = (ConnectivityManager) getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        if (netInfo != null && netInfo.isConnected()) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public void serviceSuccess(Channel channel) {
        Item item = channel.getItem();

        weather_location.setText(service.getLocation());
        condition.setText(item.getCondition().getDescription() + ",");
        temperature.setText(item.getCondition().getTemperature() + "\u00B0 " + channel.getUnits().getTemperature());
    }

    @Override
    public void serviceFalure(Exception exception) {
        weather.setVisibility(View.GONE);

    }
}
