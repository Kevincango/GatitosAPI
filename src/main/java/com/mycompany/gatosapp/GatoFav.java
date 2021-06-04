/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gatosapp;

/**
 *
 * @author kevin
 */
public class GatoFav {
    
    Gato gato = new Gato();
    String id;
    String imageId;
    String apiKey = gato.getApiKey();
    ImageX image;
    
   public String getId(){
       return id;
   }
   
   public void setId(String id){
       this.id = id;
   }

    public String getImageId() {
        return imageId;
    }

    public void setImageId(String imageId) {
        this.imageId = imageId;
    }

    public String getApiKey() {
        return apiKey;
    }

    public void setApiKey(String apiKey) {
        this.apiKey = apiKey;
    }

    public ImageX getImage() {
        return image;
    }

    public void setImage(ImageX image) {
        this.image = image;
    }
   
   
    
}
