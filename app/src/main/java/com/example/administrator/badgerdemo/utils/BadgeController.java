package com.example.administrator.badgerdemo.utils;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.os.Bundle;

public final class BadgeController
{
    private static final String[] LAUNCHER_WHITE_LIST;
    private static final String TAG = "BadgeUtils";
    private static Context sApplication;
    private static String sCurBadgeProviderAuthorities;
    private static String sCurLauncherPackageName;
    private static boolean sDebug = false;
    private static volatile Integer sNextCount;
    private static volatile boolean sResumed = true;

    static
    {
        LAUNCHER_WHITE_LIST = new String[] { "com.tct.launcher", "com.bbk.studyos.launcher", "com.android.launcher3", "com.gionee.amisystem" };
    }

    private static String getCurLauncherPackageName()
    {
        try
        {
            Intent localIntent = new Intent("android.intent.action.MAIN");
            localIntent.addCategory("android.intent.category.HOME");
            ResolveInfo localResolveInfo = sApplication.getPackageManager().resolveActivity(localIntent, 0);
            if (localResolveInfo != null)
            {
                if (localResolveInfo.activityInfo == null)
                    return null;
                if (!localResolveInfo.activityInfo.packageName.equals("android"))
                {
                    String str = localResolveInfo.activityInfo.packageName;
                    return str;
                }
            }
        }
        catch (Exception localException)
        {
        }
        return null;
    }

    public static void init(Context paramContext)
    {
        sApplication = paramContext.getApplicationContext();
        String str = getCurLauncherPackageName();
        String[] arrayOfString = LAUNCHER_WHITE_LIST;
        int i = arrayOfString.length;
        int j = 0;
        while (true)
        {
            int k = 0;
            if (j < i)
            {
                if (arrayOfString[j].equalsIgnoreCase(str))
                    k = 1;
            }
            else
            {
                if (sDebug)
                    break ;
                if (k != 0)
                    sCurLauncherPackageName = str;
                if (sCurLauncherPackageName == null);
            }
            try
            {
                ApplicationInfo localApplicationInfo = sApplication.getPackageManager().getApplicationInfo(sCurLauncherPackageName, PackageManager.GET_META_DATA);
                if (localApplicationInfo != null){
                    sCurBadgeProviderAuthorities = localApplicationInfo.metaData.getString("badge_provider");
                    return;
                }
                j++;
                sCurLauncherPackageName = str;
                continue;
            }
            catch (Exception localException)
            {
            }
        }
    }

    public static boolean isSupport(Context paramContext)
    {
        if (sCurLauncherPackageName == null)
            init(paramContext);
        return sCurBadgeProviderAuthorities != null;
    }

    public static void resumeOrPause(boolean paramBoolean)
    {
        sResumed = paramBoolean;
        Integer localInteger = sNextCount;
        if ((sResumed) && (localInteger != null))
            setBadge(localInteger.intValue());
    }

    public static boolean setBadge(int paramInt)
    {
        if (!sResumed)
            sNextCount = Integer.valueOf(paramInt);
        while (sCurBadgeProviderAuthorities == null)
        {
            sNextCount = null;
            return false;

        }
        Uri localUri = Uri.parse("content://" + sCurBadgeProviderAuthorities + "/badge");
        Bundle localBundle1 = new Bundle();
        localBundle1.putInt("count", paramInt);
        Bundle localBundle2 = sApplication.getContentResolver().call(localUri, "setBadge", "", localBundle1);
        boolean bool = false;
        if (localBundle2 != null)
            bool = localBundle2.getBoolean("result");
        return bool;
    }
}
