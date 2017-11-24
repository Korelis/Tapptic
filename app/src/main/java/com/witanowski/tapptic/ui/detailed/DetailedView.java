package com.witanowski.tapptic.ui.detailed;

import com.witanowski.tapptic.data.model.Number;

/**
 * Created by Michal Witanowski on 2017-11-24.
 */

public interface DetailedView {

    void showNumber(Number number);

    void setFailure();
}
