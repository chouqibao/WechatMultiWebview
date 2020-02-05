package com.xiangteng.xposed.wechatmultiwebview;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import java.lang.reflect.Field;

import de.robv.android.xposed.IXposedHookLoadPackage;
import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.callbacks.XC_LoadPackage;

import static de.robv.android.xposed.XposedHelpers.findAndHookMethod;

public class XposedMain implements IXposedHookLoadPackage {

    public static String LOG_TAG = "Xposed-WeChatMultiWebview";

    @Override
    public void handleLoadPackage(final XC_LoadPackage.LoadPackageParam lpparam) throws Throwable {
        if (lpparam.packageName.equals("com.tencent.mm")) {
            //Log.i(LOG_TAG, "WeChat Hooked!");
            findAndHookMethod(Activity.class, "startActivity", Intent.class, Bundle.class, new XC_MethodHook() {
                protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                    Intent intent = (Intent) param.args[0];
                    String target = intent.getComponent().getClassName();
                    //Log.i(LOG_TAG + "-IntentTarget", target);
                    if (target.equals("com.tencent.mm.plugin.webview.ui.tools.WebViewUI")
                            || target.equals("com.tencent.mm.plugin.webview.ui.tools.preload.TmplWebViewTooLMpUI")) {
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_DOCUMENT);
                        intent.addFlags(Intent.FLAG_ACTIVITY_MULTIPLE_TASK);
                    }
                }
            });
        }
    }
}

