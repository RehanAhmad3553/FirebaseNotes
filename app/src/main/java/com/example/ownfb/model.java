package com.example.ownfb;

public class model {
   String first,second,third,Imageurl;


   public model(String first, String second, String third, String imageurl) {
      this.first = first;
      this.second = second;
      this.third = third;
      Imageurl = imageurl;
   }

   public model() {
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

   public String getImageurl() {
      return Imageurl;
   }

   public void setImageurl(String imageurl) {
      Imageurl = imageurl;
   }
}
