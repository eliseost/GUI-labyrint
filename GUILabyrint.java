import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.stage.FileChooser;
import javafx.scene.shape.Rectangle;
import javafx.scene.paint.Color;
import java.io.File;
import java.util.Scanner;
import java.io.FileNotFoundException;
import java.util.ArrayList;


public class GUILabyrint extends Application{
  private static Liste<String> s = new Lenkeliste<String>();
  public Rute[][] ruter;
  public Labyrint lab;

  @Override
  public void start(Stage teater){

    FileChooser fileChooser = new FileChooser();
    File fil = fileChooser.showOpenDialog(teater);

    try{
      GridPane pane = new GridPane();
      //pane.setGridLinesVisible(true);

      Labyrint lab = Labyrint.lesFraFil(fil);
      Rute[][] ruter = lab.hentArray();
      GuiRute[][] guiRuter = new GuiRute[lab.hentRader()][lab.hentKolonner()];

      for(int i = 0; i < ruter.length; i++){
        for(int j = 0; j < ruter[i].length; j++){

          if(ruter[i][j] instanceof Aapning){
            //Hører denne firkanten til ruten nå?
            GuiRute firkant = new GuiRute(ruter[i][j], 10, 10, Color.WHITE);
            pane.add(firkant, j, i);
            guiRuter[i][j] = firkant;
          }

          else if(ruter[i][j] instanceof HvitRute){
            final GuiRute firkant = new GuiRute(ruter[i][j], 10, 10, Color.WHITE);
            //getChildren().add(firkant);
            //Når denne ruten blir trykket på endres den til rød.

            firkant.setOnMouseClicked(e ->{
              firkant.setFill(Color.RED);
              GuiRute gRute = (GuiRute) e.getSource();
              Rute rute = gRute.hentRute();
              rute.finnUtvei();
              s = lab.hentListe();
              if(s.stoerrelse() == 0){
                System.out.println("Det finnes ingen løsninger.");
              }else{
                boolean[][] losning = losningStringTilTabell(s.hent(0), lab.hentKolonner(), lab.hentRader());

                //Går gjennom boolean lista for å finne hvilke ruter som skal farges røde.

                for(int k = 0; k < losning.length; k++){
                  for(int l = 0; l < losning[k].length; l++){
                    if(losning[k][l] == true){
                      guiRuter[k][l].setFill(Color.RED);
                    }
                  }
                }
              }
            });

            pane.add(firkant, j, i);
            guiRuter[i][j] = firkant;
          }else{
              GuiRute firkant = new GuiRute(ruter[i][j], 10, 10, Color.BLACK);
              pane.add(firkant, j, i);
              guiRuter[i][j] = firkant;
          }
        }
      }

      Scene scene = new Scene(pane);
      teater.setTitle("Labyrint");
      teater.setScene(scene);
      teater.show();
    }catch(Exception e){}

  }

  static boolean[][] losningStringTilTabell(String losningString, int bredde, int hoyde) {
    boolean[][] losning = new boolean[hoyde][bredde];
    java.util.regex.Pattern p = java.util.regex.Pattern.compile("\\(([0-9]+),([0-9]+)\\)");
    java.util.regex.Matcher m = p.matcher(losningString.replaceAll("\\s",""));
    while (m.find()) {
        int x = Integer.parseInt(m.group(1));
        int y = Integer.parseInt(m.group(2));
        losning[y][x] = true;
    }
    return losning;
  }


}
