package com.nativelinking; // replace your-app-name with your app’s name

import com.facebook.react.ReactPackage;
import com.facebook.react.bridge.NativeModule;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.uimanager.ViewManager;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

//Once module is written in CalenderModule.java
//1. It needs to be registered with React Native
//2. Add your native module to a ReactPackage
//3. Register the ReactPackage with React Native
//4. React Native invokes the method createNativeModules() on a ReactPackage in order to get the list of native modules to register.
//5. To register the CalendarModule package, Add MyAppPackage to the list of packages returned in ReactNativeHost's getPackages() method. Open up your MainApplication.java

public class MyAppPackage implements ReactPackage {

  @Override
  public List<ViewManager> createViewManagers(
    ReactApplicationContext reactContext
  ) {
    return Collections.emptyList();
  }

  @Override
  public List<NativeModule> createNativeModules(
    ReactApplicationContext reactContext
  ) {
    List<NativeModule> modules = new ArrayList<>();

    modules.add(new CalendarModule(reactContext));
    modules.add(new LockscreenModule(reactContext, reactContext));

    return modules;
  }
}
