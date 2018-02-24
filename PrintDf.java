/**
 * Luokka levyn käyttöasteraportin tulostukseen ja hallintaan.
 * Created by Olli Ritari on 24/02/2018.
 */
import java.io.*;
import java.util.*;

public class PrintDf {

    public static void main (String[] args) {

        String s = null;
        ArrayList output = new ArrayList();
        ArrayList errors = new ArrayList();

        //Try-catch välttää ohelman kaatumisen ja hallitsee mahdolliset poikkeustilanteet
        try {

            //Ajetaan "df" -komento käyttäen Runtime exec metodia.
            //Saadaan Process -objekti, josta voidaan lukea tuloste
            Process p = Runtime.getRuntime().exec("df");

            BufferedReader stdInput = new BufferedReader(new InputStreamReader(p.getInputStream()));

            BufferedReader stdError = new BufferedReader(new InputStreamReader(p.getErrorStream()));

            // Luetaan tuloste riveittäin ja lisätään rivi kerrallaan listaan
            while ((s = stdInput.readLine()) != null) {
                output.add(s);
            }

            // Luetaan mahdolliset virheet
            while ((s = stdError.readLine()) != null) {
                errors.add(s);
            }

        }
        catch (IOException e) {
            System.out.println("Poikkeus tapahtui: ");
            e.printStackTrace();
            System.exit(-1);
        }

        //Jos tuloste saatiin listalle, tulostetaan listan sisältö
        if (output.size()!=0) {
            System.out.println("Levyn käyttöasteraportti:");
            for(int i = 0; i < output.size(); i++) {
                System.out.println(output.get(i));
            }
            System.out.println("\n" + "Käyttöasteraporttiin tulostettiin " + output.size() + " riviä tekstiä.");
        }

        //Jos virheitä saatiin listalle, tulostetaan listan sisältö
        if (errors.size() != 0) {
            for(int i = 0; i < errors.size(); i++) {
                System.out.println(errors.get(i));
            }
        }

        //Extra-osio. Odotetaan syötettä ja toimitaan sen mukaan
        //Luodaan tarvittavat muuttujat ja scanner, joka ottaa käyttäjän syötteet vastaan
        int input = -1;
        String wanted;
        boolean match;
        Scanner reader = new Scanner(System.in);

        //Otetaan syötteitä vastaan, kunnes käyttäjä syöttää 3, jolloin ohjelma lopetetaan
        do{
            System.out.println("\n" + "Anna haluamasi komento:");
            System.out.println("1 = Hae rivi, 2 = Poista rivi, 3 = Lopeta");

            match = false;
            try {
                input = Integer.parseInt(reader.nextLine());
            }
            catch (NumberFormatException e){
                System.out.println("Väärä syöte. Anna komento 1, 2 tai 3.");
                input = -1;
            }

            if (input == 1) {
                System.out.println("Anna haettavan Filesystem-sarakkeen nimi:");
                wanted = reader.nextLine();
                //Käydään tulosteen rivit yksitellen läpi ja verrataan haluttuun arvoon
                //Ei verrata ensimmäiseen riiviin, koska se on otsikkorivi
                for (int j = 1; j < output.size(); j++){
                    String current = output.get(j).toString();
                    //Jos vastaavuus löytyy tulostetaan haluttu rivi
                    if (current.substring(0, wanted.length()).equals(wanted)){
                        System.out.println(output.get(0));
                        System.out.println(current);
                        match = true;
                    }
                }
                //Jos vastinetta ei löytynyt, kerrotaan käyttäjälle
                if (!match)
                    System.out.println("Haluamaasi riviä ei löytynyt.");
            }
            if (input == 2){
                System.out.println("Anna poistettavan Filesystem-sarakkeen nimi:");
                wanted = reader.nextLine();
                //Käydään tulosteen rivit yksitellen läpi ja verrataan haluttuun arvoon
                for (int j = 1; j < output.size(); j++){
                    String current = output.get(j).toString();
                    //Jos vastaavuus löytyy poistetaan haluttu rivi ja tulostetaan jäljelle jäänyt raportti
                    if (current.substring(0, wanted.length()).equals(wanted)){
                        output.remove(j);
                        match = true;
                        System.out.println("Rivi poistettu.");
                        System.out.println("Raportin sisältö nyt:");
                        for(int i = 0; i < output.size(); i++) {
                            System.out.println(output.get(i));
                        }
                    }
                }
                //Jos vastinetta ei löytynyt, kerrotaan käyttäjälle
                if (!match)
                    System.out.println("Haluamaasi riviä ei löytynyt.");
            }

        }
        while (input != 3);

        reader.close();
    }
}
