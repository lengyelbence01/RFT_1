package com.example.autokereskedes;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class AutoAdatKezelo {
        public ArrayList<Auto> osszesAuto=new ArrayList<>();
        public ArrayList<String[]> sorokAdatai=new ArrayList<>();
        public String filename;
        public Auto kivalasztottAuto;

        //CONSTRUKTOR

    public AutoAdatKezelo(){
        filename="./autok.csv";
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
        } catch (FileNotFoundException e){
            e.printStackTrace();
        }
        //Creation of each User
        for(int i=0; i<sorokAdatai.size();i++){
            Auto tempAuto = new Auto (Integer.parseInt(sorokAdatai.get(i)[0]),sorokAdatai.get(i)[1],sorokAdatai.get(i)[2],
                    Integer.parseInt(sorokAdatai.get(i)[3]),Integer.parseInt(sorokAdatai.get(i)[4]));
            //add each tweet to the allTweets array
            osszesAuto.add(tempAuto);
        }
    }
}
