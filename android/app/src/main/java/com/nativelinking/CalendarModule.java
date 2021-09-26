package com.nativelinking;

import android.util.Log; // To view logs in native code
import com.facebook.react.bridge.Callback;
import com.facebook.react.bridge.NativeModule;
import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import java.util.HashMap;
import java.util.Map;

public class CalendarModule extends ReactContextBaseJavaModule { //ReactContextBaseJavaModule gives access to ReactApplicationContext which is useful for Native Modules that need to hook into activity lifecycle methods

  CalendarModule(ReactApplicationContext context) {
    super(context);
  }

  @Override //Get name method sets a string using which the module will be identified in RN.
  public String getName() {
    return "CalendarModule";
  }

  @ReactMethod //All native module methods meant to be invoked from JavaScript must be annotated with @ReactMethod.
  // Issues with callbacks:
  //1. You can only have two callbacks in your function arguments- a successCallback and a failureCallback.
  //2. The last argument to a native module method call, if it's a function, is treated as the successCallback.
  //3. The second to last argument to a native module method call, if it's a function, is treated as the failure callback

  //Important:
  //1. Native module method can only invoke one callback, one time.
  //2. And each callback can only be invoked at most one time.
  //3. A native module can, however, store the callback and invoke it later.

  //OPTION 1 (Once callback to handle success and faliure)
  public void createCalendarEvent(
    String name,
    String location,
    Callback callback
  ) {
    callback.invoke(null, "This is callback from native code"); // First argument is error, second is success.
  }

  //OPTION 2 (Two callbacks to handle success and faliure)
  public void createCalendarEventWithTwoCallbacks(
    String name,
    String location,
    Callback myFailureCallback,
    Callback mySuccessCallback
  ) {
    myFailureCallback.invoke("This is faliure callback from native code");
    mySuccessCallback.invoke("This is faliure callback from native code");
  }

  //OPTION 3 (Promises to handle success and faliure)
  public void createCalendarEventWithTwoCallbacks(
    String name,
    String location,
    Promise promise
  ) {
    try {
      promise.resolve("PROMISE_SUCCESS");
    } catch (Exception e) {
      promise.reject("ERROR", "PROMISE_FALIURE");
    }
  }
}
