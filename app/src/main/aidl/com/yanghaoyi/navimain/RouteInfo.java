package com.yanghaoyi.navimain;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * @author : YangHaoYi on 2018/8/15.
 *         Email  :  yang.haoyi@qq.com
 *         Description :
 *         Change : YangHaoYi on 2018/8/15.
 *         Version : V 1.0
 */
public class RouteInfo implements Parcelable{

    private int sratrPoint;
    private int endPoint;
    private String name;

    public RouteInfo(){

    }

    protected RouteInfo(Parcel in) {
        sratrPoint = in.readInt();
        endPoint = in.readInt();
        name = in.readString();
    }

    public static final Creator<RouteInfo> CREATOR = new Creator<RouteInfo>() {
        @Override
        public RouteInfo createFromParcel(Parcel in) {
            return new RouteInfo(in);
        }

        @Override
        public RouteInfo[] newArray(int size) {
            return new RouteInfo[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(sratrPoint);
        dest.writeInt(endPoint);
        dest.writeString(name);
    }

    public int getSratrPoint() {
        return sratrPoint;
    }

    public void setSratrPoint(int sratrPoint) {
        this.sratrPoint = sratrPoint;
    }

    public int getEndPoint() {
        return endPoint;
    }

    public void setEndPoint(int endPoint) {
        this.endPoint = endPoint;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void readFromParcel(Parcel dest) {
        //注意，此处的读值顺序应当是和writeToParcel()方法中一致的
        dest.writeInt(sratrPoint);
        dest.writeInt(endPoint);
        dest.writeString(name);

    }

}
