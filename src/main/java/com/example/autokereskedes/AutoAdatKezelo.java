package com.example.autokereskedes;

import javafx.scene.image.Image;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class AutoAdatKezelo {
    public ArrayList<Auto> osszesAuto=new ArrayList<>();
    public ArrayList<String[]> sorokAdatai=new ArrayList<>();
    public String filename;
    int nextIndex=1;
    public Auto kivalasztottAuto;




    //Autó keresés ID alapján
    public Auto getAutoByID(String autoID){
        System.out.println("Searching for auto with ID: "+autoID);
        for(int i = 0; i<osszesAuto.size(); i++){
            if(autoID.equals(osszesAuto.get(i).auto_id.toString())){
                return osszesAuto.get(i);
            }
        }
        System.out.println("Adatbázis hiba!");
        return osszesAuto.get(0);
    }


    //autók számának lekérdezése
    public int getAutokSzama(){
        return (osszesAuto.size());
    }

    //új autó hozzáadása
    public void addAuto(String marka,String modell, String evjarat, String ar,String kep_link){

        for (int i=0;i<osszesAuto.size();i++){
            if(nextIndex<osszesAuto.get(i).auto_id)
                nextIndex=osszesAuto.get(i).auto_id+1;
        }
        Auto newAuto=new Auto(nextIndex,marka,modell,Integer.parseInt(evjarat),Integer.parseInt(ar),kep_link);
        osszesAuto.add(newAuto);

        //új autó hozzáadása a CSV file-hoz
        try{
            FileWriter fw  = new FileWriter(filename, true);
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter pw = new PrintWriter(bw);

            //Minden mező kitöltésének ellenőrzése
            if(marka.equals("") || modell.equals("") || evjarat.equals("") || ar.equals("") || kep_link.equals("")) {
                //Ha az egyik mező üres, Exception dobás
                System.out.println("Error: Fill all fields");
                pw.flush();
                pw.close();
            }else{
                //Ha ki vannak töltve a mezők
                pw.println(nextIndex + "," + marka + "," + modell + "," + evjarat + "," + ar +"," + kep_link);
                pw.flush();
                pw.close();
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    //Autó eltévolítása
    public void removeAuto(int index) {
        if (index >= 0 && index < osszesAuto.size()) {
            osszesAuto.remove(index);
            try {
                FileWriter fw = new FileWriter(filename);
                BufferedWriter bw = new BufferedWriter(fw);
                PrintWriter pw = new PrintWriter(bw);

                // Újraírjuk az első sort
                pw.println("auto_id,marka,modell,evjarat,ar,kep");

                // Újraírjuk a többi adatot
                for (int i = 0; i < osszesAuto.size(); i++) {
                    pw.println(osszesAuto.get(i).auto_id + "," + osszesAuto.get(i).marka +
                            "," + osszesAuto.get(i).modell + "," + osszesAuto.get(i).evjarat + "," +
                            osszesAuto.get(i).ar + "," + osszesAuto.get(i).kep_link);
                }

                pw.flush();
                pw.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    //CONSTRUKTOR
    public AutoAdatKezelo(){
        filename="src/main/resources/autok.csv";
        File file=new File(filename);

        try {
            Scanner inputStream = new Scanner(file);
            inputStream.nextLine(); //ignores first line

            //DATAROWS SIZE IS 0 HERE

            while (inputStream.hasNext()){
                String data = inputStream.nextLine(); //gets whole line
                String[] values = data.split(","); //splits by commas
                sorokAdatai.add(values); //adds the line to an arraylist (dataRows)
            }
            inputStream.close();
        } catch (Exception e){
            System.out.println(e.getMessage());
        }
        //Autó példányok kiválasztása - létrehozása
        for(int i=0; i<sorokAdatai.size();i++){
            Auto tempAuto = new Auto (Integer.parseInt(sorokAdatai.get(i)[0]),sorokAdatai.get(i)[1],sorokAdatai.get(i)[2],
                    Integer.parseInt(sorokAdatai.get(i)[3]),Integer.parseInt(sorokAdatai.get(i)[4]),
                    sorokAdatai.get(i)[5]);
            osszesAuto.add(tempAuto);
        }
    }
}
