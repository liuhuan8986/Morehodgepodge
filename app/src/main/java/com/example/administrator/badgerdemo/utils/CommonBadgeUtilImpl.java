/*
package com.example.administrator.badgerdemo.utils;

import android.app.Notification;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;

import java.lang.reflect.Field;
import java.util.Iterator;

public class CommonBadgeUtilImpl {
    public static final String ACTION_APPLICATION_MESSAGE_QUERY = "android.intent.action.APPLICATION_MESSAGE_QUERY";
    public static final String ACTION_APPLICATION_MESSAGE_UPDATE = "android.intent.action.APPLICATION_MESSAGE_UPDATE";
    public static final String ACTION_QQLAUNCHER_BADGE_UPDATE = "com.tencent.qlauncher.action.ACTION_UPDATE_SHORTCUT";
    public static final String EXTRA_UPDATE_APPLICATION_COMPONENT_NAME = "android.intent.extra.update_application_component_name";
    public static final String EXTRA_UPDATE_APPLICATION_MESSAGE_TEXT = "android.intent.extra.update_application_message_text";
    public static final String LENOVO_PACKAGE_NAME = "com.lenovo.launcher";
    public static final String MANUFACTURER_OF_HARDWARE_HUAWEI = "huawei";
    public static final String MANUFACTURER_OF_HARDWARE_LENOVO = "lenovo";
    public static final String MANUFACTURER_OF_HARDWARE_OPPO = "OPPO";
    public static final String MANUFACTURER_OF_HARDWARE_SANXING = "samsung";
    public static final String MANUFACTURER_OF_HARDWARE_SONY = "Sony Ericsson";
    public static final String MANUFACTURER_OF_HARDWARE_VIVO = "vivo";
    public static final String MANUFACTURER_OF_HARDWARE_XIAOMI = "Xiaomi";
    public static final String MANUFACTURER_OF_HARDWARE_ZUK = "ZUK";
    public static final String MANUFACTURER_OF_LENOVO_LAUNCHER_BADGE = "content://com.lenovo.launcher.badge/lenovo_badges";
    public static int NOTIFICATION_ID_NOTIFYID = 0;
    private static final String OPPO_QQ_BADGE_NUMBER = "com.tencent.mobileqq.badge";
    public static final String TAG = "CommonBadgeUtilImpl";
    public static int haslenovoLanucher;
    private static Boolean haveprovider = null;
    public static String mLauncherClassName;
    public static int mLimitCount;
    private static int miui6Flag;
    public static PackageManager packmag;
    private static int sBadgeAbility;
    private static Context sContext;
    public static String[] sQQLuancherPackageNames;

    static
    {
        NOTIFICATION_ID_NOTIFYID = 110234;
        sQQLuancherPackageNames = new String[] { "com.tencent.qlauncher.lite", "com.tencent.qlauncher", "com.tencent.qqlauncher", "com.tencent.launcher" };
        mLauncherClassName = "";
        haslenovoLanucher = -1;
        mLimitCount = -1;
        sBadgeAbility = 0;
        miui6Flag = 0;
    }

    public static void changeMI6Badge(Context paramContext, int paramInt, Notification paramNotification)
    {
        if (paramInt > mLimitCount)
            paramInt = mLimitCount;
        try
        {
            Object localObject = Class.forName("android.app.MiuiNotification").newInstance();
            Field localField = localObject.getClass().getDeclaredField("messageCount");
            localField.setAccessible(true);
            localField.set(localObject, Integer.valueOf(paramInt));
            paramNotification.getClass().getField("extraNotification").set(paramNotification, localObject);
            return;
        }
        catch (NoSuchFieldException localNoSuchFieldException)
        {
            localNoSuchFieldException.printStackTrace();
            return;
        }
        catch (IllegalArgumentException localIllegalArgumentException)
        {
            localIllegalArgumentException.printStackTrace();
            return;
        }
        catch (IllegalAccessException localIllegalAccessException)
        {
            localIllegalAccessException.printStackTrace();
            return;
        }
        catch (ClassNotFoundException localClassNotFoundException)
        {
            localClassNotFoundException.printStackTrace();
            return;
        }
        catch (InstantiationException localInstantiationException)
        {
            localInstantiationException.printStackTrace();
        }
    }

    public static void changeMIBadge(Context paramContext, int paramInt)
    {
        if (isMIUI6())
            return;
        Intent localIntent = new Intent("android.intent.action.APPLICATION_MESSAGE_UPDATE");
        localIntent.putExtra("android.intent.extra.update_application_component_name", "com.tencent.mobileqq/.activity.SplashActivity");
        String str;
        if (paramInt > 0)
            if (paramInt > mLimitCount)
                str = "" + mLimitCount;
        while (true)
        {
            localIntent.putExtra("android.intent.extra.update_application_message_text", str);
            paramContext.sendBroadcast(localIntent);
            return;
            str = paramInt + "";
            continue;
            str = "";
        }
    }

    public static void changeOPPOBadge(Context paramContext, int paramInt)
    {
        if (paramInt > mLimitCount)
            paramInt = mLimitCount;
        try
        {
            Bundle localBundle = new Bundle();
            localBundle.putInt("app_badge_count", paramInt);
            paramContext.getContentResolver().call(Uri.parse("content://com.android.badge/badge"), "setAppBadgeCount", null, localBundle);
            return;
        }
        catch (Throwable localThrowable)
        {
        }
    }

    public static void changeVivoBadge(Context paramContext, int paramInt)
    {
        if (paramInt > mLimitCount)
            paramInt = mLimitCount;
        String str = getLauncherClassName(paramContext);
        if (str == null)
            return;
        Intent localIntent = new Intent("launcher.action.CHANGE_APPLICATION_NOTIFICATION_NUM");
        localIntent.putExtra("packageName", paramContext.getPackageName());
        localIntent.putExtra("className", str);
        localIntent.putExtra("notificationNum", paramInt);
        paramContext.sendBroadcast(localIntent);
    }

    public static void changeZUKBadge(Context paramContext, int paramInt)
    {
        if (paramInt > mLimitCount)
            paramInt = mLimitCount;
        try
        {
            Bundle localBundle = new Bundle();
            localBundle.putStringArrayList("app_shortcut_custom_id", null);
            localBundle.putInt("app_badge_count", paramInt);
            if (paramContext.getContentResolver().call(Uri.parse("content://com.android.badge/badge"), "setAppBadgeCount", null, localBundle) != null);
            for (boolean bool = true; ; bool = false)
            {
                Log.d("CommonBadgeUtilImpl", "changeZUKBadge mcount=" + paramInt + "result=" + bool);
                return;
            }
        }
        catch (Throwable localThrowable)
        {
            localThrowable.printStackTrace();
        }
    }

    public static String getLauncherClassName(Context paramContext)
    {
        if (!TextUtils.isEmpty(mLauncherClassName))
            return mLauncherClassName;
        PackageManager localPackageManager = paramContext.getPackageManager();
        Intent localIntent = new Intent("android.intent.action.MAIN");
        localIntent.addCategory("android.intent.category.LAUNCHER");
        try
        {
            Iterator localIterator = localPackageManager.queryIntentActivities(localIntent, 0).iterator();
            while (localIterator.hasNext())
            {
                ResolveInfo localResolveInfo = (ResolveInfo)localIterator.next();
                if (!localResolveInfo.activityInfo.applicationInfo.packageName.equalsIgnoreCase(paramContext.getPackageName()))
                    continue;
                String str = localResolveInfo.activityInfo.name;
                mLauncherClassName = str;
                return str;
            }
        }
        catch (Exception localException)
        {
            return null;
        }
        return null;
    }

    // ERROR //
    public static boolean isMIUI6()
    {
        // Byte code:
        //   0: getstatic 103	com/tencent/commonsdk/badge/CommonBadgeUtilImpl:miui6Flag	I
        //   3: ifne +131 -> 134
        //   6: iconst_m1
        //   7: putstatic 103	com/tencent/commonsdk/badge/CommonBadgeUtilImpl:miui6Flag	I
        //   10: ldc 93
        //   12: astore_0
        //   13: aconst_null
        //   14: astore_1
        //   15: new 346	java/io/BufferedReader
        //   18: dup
        //   19: new 348	java/io/InputStreamReader
        //   22: dup
        //   23: new 350	java/lang/ProcessBuilder
        //   26: dup
        //   27: iconst_2
        //   28: anewarray 81	java/lang/String
        //   31: dup
        //   32: iconst_0
        //   33: ldc_w 352
        //   36: aastore
        //   37: dup
        //   38: iconst_1
        //   39: ldc_w 354
        //   42: aastore
        //   43: invokespecial 357	java/lang/ProcessBuilder:<init>	([Ljava/lang/String;)V
        //   46: invokevirtual 361	java/lang/ProcessBuilder:start	()Ljava/lang/Process;
        //   49: invokevirtual 367	java/lang/Process:getInputStream	()Ljava/io/InputStream;
        //   52: invokespecial 370	java/io/InputStreamReader:<init>	(Ljava/io/InputStream;)V
        //   55: sipush 1024
        //   58: invokespecial 373	java/io/BufferedReader:<init>	(Ljava/io/Reader;I)V
        //   61: astore_2
        //   62: aload_2
        //   63: invokevirtual 376	java/io/BufferedReader:readLine	()Ljava/lang/String;
        //   66: astore_0
        //   67: aload_2
        //   68: invokevirtual 379	java/io/BufferedReader:close	()V
        //   71: aload_2
        //   72: ifnull +7 -> 79
        //   75: aload_2
        //   76: invokevirtual 379	java/io/BufferedReader:close	()V
        //   79: aload_0
        //   80: invokestatic 285	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
        //   83: ifne +51 -> 134
        //   86: aload_0
        //   87: invokestatic 383	java/lang/Integer:parseInt	(Ljava/lang/String;)I
        //   90: istore 24
        //   92: iload 24
        //   94: istore 22
        //   96: iload 22
        //   98: iconst_3
        //   99: if_icmple +35 -> 134
        //   102: ldc_w 385
        //   105: new 187	java/lang/StringBuilder
        //   108: dup
        //   109: invokespecial 188	java/lang/StringBuilder:<init>	()V
        //   112: ldc_w 387
        //   115: invokevirtual 192	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   118: iload 22
        //   120: invokevirtual 195	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
        //   123: invokevirtual 199	java/lang/StringBuilder:toString	()Ljava/lang/String;
        //   126: invokestatic 276	android/util/Log:d	(Ljava/lang/String;Ljava/lang/String;)I
        //   129: pop
        //   130: iconst_1
        //   131: putstatic 103	com/tencent/commonsdk/badge/CommonBadgeUtilImpl:miui6Flag	I
        //   134: getstatic 103	com/tencent/commonsdk/badge/CommonBadgeUtilImpl:miui6Flag	I
        //   137: iconst_1
        //   138: if_icmpne +261 -> 399
        //   141: iconst_1
        //   142: ireturn
        //   143: astore 21
        //   145: aload 21
        //   147: invokevirtual 388	java/lang/NumberFormatException:printStackTrace	()V
        //   150: iconst_0
        //   151: istore 22
        //   153: goto -57 -> 96
        //   156: astore 27
        //   158: aload_1
        //   159: ifnull +7 -> 166
        //   162: aload_1
        //   163: invokevirtual 379	java/io/BufferedReader:close	()V
        //   166: aload_0
        //   167: invokestatic 285	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
        //   170: ifne -36 -> 134
        //   173: aload_0
        //   174: invokestatic 383	java/lang/Integer:parseInt	(Ljava/lang/String;)I
        //   177: istore 7
        //   179: iload 7
        //   181: istore 5
        //   183: iload 5
        //   185: iconst_3
        //   186: if_icmple -52 -> 134
        //   189: ldc_w 385
        //   192: new 187	java/lang/StringBuilder
        //   195: dup
        //   196: invokespecial 188	java/lang/StringBuilder:<init>	()V
        //   199: ldc_w 387
        //   202: invokevirtual 192	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   205: iload 5
        //   207: invokevirtual 195	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
        //   210: invokevirtual 199	java/lang/StringBuilder:toString	()Ljava/lang/String;
        //   213: invokestatic 276	android/util/Log:d	(Ljava/lang/String;Ljava/lang/String;)I
        //   216: pop
        //   217: iconst_1
        //   218: putstatic 103	com/tencent/commonsdk/badge/CommonBadgeUtilImpl:miui6Flag	I
        //   221: goto -87 -> 134
        //   224: astore 4
        //   226: aload 4
        //   228: invokevirtual 388	java/lang/NumberFormatException:printStackTrace	()V
        //   231: iconst_0
        //   232: istore 5
        //   234: goto -51 -> 183
        //   237: astore 26
        //   239: aload_1
        //   240: ifnull +7 -> 247
        //   243: aload_1
        //   244: invokevirtual 379	java/io/BufferedReader:close	()V
        //   247: aload_0
        //   248: invokestatic 285	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
        //   251: ifne -117 -> 134
        //   254: aload_0
        //   255: invokestatic 383	java/lang/Integer:parseInt	(Ljava/lang/String;)I
        //   258: istore 13
        //   260: iload 13
        //   262: istore 11
        //   264: iload 11
        //   266: iconst_3
        //   267: if_icmple -133 -> 134
        //   270: ldc_w 385
        //   273: new 187	java/lang/StringBuilder
        //   276: dup
        //   277: invokespecial 188	java/lang/StringBuilder:<init>	()V
        //   280: ldc_w 387
        //   283: invokevirtual 192	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   286: iload 11
        //   288: invokevirtual 195	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
        //   291: invokevirtual 199	java/lang/StringBuilder:toString	()Ljava/lang/String;
        //   294: invokestatic 276	android/util/Log:d	(Ljava/lang/String;Ljava/lang/String;)I
        //   297: pop
        //   298: iconst_1
        //   299: putstatic 103	com/tencent/commonsdk/badge/CommonBadgeUtilImpl:miui6Flag	I
        //   302: goto -168 -> 134
        //   305: astore 10
        //   307: aload 10
        //   309: invokevirtual 388	java/lang/NumberFormatException:printStackTrace	()V
        //   312: iconst_0
        //   313: istore 11
        //   315: goto -51 -> 264
        //   318: astore 15
        //   320: aload_1
        //   321: ifnull +7 -> 328
        //   324: aload_1
        //   325: invokevirtual 379	java/io/BufferedReader:close	()V
        //   328: aload_0
        //   329: invokestatic 285	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
        //   332: ifne +51 -> 383
        //   335: aload_0
        //   336: invokestatic 383	java/lang/Integer:parseInt	(Ljava/lang/String;)I
        //   339: istore 19
        //   341: iload 19
        //   343: istore 17
        //   345: iload 17
        //   347: iconst_3
        //   348: if_icmple +35 -> 383
        //   351: ldc_w 385
        //   354: new 187	java/lang/StringBuilder
        //   357: dup
        //   358: invokespecial 188	java/lang/StringBuilder:<init>	()V
        //   361: ldc_w 387
        //   364: invokevirtual 192	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   367: iload 17
        //   369: invokevirtual 195	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
        //   372: invokevirtual 199	java/lang/StringBuilder:toString	()Ljava/lang/String;
        //   375: invokestatic 276	android/util/Log:d	(Ljava/lang/String;Ljava/lang/String;)I
        //   378: pop
        //   379: iconst_1
        //   380: putstatic 103	com/tencent/commonsdk/badge/CommonBadgeUtilImpl:miui6Flag	I
        //   383: aload 15
        //   385: athrow
        //   386: astore 16
        //   388: aload 16
        //   390: invokevirtual 388	java/lang/NumberFormatException:printStackTrace	()V
        //   393: iconst_0
        //   394: istore 17
        //   396: goto -51 -> 345
        //   399: iconst_0
        //   400: ireturn
        //   401: astore 25
        //   403: goto -324 -> 79
        //   406: astore 8
        //   408: goto -242 -> 166
        //   411: astore 14
        //   413: goto -166 -> 247
        //   416: astore 20
        //   418: goto -90 -> 328
        //   421: astore 15
        //   423: aload_2
        //   424: astore_1
        //   425: goto -105 -> 320
        //   428: astore 9
        //   430: aload_2
        //   431: astore_1
        //   432: goto -193 -> 239
        //   435: astore_3
        //   436: aload_2
        //   437: astore_1
        //   438: goto -280 -> 158
        //
        // Exception table:
        //   from	to	target	type
        //   86	92	143	java/lang/NumberFormatException
        //   15	62	156	java/io/IOException
        //   173	179	224	java/lang/NumberFormatException
        //   15	62	237	java/lang/Exception
        //   254	260	305	java/lang/NumberFormatException
        //   15	62	318	finally
        //   335	341	386	java/lang/NumberFormatException
        //   75	79	401	java/io/IOException
        //   162	166	406	java/io/IOException
        //   243	247	411	java/io/IOException
        //   324	328	416	java/io/IOException
        //   62	71	421	finally
        //   62	71	428	java/lang/Exception
        //   62	71	435	java/io/IOException
    }

    public static boolean isQQLanucher()
    {
        if (packmag == null)
            packmag = sContext.getPackageManager();
        int i = 0;
        while (true)
        {
            int j = sQQLuancherPackageNames.length;
            int k = 0;
            if (i < j);
            try
            {
                PackageInfo localPackageInfo = packmag.getPackageInfo(sQQLuancherPackageNames[i], 0);
                if (localPackageInfo != null)
                {
                    k = 1;
                    return true;
                }
            }
            catch (Exception localException)
            {
                i++;
            }
        }
    }

    public static boolean isSupportBadge(Context paramContext)
    {
        int i = 1;
        if (sContext == null)
            sContext = paramContext;
        if (sBadgeAbility != 0)
        {
            if (sBadgeAbility == i)
                return true;
            return false;
        }
        int j;
        if (Build.MANUFACTURER.equalsIgnoreCase("ZUK"))
        {
            j = 1;
            BadgeController.init(sContext);
            if (BadgeController.isSupport(sContext))
                j = 1;
            if (j == 0){
                return false;
            }

        }
        while (true)
        {
            sBadgeAbility = i;
            if ((islenovoLanucher("com.lenovo.launcher")) || (isQQLanucher()))
            {
                j = 1;
                break;
            }
            if (Build.MANUFACTURER.equalsIgnoreCase("Xiaomi"))
            {
                j = 1;
                break;
            }
            if (Build.MANUFACTURER.equalsIgnoreCase("samsung"))
            {
                j = 1;
                break;
            }
            if (Build.MANUFACTURER.equalsIgnoreCase("huawei"))
            {
                j = 1;
                break;
            }
            if (Build.MANUFACTURER.equalsIgnoreCase("OPPO"))
            {
                j = 1;
                break;
            }
            if (Build.MANUFACTURER.equalsIgnoreCase("vivo"))
            {
                j = 1;
                break;
            }
            j = 0;
            break;
            label173: i = 2;
        }
    }

    public static boolean islenovoLanucher(String paramString)
    {
        if (Build.VERSION.SDK_INT < 15);
        while (true)
        {
            return false;
            if (haslenovoLanucher == -1)
                break;
            if (haslenovoLanucher == 1)
                return true;
        }
        try
        {
            if ((packmag == null) && (sContext != null))
                packmag = sContext.getPackageManager();
            if (Float.valueOf(Float.parseFloat(packmag.getPackageInfo(paramString, 0).versionName.substring(0, 3))).floatValue() >= 6.7F)
            {
                haslenovoLanucher = 1;
                return true;
            }
            haslenovoLanucher = 0;
            return false;
        }
        catch (PackageManager.NameNotFoundException localNameNotFoundException)
        {
            haslenovoLanucher = 0;
            return false;
        }
        catch (Exception localException)
        {
            haslenovoLanucher = 0;
        }
        return false;
    }

    public static void setBadge(Context paramContext, int paramInt)
    {
        setBadge(paramContext, paramInt, false);
    }

    public static void setBadge(Context paramContext, int paramInt, boolean paramBoolean)
    {
        Log.d("CommonBadgeUtilImpl", "setBadge count=" + paramInt + "|forceSet=" + paramBoolean);
        if (isQQLanucher())
            setQQLauncherBadges(paramContext, paramInt);
        if (Build.MANUFACTURER.equalsIgnoreCase("ZUK"))
            changeZUKBadge(paramContext, paramInt);
        if (islenovoLanucher("com.lenovo.launcher"))
            setLenovoBadge(paramContext, paramInt);
        if (Build.MANUFACTURER.equalsIgnoreCase("Xiaomi"))
            changeMIBadge(paramContext, paramInt);
        do
        {
            return;
            if (Build.MANUFACTURER.equalsIgnoreCase("samsung"))
            {
                setSamsungBadge(paramContext, paramInt);
                return;
            }
            if (Build.MANUFACTURER.equalsIgnoreCase("huawei"))
            {
                setHuaweiBadge(paramContext, paramInt);
                return;
            }
            if (!Build.MANUFACTURER.equalsIgnoreCase("OPPO"))
                continue;
            changeOPPOBadge(paramContext, paramInt);
            return;
        }
        while (!Build.MANUFACTURER.equalsIgnoreCase("vivo"));
        changeVivoBadge(paramContext, paramInt);
    }

    public static void setHuaweiBadge(Context paramContext, int paramInt)
    {
        int i = paramInt;
        try
        {
            String str = getLauncherClassName(paramContext);
            if (str == null)
                return;
            if (i > mLimitCount)
                i = mLimitCount;
            Bundle localBundle = new Bundle();
            localBundle.putString("package", paramContext.getPackageName());
            localBundle.putString("class", str);
            localBundle.putInt("badgenumber", i);
            paramContext.getContentResolver().call(Uri.parse("content://com.huawei.android.launcher.settings/badge/"), "change_badge", null, localBundle);
            return;
        }
        catch (Throwable localThrowable)
        {
        }
    }

    public static void setLenovoBadge(Context paramContext, int paramInt)
    {
        ContentValues localContentValues = new ContentValues();
        String str = getLauncherClassName(paramContext);
        if (str == null);
        while (true)
        {
            return;
            Cursor localCursor = null;
            try
            {
                localContentValues.put("package", paramContext.getPackageName());
                localContentValues.put("class", str);
                localContentValues.put("badgecount", Integer.valueOf(paramInt));
                localContentValues.put("extraData", "");
                ContentResolver localContentResolver1 = paramContext.getContentResolver();
                Uri localUri1 = Uri.parse("content://com.lenovo.launcher.badge/lenovo_badges");
                String[] arrayOfString1 = { "package", "class", "badgecount", "extraData" };
                String[] arrayOfString2 = new String[1];
                arrayOfString2[0] = paramContext.getPackageName();
                localCursor = localContentResolver1.query(localUri1, arrayOfString1, "package=?", arrayOfString2, null);
                if ((localCursor != null) && (localCursor.getCount() > 0))
                {
                    if (localCursor.moveToFirst())
                    {
                        ContentResolver localContentResolver2 = paramContext.getContentResolver();
                        Uri localUri2 = Uri.parse("content://com.lenovo.launcher.badge/lenovo_badges");
                        String[] arrayOfString3 = new String[1];
                        arrayOfString3[0] = paramContext.getPackageName();
                        localContentResolver2.update(localUri2, localContentValues, "package=?", arrayOfString3);
                    }
                    return;
                }
                Log.d("lenovo", "setLenovoBadge cur=null");
                paramContext.getContentResolver().insert(Uri.parse("content://com.lenovo.launcher.badge/lenovo_badges"), localContentValues);
                return;
            }
            catch (Throwable localThrowable)
            {
                return;
            }
            finally
            {
                if (localCursor != null)
                    localCursor.close();
            }
        }
        throw localObject;
    }

    public static void setLimitCount(int paramInt)
    {
        mLimitCount = paramInt;
    }

    public static void setMIUI6Badge(Context paramContext, int paramInt, Notification paramNotification)
    {
        if ((Build.MANUFACTURER.equalsIgnoreCase("Xiaomi")) && (isMIUI6()))
            changeMI6Badge(paramContext, paramInt, paramNotification);
    }

    public static void setQQLauncherBadges(Context paramContext, int paramInt)
    {
        if (paramInt > mLimitCount)
            paramInt = mLimitCount;
        while (true)
        {
            Intent localIntent = new Intent("com.tencent.qlauncher.action.ACTION_UPDATE_SHORTCUT");
            localIntent.putExtra("webappId", "20634");
            localIntent.putExtra("info_tips", String.valueOf(paramInt));
            paramContext.sendBroadcast(localIntent);
            return;
            if (paramInt >= 1)
                continue;
            paramInt = 0;
        }
    }

    public static void setSamsungBadge(Context paramContext, int paramInt)
    {
        int i = paramInt;
        Cursor localCursor = null;
        try
        {
            Boolean localBoolean = haveprovider;
            localCursor = null;
            if (localBoolean == null)
            {
                Uri localUri = Uri.parse("content://com.sec.badge/apps");
                localCursor = paramContext.getContentResolver().query(localUri, null, null, null, null);
                if (localCursor == null)
                {
                    haveprovider = Boolean.valueOf(false);
                    return;
                }
                haveprovider = Boolean.valueOf(true);
            }
            if (haveprovider.booleanValue())
            {
                String str = getLauncherClassName(paramContext);
                if (str == null)
                    return;
                if (i > mLimitCount)
                    i = mLimitCount;
                Intent localIntent = new Intent("android.intent.action.BADGE_COUNT_UPDATE");
                localIntent.putExtra("badge_count", i);
                localIntent.putExtra("badge_count_package_name", paramContext.getPackageName());
                localIntent.putExtra("badge_count_class_name", str);
                paramContext.sendBroadcast(localIntent);
            }
            return;
        }
        catch (Throwable localThrowable)
        {
            return;
        }
        finally
        {
            if (localCursor != null)
                localCursor.close();
        }
        throw localObject;
    }

    public static void setSonyBadge(Context paramContext, int paramInt)
    {
        Intent localIntent = new Intent();
        String str1 = getLauncherClassName(paramContext);
        if (str1 == null)
            return;
        if (paramInt < 1)
        {
            str2 = "";
            localIntent.putExtra("com.sonyericsson.home.intent.extra.badge.SHOW_MESSAGE", false);
            localIntent.setAction("com.sonyericsson.home.action.UPDATE_BADGE");
            localIntent.putExtra("com.sonyericsson.home.intent.extra.badge.ACTIVITY_NAME", str1);
            localIntent.putExtra("com.sonyericsson.home.intent.extra.badge.MESSAGE", str2);
            localIntent.putExtra("com.sonyericsson.home.intent.extra.badge.PACKAGE_NAME", paramContext.getPackageName());
            paramContext.sendBroadcast(localIntent);
            return;
        }
        if (paramInt > mLimitCount);
        for (String str2 = "" + mLimitCount; ; str2 = paramInt + "")
        {
            localIntent.putExtra("com.sonyericsson.home.intent.extra.badge.SHOW_MESSAGE", true);
            break;
        }
    }
}
*/
