package com.witanowski.tapptic.ui.main;


import com.witanowski.tapptic.data.model.Number;

import java.util.ArrayList;

/**
 * Created by Michal Witanowski on 2017-11-24.
 */

public class MainPresenterImpl implements MainPresenter, MainInteractor.OnGetNumbersFinishedListener {

    private MainView mainView;
    private MainInteractor mainInteractor;

    public MainPresenterImpl(MainView mainView){
        this.mainView = mainView;
        this.mainInteractor = new MainInteractorImpl();
    }

    @Override
    public void showNumbers() {
        mainInteractor.getNumbers(this);
    }

    @Override
    public void onFailure() {
        if(mainView != null){
            mainView.setFailure();
        }
    }

    @Override
    public void onSuccess(ArrayList<Number> numbers) {
        if(mainView != null){
            mainView.showNumbers(numbers);
        }
    }
}
