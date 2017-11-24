package com.witanowski.tapptic.ui.main;

import com.witanowski.tapptic.data.model.Number;

import java.util.ArrayList;

/**
 * Created by Michal Witanowski on 2017-11-24.
 */

public interface MainInteractor {
    interface OnGetNumbersFinishedListener {
        void onFailure();

        void onSuccess(ArrayList<Number> numbers);
    }

    void getNumbers(OnGetNumbersFinishedListener listener);
}
