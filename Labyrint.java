import java.io.*;
import java.util.*;

class Labyrint{

  private static Rute[][] labyrintArray;
  private static int antRader;
  private static int antKol;
  private static Liste<String> s = new Lenkeliste<String>();

  private Labyrint(int rader, int kolonner){
    antRader = rader;
    antKol = kolonner;
    labyrintArray = new Rute[antRader][antKol];
  }

  static Labyrint lesFraFil(File fil) throws FileNotFoundException{

    Scanner scanner = new Scanner(fil);
    String[] innlest = scanner.nextLine().split(" ");

    int antR = Integer.parseInt(innlest[0]);
    int antK = Integer.parseInt(innlest[1]);

    Labyrint lab = new Labyrint(antR, antK);

    for(int rad = 0; rad < antR; rad++){
      String linje = scanner.nextLine();

      for(int kol = 0; kol < antK; kol++){
        char x = linje.charAt(kol);

        if(x == '#'){
          Rute rute = new SortRute(rad, kol, lab);
          labyrintArray[rad][kol] = rute;
        }
        if(x == '.'){
          if (erAapning(rad, kol, antR, antK)){
            Rute rute = new Aapning(rad, kol, lab);
            labyrintArray[rad][kol] = rute;
          }
          else{
            Rute rute = new HvitRute(rad, kol, lab);
            labyrintArray[rad][kol] = rute;
          }
        }
      }
    }


    for(int rad = 0; rad < antR; rad++){
      for(int kol = 0; kol < antK; kol++){
        Rute nord = null;
        Rute oest = null;
        Rute soer = null;
        Rute vest = null;

        if(rad > 0){
          nord = labyrintArray[rad-1][kol];
        }
        if(kol < antK - 1){
          oest = labyrintArray[rad][kol+1];
        }
        if(rad < antR - 1){
          soer = labyrintArray[rad+1][kol];
        }
        if(kol > 0){
          vest = labyrintArray[rad][kol-1];
        }
        labyrintArray[rad][kol].settNaboer(nord, vest, soer, oest);
      }
    }
    return lab;
  }

  public static boolean erAapning(int rad, int kol, int antR, int antK){
    if (rad == 0 || kol == 0 || rad == antR-1 || kol == antK-1){
      return true;
    }
    return false;
  }

  public Rute[][] hentArray(){
    return labyrintArray;
  }

  public void skrivUt(){
    for(int rad = 0; rad < antRader; rad++){
      for(int kol = 0; kol < antKol; kol++){
        System.out.print(labyrintArray[rad][kol].charTilTegn());
      }
      System.out.println();
    }
  }

  public Liste<String> hentListe(){
    return s;
  }

  public Liste<String> finnUtveiFra(int rad, int kol){
    s = new Lenkeliste<String>();

    if(kol < 0 || kol > antKol || rad < 0 || rad > antRader){
      System.out.println("Ugyldige koordinater");
      return s;
    }

    labyrintArray[kol][rad].finnUtvei();
    resettAlleruter();

    return s;
  }

  public void resettAlleruter(){
    for(int rad = 0; rad < antRader; rad++){
      for(int kol = 0; kol < antKol; kol++){
        labyrintArray[rad][kol].resettBesokt();
      }
    }
  }

  public int hentKolonner(){
    return antKol;
  }

  public int hentRader(){
    return antRader;
  }
}
