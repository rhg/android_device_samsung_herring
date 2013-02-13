package com.cyanogenmod.settings.device;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.preference.CheckBoxPreference;
import android.preference.ListPreference;
import android.preference.Preference;
import android.preference.PreferenceActivity;
import android.preference.PreferenceCategory;

public class DeviceSettings extends PreferenceActivity  {

    public static final String KEY_COLOR_TUNING = "color_tuning";
    public static final String KEY_MDNIE = "mdnie";
    public static final String KEY_BACKLIGHT_TIMEOUT = "backlight_timeout";
    public static final String KEY_HSPA = "hspa";
    public static final String KEY_HSPA_CATEGORY = "category_radio";
    private ColorTuningPreference mColorTuning;
    private ListPreference mMdnie;
    private ListPreference mBacklightTimeout;
    private ListPreference mHspa;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.main);

        mColorTuning = (ColorTuningPreference) findPreference(KEY_COLOR_TUNING);
        mColorTuning.setEnabled(ColorTuningPreference.isSupported());

        mMdnie = (ListPreference) findPreference(KEY_MDNIE);
        mMdnie.setEnabled(Mdnie.isSupported());
        mMdnie.setOnPreferenceChangeListener(new Mdnie());

        mBacklightTimeout = (ListPreference) findPreference(KEY_BACKLIGHT_TIMEOUT);
        mBacklightTimeout.setEnabled(TouchKeyBacklightTimeout.isSupported());
        mBacklightTimeout.setOnPreferenceChangeListener(new TouchKeyBacklightTimeout());

        mHspa = (ListPreference) findPreference(KEY_HSPA);
        if (Hspa.isSupported()) {
           mHspa.setOnPreferenceChangeListener(new Hspa(this));
        } else {
           PreferenceCategory category = (PreferenceCategory) getPreferenceScreen().findPreference(KEY_HSPA_CATEGORY);
           category.removePreference(mHspa);
           getPreferenceScreen().removePreference(category);
        }
		
}
}
