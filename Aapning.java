import javafx.scene.shape.Rectangle;
import javafx.scene.paint.Color;

class Aapning extends HvitRute{

  public Aapning(int r, int k, Labyrint l){
    super(r, k, l);
  }

  public void gaa(String s){
    s += "(" + kolonne + ", " + rad + ")";

    Liste<String> liste = lab.hentListe();
    liste.leggTil(s);
  }
}
