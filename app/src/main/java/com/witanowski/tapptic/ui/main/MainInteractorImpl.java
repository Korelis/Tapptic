package com.witanowski.tapptic.ui.main;

import android.util.Log;
import android.widget.Toast;

import com.witanowski.tapptic.BuildConfig;
import com.witanowski.tapptic.R;
import com.witanowski.tapptic.data.model.Number;
import com.witanowski.tapptic.data.remote.APIUtils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Response;

/**
 * Created by Michal Witanowski on 2017-11-24.
 */

public class MainInteractorImpl implements MainInteractor {

    private ArrayList<Number> numbers = new ArrayList<>();

    @Override
    public void getNumbers(final OnGetNumbersFinishedListener listener) {

        OkHttpClient client = new OkHttpClient();

        client.newCall(APIUtils.getRequest(BuildConfig.URL_MASTER)).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                listener.onFailure();
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, final Response response) throws IOException {
                if (!response.isSuccessful()) {
                    listener.onFailure();

                    throw new IOException("Unexpected code " + response);
                } else {
                    try {
                        JSONArray jsonArray = new JSONArray(response.body().string());
                        for (int i = 0; i < jsonArray.length(); i++) {
                            JSONObject obj = jsonArray.getJSONObject(i);
                            Number number = new Number();
                            number.setName(obj.getString("name"));
                            number.setImage(obj.getString("image"));
                            numbers.add(number);
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                    if(numbers.size() > 0)
                        listener.onSuccess(numbers);
                    else
                        listener.onFailure();
                }
            }
        });
    }
}
