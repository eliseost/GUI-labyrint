import java.util.*;
import java.io.*;


class Test{

  public static void main(String[] args){
    Labyrint lab = null;

    try{
        File fil = new File("3.in");
        lab = Labyrint.lesFraFil(fil);
    }catch(FileNotFoundException e){
        System.out.println("Fant ikke filen");
        return;
    }
    lab.skrivUt();


    lab.finnUtveiFra(5,3);
    Liste<String> liste = lab.hentListe();
    for(String s : liste){
      System.out.println(s);
      System.out.println(" ");
    }

    lab.finnUtveiFra(6,1);
    liste = lab.hentListe();
    for(String s : liste){
      System.out.println(s);
      System.out.println(" ");


    }
  }

}
