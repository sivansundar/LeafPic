package org.horaapps.leafpic.activities;

import android.annotation.TargetApi;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.CallSuper;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ScrollView;
import android.widget.TextView;

import com.mikepenz.google_material_typeface_library.GoogleMaterial;

import org.horaapps.leafpic.R;
import org.horaapps.leafpic.util.ChromeCustomTabs;
import org.horaapps.leafpic.util.StringUtils;
import org.horaapps.liz.ColorPalette;
import org.horaapps.liz.ThemedActivity;
import org.horaapps.liz.ui.ThemedIcon;

/**
 * Created by Jibo on 02/03/2016.
 */
public class DonateActivity extends ThemedActivity {

    private Toolbar toolbar;
    private ChromeCustomTabs cts;
    private ScrollView scr;
    private Button btnDonatePP;
    private Button btnDonateGpay;

    public static void startActivity(@NonNull Context context) {
        context.startActivity(new Intent(context, DonateActivity.class));
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);


    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        setContentView(R.layout.activity_donate);
        toolbar = findViewById(R.id.toolbar);
        // btnDonateGpay = findViewById(R.id.button_donate_play_store);
        scr = findViewById(R.id.donateAct_scrollView);
        btnDonatePP = findViewById(R.id.button_donate_paypal);
        btnDonateGpay = findViewById(R.id.button_donate_play_store);


        iniUi();
        cts = new ChromeCustomTabs(DonateActivity.this);
    }

    @Override
    public void onDestroy() {
        cts.destroy();
        super.onDestroy();
    }

    private void iniUi() {
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(getToolbarIcon(GoogleMaterial.Icon.gmd_arrow_back));
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        btnDonatePP.setText(getString(R.string.donate));
        btnDonatePP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cts.launchUrl("https://www.paypal.me/HoraApps");
            }
        });

        btnDonateGpay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cts.launchUrl("https://liberapay.com/cro/donate");
            }
        });
        findViewById(R.id.donate_bitcoin_item).setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                ClipboardManager clipboard = (ClipboardManager) getSystemService(CLIPBOARD_SERVICE);
                ClipData clip = ClipData.newPlainText("HoraApps BTC", ((TextView) v).getText());
                clipboard.setPrimaryClip(clip);
                StringUtils.showToast(getApplicationContext(), getString(R.string.address_copied));
                return true;
            }
        });
    }

    @CallSuper
    @Override
    public void updateUiElements() {
        super.updateUiElements();
        toolbar.setBackgroundColor(getResources().getColor(R.color.md_black_1000));
        setStatusBarColor();
        setNavBarColor();
        setRecentApp(getString(R.string.donate));

        ((TextView) findViewById(R.id.team_name)).setTextColor(getAccentColor());
        ((TextView) findViewById(R.id.donate_liberapay_item_title)).setTextColor(getAccentColor());
        ((TextView) findViewById(R.id.donate_paypal_item_title)).setTextColor(getAccentColor());
        ((TextView) findViewById(R.id.donate_bitcoin_item_title)).setTextColor(getAccentColor());

        findViewById(R.id.donate_background).setBackgroundColor(getResources().getColor(R.color.md_black_1000));

        int color = getCardBackgroundColor();
        int backgroundColor = getResources().getColor(R.color.md_black_1000);

        ((CardView) findViewById(R.id.donate_header_card)).setCardBackgroundColor(backgroundColor);
        ((CardView) findViewById(R.id.donate_paypal_card)).setCardBackgroundColor(backgroundColor);
        ((CardView) findViewById(R.id.donate_bitcoin_card)).setCardBackgroundColor(backgroundColor);
        ((CardView) findViewById(R.id.donate_googleplay_card)).setCardBackgroundColor(backgroundColor);

        color = getIconColor();
        ((ThemedIcon) findViewById(R.id.donate_paypal_icon_title)).setColor(color);
        ((ThemedIcon) findViewById(R.id.donate_bitcoin_icon_title)).setColor(color);
        ((ThemedIcon) findViewById(R.id.donate_header_icon)).setColor(color);

        color = getResources().getColor(R.color.md_white_1000);
        ((TextView) findViewById(R.id.donate_paypal_item)).setTextColor(color);
        ((TextView) findViewById(R.id.donate_liberapay_item)).setTextColor(color);
        ((TextView) findViewById(R.id.donate_bitcoin_item)).setTextColor(color);
        ((TextView) findViewById(R.id.donate_header_item)).setTextColor(color);

        setScrollViewColor(scr);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    protected void setStatusBarColor() {

        getWindow().setStatusBarColor(ColorPalette.getObscuredColor(getResources().getColor(R.color.md_black_1000)));

    }
}