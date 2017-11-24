package com.witanowski.tapptic.ui.detailed;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;
import com.witanowski.tapptic.R;
import com.witanowski.tapptic.data.model.Number;
import com.witanowski.tapptic.data.remote.APIUtils;

/**
 * Created by Michal Witanowski on 2017-11-24.
 */

public class DetailedFragment extends Fragment implements DetailedView{

    private DetailedPresenter presenter;

    private TextView tvNumber;
    private ImageView ivNumber;


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_refresh:
                checkConnection();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        final View rootView = inflater.inflate(R.layout.fragment_detailed, container, false);

        tvNumber = (TextView) rootView.findViewById(R.id.tvNumber);
        ivNumber = (ImageView) rootView.findViewById(R.id.ivNumber);

        presenter = new DetailedPresenterImpl(this);

        checkConnection();

        return rootView;
    }

    @Override
    public void showNumber(final Number number) {
        getActivity().runOnUiThread(new Runnable() {
            public void run() {
                tvNumber.setText(number.getText());
                Picasso.with(getActivity()).load(number.getImage()).into(ivNumber);
            }
        });
    }

    @Override
    public void setFailure() {
        getActivity().runOnUiThread(new Runnable() {
            public void run() {
                Toast.makeText(getActivity()
                        , getString(R.string.couldnt_connect_to_server)
                        , Toast.LENGTH_LONG).show();
            }
        });
    }

    private void checkConnection(){
        if(APIUtils.isNetworkAvailable(getActivity())) {
            presenter.showNumber(getArguments().getString("message"));
        }
        else {
            Toast.makeText(getActivity(), getString(R.string.no_internet_connection)
                    , Toast.LENGTH_LONG).show();
        }
    }
}
