package com.witanowski.tapptic.ui.detailed;

import com.witanowski.tapptic.data.model.Number;

/**
 * Created by Michal Witanowski on 2017-11-24.
 */

public interface DetailedInteractor {
    interface OnGetNumbersFinishedListener {
        void onFailure();

        void onSuccess(Number numbers);
    }

    void getNumber(DetailedInteractor.OnGetNumbersFinishedListener listener, String param);
}
