package id.delta.iconlauncher;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Parcelable;
import android.preference.PreferenceManager;

/**
 * Created by Administrator on 6/24/16.
 */
public class Utils {

    private Utils (){}

    public static void setIcon (Activity activity){
        Context context = activity;
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        int n = Integer.parseInt((String) preferences.getString("key_pref_icons", "0"));
        if (n == 0) {
            activity.getPackageManager().setComponentEnabledSetting(
                    new ComponentName("id.delta.iconlauncher", "id.delta.iconlauncher.MainActivity-Red"),
                    PackageManager.COMPONENT_ENABLED_STATE_ENABLED, PackageManager.DONT_KILL_APP);
            Utils.addShortcut(context);

            try {
                activity.getPackageManager().setComponentEnabledSetting(
                        new ComponentName("id.delta.iconlauncher", "id.delta.iconlauncher.MainActivity-Green"),
                        PackageManager.COMPONENT_ENABLED_STATE_DISABLED, PackageManager.DONT_KILL_APP);
                activity.getPackageManager().setComponentEnabledSetting(
                        new ComponentName("id.delta.iconlauncher", "id.delta.iconlauncher.MainActivity-Blue"),
                        PackageManager.COMPONENT_ENABLED_STATE_DISABLED, PackageManager.DONT_KILL_APP);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        if (n == 1){
            activity.getPackageManager().setComponentEnabledSetting(
                    new ComponentName("id.delta.iconlauncher","id.delta.iconlauncher.MainActivity-Green"),
                    PackageManager.COMPONENT_ENABLED_STATE_ENABLED,PackageManager.DONT_KILL_APP);

            Utils.addShortcut(context);

            try {
                activity.getPackageManager().setComponentEnabledSetting(
                        new ComponentName("id.delta.iconlauncher","id.delta.iconlauncher.MainActivity-Red"),
                        PackageManager.COMPONENT_ENABLED_STATE_DISABLED,PackageManager.DONT_KILL_APP
                );
                activity.getPackageManager().setComponentEnabledSetting(
                        new ComponentName("id.delta.iconlauncher","id.delta.iconlauncher.MainActivity-Blue"),
                        PackageManager.COMPONENT_ENABLED_STATE_DISABLED, PackageManager.DONT_KILL_APP
                );
            }catch (Exception e){
                e.printStackTrace();
            }
        }

        if (n == 2){
            activity.getPackageManager().setComponentEnabledSetting(
                    new ComponentName("id.delta.iconlauncher", "id.delta.iconlauncher.MainActivity-Blue"),
                    PackageManager.COMPONENT_ENABLED_STATE_ENABLED,PackageManager.DONT_KILL_APP
            );

            Utils.addShortcut(context);

            try {
                activity.getPackageManager().setComponentEnabledSetting(
                        new ComponentName("id.delta.iconlauncher","id.delta.iconlauncher.MainActivity-Red"),
                        PackageManager.COMPONENT_ENABLED_STATE_DISABLED,PackageManager.DONT_KILL_APP
                );
                activity.getPackageManager().setComponentEnabledSetting(
                        new ComponentName("id.delta.iconlauncher","id.delta.iconlauncher.MainActivity-Green"),
                        PackageManager.COMPONENT_ENABLED_STATE_DISABLED, PackageManager.DONT_KILL_APP
                );
            } catch (Exception e){
                e.printStackTrace();
            }
        }
    }


    private static void addShortcut(Context context) {
        Intent shortcutIntent = new Intent();
        shortcutIntent.setAction(Intent.ACTION_MAIN);
        shortcutIntent.addCategory(Intent.CATEGORY_LAUNCHER);
        shortcutIntent.setClassName(context, "id.delta.iconlauncher.MainActivity-Green");
        shortcutIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TOP);

        Parcelable iconResource = Intent.ShortcutIconResource.fromContext(context,  R.mipmap.ic_launcher);

        Intent intent = new Intent();
        intent.putExtra(Intent.EXTRA_SHORTCUT_INTENT, shortcutIntent);
        intent.putExtra(Intent.EXTRA_SHORTCUT_NAME, context.getResources().getString(R.string.app_name));
        intent.putExtra(Intent.EXTRA_SHORTCUT_ICON_RESOURCE,iconResource);
        intent.putExtra("duplicate", false);
        intent.setAction("com.android.launcher.action.INSTALL_SHORTCUT");
        context.sendBroadcast(intent);
    }


}
