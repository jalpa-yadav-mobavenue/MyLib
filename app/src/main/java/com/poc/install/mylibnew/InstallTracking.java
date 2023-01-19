package com.poc.install.mylibnew;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.util.Log;
import android.widget.Toast;

public class InstallTracking {
    BroadcastReceiver packageInstallBroadcast = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (!intent.getExtras().containsKey(Intent.EXTRA_REPLACING)) {
                try {
                    Log.e("InstallPackageReceiver", "NAME: " + intent.getData().toString().replace("package:", ""));
                    Toast.makeText(context, "INSTALLED: " + intent.getData().toString().replace("package:", ""), Toast.LENGTH_LONG).show();
                    ;
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    };

    public void registerReceiver(Context context) {
        try {
            IntentFilter intentFilter = new IntentFilter();
            intentFilter.addAction(Intent.ACTION_PACKAGE_ADDED);
            intentFilter.addAction(Intent.ACTION_INSTALL_PACKAGE);
            intentFilter.addCategory(Intent.CATEGORY_DEFAULT);
            intentFilter.addDataScheme("package");
            context.registerReceiver(packageInstallBroadcast, intentFilter);
//            AppLogger.getInstance().e("onCreate=======", "REGISTER");
//            ComponentName receiver = new ComponentName(AppController.getContext(), InstallPackageReceiver.class);
//            PackageManager pm = AppController.getContext().getPackageManager();
//            pm.setComponentEnabledSetting(receiver, PackageManager.COMPONENT_ENABLED_STATE_ENABLED, PackageManager.DONT_KILL_APP);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void unregisterReceiver(Context context) {
        try {
            context.unregisterReceiver(packageInstallBroadcast);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
