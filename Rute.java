//import javafx.scene.layout.StackPane;
//import javafx.scene.text.Text;

abstract class Rute{

  protected int rad;
  protected int kolonne;
  protected Labyrint lab;
  protected Rute nord;
  protected Rute oest;
  protected Rute soer;
  protected Rute vest;
  //protected Rute[][] array;
  protected boolean besokt = false;

  public Rute(int r, int k, Labyrint l){
    rad = r;
    kolonne = k;
    lab = l;
  }

  public void settNaboer(Rute nord, Rute vest, Rute soer, Rute oest){
    this.nord = nord;
    this.vest = vest;
    this.soer = soer;
    this.oest = oest;
  }

  public void finnUtvei(){
    this.gaa(" ");
  }

  abstract public void resettBesokt();

  abstract public void gaa(String s);

  abstract public char charTilTegn();


}
