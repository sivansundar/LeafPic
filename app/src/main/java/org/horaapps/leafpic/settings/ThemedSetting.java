package org.horaapps.leafpic.settings;

import org.horaapps.liz.Theme;
import org.horaapps.liz.ThemedActivity;

/**
 * Created by dnld on 12/9/16.
 */

public class ThemedSetting {

    private ThemedActivity activity;

    public ThemedSetting(ThemedActivity activity) {
        this.activity = activity;
    }

    public ThemedSetting() {

    }


    public ThemedActivity getActivity() {
        return activity;
    }

}
