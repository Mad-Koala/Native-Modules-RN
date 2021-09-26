package com.nativelinking;

import android.app.Activity;
import android.app.KeyguardManager;
import android.content.Context;
import android.os.PowerManager;
import android.util.Log; // To view logs in native code
import android.view.Window;
import android.view.WindowManager;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.UiThreadUtil;

public class LockscreenModule extends ReactContextBaseJavaModule {

  private final ReactApplicationContext reactContext;
  private static final String TAG = "myapp:mywakelocktag";

  LockscreenModule(
    ReactApplicationContext context,
    ReactApplicationContext reactContext
  ) {
    super(context);
    this.reactContext = reactContext;
  }

  @Override //Get name method sets a string using which the module will be identified in RN.
  public String getName() {
    return "LockscreenModule";
  }

  @ReactMethod
  public void unlock() {
    UiThreadUtil.runOnUiThread(
      new Runnable() {
        public void run() {
          Activity mCurrentActivity = getCurrentActivity();
          if (mCurrentActivity == null) {
            Log.d(TAG, "ReactContext doesn't hava any Activity attached.");
            return;
          }
          KeyguardManager keyguardManager = (KeyguardManager) reactContext.getSystemService(
            Context.KEYGUARD_SERVICE
          );
          KeyguardManager.KeyguardLock keyguardLock = keyguardManager.newKeyguardLock(
            Context.KEYGUARD_SERVICE
          );
          keyguardLock.disableKeyguard();

          PowerManager powerManager = (PowerManager) reactContext.getSystemService(
            Context.POWER_SERVICE
          );
          PowerManager.WakeLock wakeLock = powerManager.newWakeLock(
            PowerManager.FULL_WAKE_LOCK |
            PowerManager.ACQUIRE_CAUSES_WAKEUP |
            PowerManager.SCREEN_BRIGHT_WAKE_LOCK |
            PowerManager.ON_AFTER_RELEASE,
            "myapp:mywakelocktag"
          );
          wakeLock.acquire();

          Window window = mCurrentActivity.getWindow();
          window.addFlags(
            WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED |
            WindowManager.LayoutParams.FLAG_DISMISS_KEYGUARD |
            WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON |
            WindowManager.LayoutParams.FLAG_TURN_SCREEN_ON
          );
        }
      }
    );
  }
}
