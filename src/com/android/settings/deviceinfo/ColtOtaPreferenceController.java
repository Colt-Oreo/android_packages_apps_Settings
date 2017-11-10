/*
 * Copyright (C) 2017 ColtOS Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.android.settings.deviceinfo;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.SystemProperties;
import android.os.PersistableBundle;
import android.os.UserManager;
import android.support.v7.preference.Preference;
import android.support.v7.preference.PreferenceScreen;
import android.telephony.CarrierConfigManager;
import android.text.TextUtils;
import android.util.Log;

import com.android.settings.R;
import com.colt.settings.utils.Utils;
import com.android.settings.core.PreferenceController;

public class ColtOtaPreferenceController extends PreferenceController {

    private static final String TAG = "ColtOtaPrefContr";

    private static final String KEY_COLT_OTA = "colt_ota";
    private static final String KEY_COLTOTA_PACKAGE_NAME = "org.coltos.ota";

    private final UserManager mUm;

    public ColtOtaPreferenceController(Context context, UserManager um) {
        super(context);
        mUm = um;
    }

    @Override
    public boolean isAvailable() {
     String buildtype = SystemProperties.get("ro.colt.releasetype","unofficial");
     if (Utils.isPackageInstalled(mContext, KEY_COLTOTA_PACKAGE_NAME) && buildtype.equalsIgnoreCase("official")) {

        return mUm.isAdminUser();

    } else {

        return false;

    }
    }

    @Override
    public String getPreferenceKey() {
        return KEY_COLT_OTA;
    }

    @Override
    public void displayPreference(PreferenceScreen screen) {
        if (!isAvailable()) {
            removePreference(screen, KEY_COLT_OTA);
        }
    }


}
