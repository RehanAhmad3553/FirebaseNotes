package com.example.ownfb;

public class model {

    String ImageUrl,first,second,third;

    public model(String imageUrl, String first, String second, String third) {
        ImageUrl = imageUrl;
        this.first = first;
        this.second = second;
        this.third = third;
    }

    public model() {
    }


    public String getImageUrl() {
        return ImageUrl;
    }

    public void setImageUrl(String imageUrl) {
        ImageUrl = imageUrl;
    }

    public String getFirst() {
        return first;
    }

    public void setFirst(String first) {
        this.first = first;
    }

    public String getSecond() {
        return second;
    }

    public void setSecond(String second) {
        this.second = second;
    }

    public String getThird() {
        return third;
    }

    public void setThird(String third) {
        this.third = third;
    }
}
