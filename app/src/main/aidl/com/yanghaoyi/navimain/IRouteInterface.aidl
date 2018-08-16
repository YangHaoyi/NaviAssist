// IRouteInterface.aidl
package com.yanghaoyi.navimain;

// Declare any non-default types here with import statements
import com.yanghaoyi.navimain.RouteInfo;
import com.yanghaoyi.navimain.ITaskCallback;

interface IRouteInterface {
    /**
     * Demonstrates some basic types that you can use as parameters
     * and return values in AIDL.
     */
     RouteInfo getRouteInfo();

     void addRouteInfo(inout RouteInfo routeInfo);

     //用来注册回调的对象
     void registerCallback(ITaskCallback cb);
     void unregisterCallback(ITaskCallback cb);
}
