package com.jiyun.wahaha;

/**
 * Created by Administrator on 2018/3/31 0031.
 */

public class Car {
    private String name;
    private int img;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getImg() {
        return img;
    }

    public void setImg(int img) {
        this.img = img;
    }

    public Car(String name, int img) {

        this.name = name;
        this.img = img;
    }
}
