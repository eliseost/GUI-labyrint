import javafx.scene.shape.Rectangle;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

class GuiRute extends Rectangle{
  Rute rute;

  public GuiRute(Rute r, int i, int j, Paint c ){
    super(i, j, c);
    rute = r;
  }

  public Rute hentRute(){
    return rute;
  }

}
