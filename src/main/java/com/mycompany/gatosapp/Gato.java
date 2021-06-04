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
public class Gato {
    
    String id;
    String url;
    String apiKey = "3bed050a-9c08-4dd7-91e1-01479ca5592a";
    String image;
    
    public String getId(){
        return id;
    }
    
    public void setId(String id){
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getApiKey() {
        return apiKey;
    }

    public void setApiKey(String apiKey) {
        this.apiKey = apiKey;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
    
    
    
}
