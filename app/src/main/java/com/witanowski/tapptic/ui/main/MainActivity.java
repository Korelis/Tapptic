package com.witanowski.tapptic.ui.main;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import com.witanowski.tapptic.R;
import com.witanowski.tapptic.data.model.Number;
import com.witanowski.tapptic.data.remote.APIUtils;
import com.witanowski.tapptic.ui.detailed.DetailedFragment;
import com.witanowski.tapptic.ui.main.adapter.MainAdapter;

import java.util.ArrayList;

/**
 * Created by Michal Witanowski on 2017-11-24.
 */

public class MainActivity extends AppCompatActivity implements MainView {

    private MainPresenter presenter;

    private RecyclerView rvMain;

    private Context context;

    private DetailedFragment detailedFragment;

    private static final String DETAILED_FRAGMENT = "detailed_fragment";

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        if(detailedFragment != null)
            if(detailedFragment.isAdded())
                getFragmentManager().putFragment(outState, DETAILED_FRAGMENT, detailedFragment);
    }

    @Override
    protected void onRestoreInstanceState(Bundle outState) {
        super.onRestoreInstanceState(outState);
        detailedFragment = (DetailedFragment) getFragmentManager()
                .getFragment(outState, DETAILED_FRAGMENT);

        if(detailedFragment != null)
            getFragmentManager().beginTransaction()
                    .replace(R.id.lytDetailed, detailedFragment)
                    .commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.activity_main_actions, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_refresh:
                checkConnection();
                return false;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        context = this;

        rvMain = (RecyclerView) findViewById(R.id.rvMain);
        rvMain.setLayoutManager(new LinearLayoutManager(context));

        presenter = new MainPresenterImpl(this);
        checkConnection();
    }

    @Override
    public void showNumbers(final ArrayList<Number> numbers) {
        runOnUiThread(new Runnable() {
            public void run() {
                MainAdapter adapter = new MainAdapter(numbers, context);
                rvMain.setAdapter(adapter);
            }
        });
    }

    @Override
    public void setFailure() {
        runOnUiThread(new Runnable() {
            public void run() {
                Toast.makeText(context
                        , getString(R.string.couldnt_connect_to_server)
                        , Toast.LENGTH_LONG).show();
            }
        });
    }

    public void getFragment(Number number){
        detailedFragment = new DetailedFragment();
        Bundle bundle = new Bundle();
        bundle.putString("message", number.getName());
        detailedFragment.setArguments(bundle);

        getFragmentManager().popBackStack();
        getFragmentManager().beginTransaction()
                .addToBackStack(DETAILED_FRAGMENT)
                .replace(R.id.lytDetailed, detailedFragment)
                .commit();
    }

    private void checkConnection(){
        if(APIUtils.isNetworkAvailable(context)) {
            presenter.showNumbers();
        }
        else {
            Toast.makeText(context, getString(R.string.no_internet_connection)
                    , Toast.LENGTH_LONG).show();
        }
    }
}
