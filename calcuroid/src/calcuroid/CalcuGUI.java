package calcuroid;

import javafx.animation.FadeTransition;
import javafx.application.Application;
import javafx.beans.binding.Bindings;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class CalcuGUI extends Application {

  private String ss="";

   
  public static void main(String[] args) { launch(args); }
  
  @Override 
  public void start(Stage stage) {
	  
firstStage(stage);
  }
  
  
void firstStage(Stage stage){
	 VBox first=new VBox(10);
	 ImageView iv1 = new ImageView("logof.gif");
	 ImageView iv2 = new ImageView("logo2.png"); 
	 Label help=new Label("Help !");
	 help.setStyle("-fx-text-fill: white;-fx-font-size: 15px;-fx-font-weight: bold;");
	 help.setTextFill(Color.WHITE);
	 first.getChildren().addAll(iv1,iv2,help);  
   	FadeTransition ft = new FadeTransition();
      ft.setNode(iv2);
      ft.setDuration(new Duration(300));
      ft.setFromValue(1.0);
      ft.setToValue(0.0);
      ft.setCycleCount(FadeTransition.INDEFINITE);
      ft.setAutoReverse(true);
      ft.play();
      first.setAlignment(Pos.CENTER);
      first.setStyle("-fx-background-color:black;-fx-padding:5;");
      stage.setTitle("ImageView");
      stage.setScene(new Scene(first,360,640));  
      stage.show();
      stage.getIcons().add(new Image("logof.gif"));
      iv2.setOnMouseClicked(e-> secondStage(stage));
      iv1.setOnMouseClicked(e-> secondStage(stage));
}
  
void secondStage(Stage stage){
	  VBox vbox=new VBox(2);
	  Label screen  = new Label(); 
	  Button x=new Button("Close");
	  x.setStyle("-fx-background-color:#b31e00;-fx-padding:0px 4px 0px 4px;-fx-text-fill:white;-fx-font-size:10px;-fx-font-weight: bold;-fx-background-radius:5;");
	  screen.setStyle(" -fx-background-color: linear-gradient(#f2f2f2, #d6d6d6),linear-gradient(#fcfcfc 0%, #d9d9d9 20%, #d6d6d6 100%),linear-gradient(#dddddd 0%, #f6f6f6 50%);-fx-background-radius:5;-fx-padding: 10;-fx-text-fill: black;-fx-font-size: 15px;-fx-font-weight: bold;");
	  screen.prefWidthProperty().bind(stage.widthProperty().divide(1));
	  screen.prefHeightProperty().bind(stage.heightProperty().divide(9));
	  x.setOnAction(e-> System.exit(0));
	  stage.setWidth(360);
	  stage.setHeight(640);
	vbox.getChildren().addAll(x,screen);
   GridPane  calc = createButtons(screen);
  stage.setTitle("Calcuroid");
  stage.setScene(new Scene(createLayout(vbox, calc)));
  stage.show();
  stage.getIcons().add(new Image("logof.gif"));
}
  


  
  
  private BorderPane createLayout(VBox screen1, GridPane buttons) {
     BorderPane show = new BorderPane();
     show.setTop(screen1);
     ImageView bottom=new ImageView("logo2.png");
     bottom.setFitHeight(50);
     show.setBottom(bottom);
     show.setAlignment(bottom, Pos.CENTER);
     show.setStyle("-fx-background-color:black;-fx-padding:5;-fx-font-size:15;");
     show.setCenter(buttons);
     
    return show;
  }



private GridPane createButtons(Label screen){
	GridPane pane=new GridPane();
	pane.setAlignment(Pos.CENTER);
	pane.setPadding(new Insets(15, 5, 5, 5));
	 pane.setHgap(7);
	 pane.setVgap(7);

	 
Button []numbers=new Button[10]; 

for(int i=2,v=1;i<6&&v<9;i++){
	for(int j=0;j<3;j++){
		String temp=String.valueOf(v);
		numbers[v]=new Button(temp);
		numbers[v].setStyle("-fx-background-color: linear-gradient(#f2f2f2, #d6d6d6),linear-gradient(#fcfcfc 0%, #d9d9d9 20%, #d6d6d6 100%),linear-gradient(#dddddd 0%, #f6f6f6 50%);-fx-background-radius:5;-fx-padding:2;-fx-font-weight: bold;");
		pane.add(numbers[v], j, i);
		v++;}
}
numbers[9]=new Button("0");
numbers[9].setStyle("-fx-background-color: linear-gradient(#f2f2f2, #d6d6d6),linear-gradient(#fcfcfc 0%, #d9d9d9 20%, #d6d6d6 100%),linear-gradient(#dddddd 0%, #f6f6f6 50%);-fx-background-radius:5;-fx-padding:2;-fx-font-weight: bold;");
pane.add(numbers[9],1,5);


Button CEbtn=new Button("CE"); CEbtn.setStyle("-fx-text-fill:white;-fx-background-color:#000000,linear-gradient(#c98ef0,  #67159d),linear-gradient(#b361ea, #571185),linear-gradient(#9e34e5, #3b0c5a);-fx-background-radius:5;-fx-padding:2;-fx-font-weight: bold;");
Button Cbtn=new Button("C"); Cbtn.setStyle("-fx-text-fill:white;-fx-background-color:#000000,linear-gradient(#c98ef0,  #67159d),linear-gradient(#b361ea, #571185),linear-gradient(#9e34e5, #3b0c5a);-fx-background-radius:5;-fx-padding:2;-fx-font-weight: bold;");
Button sinbtn=new Button("sin");
Button cosbtn=new Button("cos");
Button tanbtn=new Button("tan");
Button powerbtn=new Button("x^y");
Button Pibtn=new Button("PI");
Button minusbtn=new Button("-");
Button multibtn=new Button("*");
Button dividebtn=new Button("/");
Button logbtn=new Button("log");
Button moodbtn=new Button("%");
Button openbbtn=new Button("(");
Button closebbtn=new Button(")");
Button plusbtn=new Button("+");
Button squarebtn=new Button("x^2");
Button o14=new Button("e");
ImageView rootof=new ImageView("rootof.png");
Button rootbtn=new Button( "Y rootOf X",rootof);
rootbtn.setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
Button equalbtn=new Button("=");
equalbtn.setStyle("-fx-text-fill:white;-fx-background-color:#000000,linear-gradient(#ff856e,#000000),linear-gradient(#ff856e,#3b0c5a),linear-gradient(#ff856e,#000000);-fx-background-radius:5;-fx-padding:2;-fx-font-weight: bold;");
Button pointbtn=new Button(".");

pane.add(sinbtn, 0, 0);
pane.add(cosbtn, 1, 0);
pane.add(tanbtn, 2, 0);
pane.add(powerbtn, 4, 4);
pane.add(Pibtn, 4, 0);
pane.add(CEbtn, 0, 1);
pane.add(Cbtn, 1, 1);
pane.add(openbbtn, 2, 1);
pane.add(closebbtn, 3, 1);
pane.add(logbtn, 3, 0);
pane.add(dividebtn, 3, 2);
pane.add(moodbtn, 4, 2); 
pane.add(multibtn, 3, 3);
pane.add(squarebtn, 4, 3);
pane.add(minusbtn, 3, 4);
pane.add(o14, 4, 1);
pane.add(pointbtn, 0, 5);
pane.add(equalbtn, 2, 5);
pane.add(plusbtn, 3, 5);
pane.add(rootbtn, 4, 5);


Button[]a =new Button[30];
pane.getChildren().toArray(a);
for(int i=0;i<a.length;i++){
	String temp=a[i].getText();

	a[i].prefWidthProperty().bind(pane.widthProperty().divide(6));
	a[i].prefHeightProperty().bind(pane.heightProperty().divide(8));
		
	if (temp=="Y rootOf X")a[i].setStyle("-fx-text-fill:white;-fx-background-color:#3b0c5a;-fx-background-radius:5;-fx-padding:2;-fx-font-weight: bold;-fx-font-size:10;");
	
	else if(!temp.matches("\\d")&&temp!="CE"&&temp!="C"&&temp!="="&&temp!=".")
		a[i].setStyle("-fx-text-fill:white;-fx-background-color:#3b0c5a;-fx-background-radius:5;-fx-padding:2;-fx-font-weight: bold;-fx-font-size:15;");

		
	if(temp=="cos"||temp=="sin"||temp=="tan"||temp=="log"){
	a[i].setOnAction(e-> {ss=ss+temp+"(";screen.setText(ss); });
	}
	else if(temp=="x^y") a[i].setOnAction(e-> {ss=ss+"^";screen.setText(ss);});
	else if(temp=="x^2")a[i].setOnAction(e-> {ss=ss+"^2";screen.setText(ss);});
	else if(temp=="Y rootOf X")a[i].setOnAction(e-> {ss=ss+"rootOf(";screen.setText(ss);});
	else if(temp=="CE") a[i].setOnAction(e-> {ss="";screen.setText(ss);});
	else if(temp=="C"){ 
			a[i].setOnAction(e-> {
				if( ss.endsWith( "sin(" )|| ss.endsWith( "tan(" )|| ss.endsWith( "log(" )|| ss.endsWith( "cos(" ))
					ss=ss.substring(0,ss.length()-4);
			else if(ss.endsWith( "rootOf(" ))ss=ss.substring(0,ss.length()-7);
			else if(ss.endsWith( "PI" ))ss=ss.substring(0,ss.length()-2);
				else if(ss.length()>0)ss=ss.substring(0,ss.length()-1); 
				screen.setText(ss);});
	}
	else if(temp=="=")
		a[i].setOnAction(e->{
			Equation current=new Equation(ss);
			try{
				screen.setText(current.solve());
				ss=screen.getText();
				}catch(Exception ex){
					screen.setText("Syntax Error");
					ss="";
				}
		});
	else a[i].setOnAction(e-> {ss=ss+temp;screen.setText(ss);});
}
return pane;
}
}
