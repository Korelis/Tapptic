package com.witanowski.tapptic.ui.detailed;

import com.witanowski.tapptic.BuildConfig;
import com.witanowski.tapptic.data.model.Number;
import com.witanowski.tapptic.data.remote.APIUtils;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by Michal Witanowski on 2017-11-24.
 */

public class DetailedInteractorImpl implements DetailedInteractor {

    private Number number;

    @Override
    public void getNumber(final DetailedInteractor.OnGetNumbersFinishedListener listener
            , String param) {
        OkHttpClient client = new OkHttpClient();

        Request request = APIUtils.getRequest(BuildConfig.URL_DETAILED + param);

        client.newCall(request).enqueue(new Callback() {
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
                        final JSONObject jsonObject = new JSONObject(response.body().string());
                        number = new Number();
                        number.setText(jsonObject.getString("text"));
                        number.setImage(jsonObject.getString("image"));
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                    if(number != null)
                        listener.onSuccess(number);
                    else
                        listener.onFailure();
                }
            }
        });
    }

}
