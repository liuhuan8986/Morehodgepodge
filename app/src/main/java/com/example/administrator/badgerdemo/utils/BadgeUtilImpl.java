/*
package com.example.administrator.badgerdemo.utils;

import android.app.Notification;
import android.content.Context;

public class BadgeUtilImpl
{
    public static final String QQSETTING_SHOW_BADGEUNREAD_KEY = "qqsetting_show_badgeunread_key";
    public static final String TAG = "BadgeUtilImpl";

    public static void changeMI6Badge(Context paramContext, int paramInt, Notification paramNotification)
    {
        if (!isEnabled(paramContext))
        {
           */
/* if (QLog.isColorLevel())
                QLog.d("BadgeUtilImpl", 2, "setBadge isdisableabled count=0");*//*

            paramInt = 0;
        }
        CommonBadgeUtilImpl.changeMI6Badge(paramContext, paramInt, paramNotification);
    }
*/
/**//*

    public static void disableBadge(Context paramContext)
    {
        if (isSupportBadge(paramContext))
        {
            setBadge(paramContext, 0, true);
            SettingCloneUtil.writeValue(paramContext, null, null, "qqsetting_show_badgeunread_key", false);
        }
    }

    public static void enableBadge(Context paramContext)
    {
        if (isSupportBadge(paramContext))
            SettingCloneUtil.writeValue(paramContext, null, null, "qqsetting_show_badgeunread_key", true);
    }

    public static boolean isEnabled(Context paramContext)
    {
        boolean bool1 = isSupportBadge(paramContext);
        boolean bool2 = false;
        if (bool1)
            bool2 = SettingCloneUtil.readValue(paramContext, null, null, "qqsetting_show_badgeunread_key", true);
        return bool2;
    }

    public static boolean isSupportBadge(Context paramContext)
    {
        return CommonBadgeUtilImpl.isSupportBadge(paramContext);
    }

    public static void setBadge(Context paramContext, int paramInt)
    {
        setBadge(paramContext, paramInt, false);
    }

    public static void setBadge(Context paramContext, int paramInt, boolean paramBoolean)
    {
        if (QLog.isColorLevel())
            QLog.d("BadgeUtilImpl", 2, "setBadge count=" + paramInt + "|forceSet=" + paramBoolean);
        if ((!isEnabled(paramContext)) && (!paramBoolean))
            return;
        if (!isEnabled(paramContext))
        {
            if (QLog.isColorLevel())
                QLog.d("BadgeUtilImpl", 2, "setBadge isdisableabled count=0");
            paramInt = 0;
        }
        BadgeController.setBadge(paramInt);
        CommonBadgeUtilImpl.setBadge(paramContext, paramInt, paramBoolean);
    }

    public static void setLimitCount(int paramInt)
    {
        CommonBadgeUtilImpl.setLimitCount(paramInt);
    }

    public static void setMIUI6Badge(Context paramContext, int paramInt, Notification paramNotification)
    {
        CommonBadgeUtilImpl.setMIUI6Badge(paramContext, paramInt, paramNotification);
    }
}*/
