package com.witanowski.tapptic.ui.main;

import com.witanowski.tapptic.data.model.Number;

import java.util.ArrayList;

/**
 * Created by Michal Witanowski on 2017-11-24.
 */

public interface MainView {
    void showNumbers(ArrayList<Number> numbers);

    void setFailure();
}
