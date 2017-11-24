package com.witanowski.tapptic.ui.detailed;

import com.witanowski.tapptic.data.model.Number;

/**
 * Created by Michal Witanowski on 2017-11-24.
 */

public class DetailedPresenterImpl implements DetailedPresenter
        , DetailedInteractor.OnGetNumbersFinishedListener {

    private DetailedView detailedView;
    private DetailedInteractor detailedInteractor;

    public DetailedPresenterImpl(DetailedView detailedView) {
        this.detailedView = detailedView;
        this.detailedInteractor = new DetailedInteractorImpl();
    }

    @Override
    public void onFailure() {
        if(detailedView != null){
            detailedView.setFailure();
        }
    }

    @Override
    public void onSuccess(Number number) {
        if(detailedView != null){
            detailedView.showNumber(number);
        }
    }

    @Override
    public void showNumber(String param) {
        detailedInteractor.getNumber(this, param);
    }
}
