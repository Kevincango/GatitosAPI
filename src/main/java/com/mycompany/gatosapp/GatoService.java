/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gatosapp;

import com.google.gson.Gson;
import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;
import java.awt.Image;
import java.io.IOException;
import java.net.URL;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

/**
 *
 * @author kevin
 */
public class GatoService {

    public static void verGatos() throws IOException {

        //vamos a traer los datos de la api
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder().url("https://api.thecatapi.com/v1/images/search").get().build();
        Response response = client.newCall(request).execute();

        String elJson = response.body().string();
        //cortar los corchetes 
        elJson = elJson.substring(1, elJson.length() - 1);
        //Crear un objeto de la clase Gson
        Gson gson = new Gson();
        Gato gato = gson.fromJson(elJson, Gato.class);
        //Redimensionar en caso de necesitar
        Image image = null;
        try {
            URL url = new URL(gato.getUrl());
            image = ImageIO.read(url);
            ImageIcon fondoGato = new ImageIcon(image);

            if (fondoGato.getIconWidth() > 800) {
                //redimensionamos
                Image fondo = fondoGato.getImage();
                Image modificada = fondo.getScaledInstance(700, 500, java.awt.Image.SCALE_SMOOTH);
                fondoGato = new ImageIcon(modificada);

            }

            String menu = "Opciones: \n"
                    + " 1. ver otra imagen \n"
                    + " 2. Favorito \n"
                    + " 3. Volver \n";

            String[] botones = {"ver otra imagen1", "favorito1", "volver1"};
            String idGato = gato.getId();
            String opcion = (String) JOptionPane.showInputDialog(null, menu, idGato, JOptionPane.INFORMATION_MESSAGE, fondoGato, botones, botones[0]);

            int seleccion = -1;
            //validamos que opcion selecciona el usuario
            for (int i = 0; i < botones.length; i++) {
                if (opcion.equals(botones[i])) {
                    seleccion = i;
                }
            }

            switch (seleccion) {
                case 0:
                    verGatos();
                    break;
                case 1:
                    favoritoGato(gato);
                    break;
                default:
                    break;
            }

        } catch (IOException e) {
            System.out.println(e);
        }

    }

    public static void favoritoGato(Gato gato) {

        try {
            OkHttpClient client = new OkHttpClient();
            MediaType mediaType = MediaType.parse("application/json");
            RequestBody body = RequestBody.create(mediaType, "{\n\t\"image_id\":\"" + gato.getId() + "\"\n}");
            Request request = new Request.Builder()
                    .url("https://api.thecatapi.com/v1/favourites")
                    .post(body)
                    .addHeader("Content-Type", "application/json")
                    .addHeader("x-api-key", gato.getApiKey())
                    .build();
            Response response = client.newCall(request).execute();
            
            String elJson = response.body().string();

        } catch (IOException e) {
            System.out.println(e);
        }

    }

    public static void getFavoritoGato(String apiKey) throws IOException {

        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url("https://api.thecatapi.com/v1/favourites")
                .get()
                .addHeader("Content-Type", "application/json")
                .addHeader("x-api-key", apiKey)
                .build();
        Response response = client.newCall(request).execute();

        //Guardamos el string con la respuesta
        String elJson = response.body().string();

        //creamos el objeto Gson
        Gson gson = new Gson();

        GatoFav[] gatosarray = gson.fromJson(elJson, GatoFav[].class);

        if (gatosarray.length > 0) {

            int min = 1;
            int max = gatosarray.length - 1;
            int aleatorio = (int) (Math.random() * ((max - min) + 1)) + min;
            int index = aleatorio - 1;

            GatoFav gatofav = gatosarray[index];

            //Redimensionar en caso de necesitar
            Image image = null;
            try {
                URL url = new URL(gatofav.image.getUrl());
                image = ImageIO.read(url);
                ImageIcon fondoGato = new ImageIcon(image);

                if (fondoGato.getIconWidth() > 800) {
                    //redimensionamos
                    Image fondo = fondoGato.getImage();
                    Image modificada = fondo.getScaledInstance(800, 600, java.awt.Image.SCALE_SMOOTH);
                    fondoGato = new ImageIcon(modificada);
                }
                String menu = "Opciones: \n"
                        + " 1. ver otra imagen \n"
                        + " 2. Eliminar Favorito \n"
                        + " 3. Volver \n";

                String[] botones = {"ver otra imagen", "eliminar favorito", "volver"};
                String id_gato = gatofav.getId();
                String opcion = (String) JOptionPane.showInputDialog(null, menu, id_gato, JOptionPane.INFORMATION_MESSAGE, fondoGato, botones, botones[0]);

                int seleccion = -1;
                //validamos que opcion selecciona el usuario
                for (int i = 0; i < botones.length; i++) {
                    if (opcion.equals(botones[i])) {
                        seleccion = i;
                    }
                }

                switch (seleccion) {
                    case 0:
                        getFavoritoGato(apiKey);
                        break;
                    case 1:
                        borrarFavorito(gatofav);
                        break;
                    default:
                        break;

                }
            } catch (IOException e) {
                System.out.println(e);
            }
        }
    }

    public static void borrarFavorito(GatoFav gatofav) {

        try {
                    OkHttpClient client = new OkHttpClient();
                    Request request = new Request.Builder()
                    .url("https://api.thecatapi.com/v1/favourites/"+gatofav.getId()+"")
                    .delete()
                    .addHeader("Content-Type", "application/json")
                    .addHeader("x-api-key", gatofav.getApiKey())
                    .build();
                    Response response = client.newCall(request).execute();
                    
                    String elJson = response.body().string();

        } catch (IOException e) {
            System.out.println(e);
        }

    }

}
