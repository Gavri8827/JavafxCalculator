import java.util.ArrayList;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

public class MyProjectController {

    @FXML
    private GridPane grid;

    @FXML
    private TextField text;
    private Button[][] buttons;
   	private final int HEIGHT = 4;
   	private final int WIDTH = 5;
   	private String[]signs= {"+","-","*","/","+/-",".","="};
   	private Calculator calculator=new Calculator();


    public void initialize() {
    	buttons = new Button[HEIGHT][WIDTH];
    	int num=0;
    	int index=0;
    	
		for (int i = 0; i < HEIGHT ; i++) {
			for (int j = 0; j < WIDTH ; j++) {
				if (i==3 && j==2) {
					break;
				}
				if(i<2) {
					buttons[i][j] = new Button(num+"");
					num++;
					buttons[i][j].setPrefSize(grid.getPrefWidth() / WIDTH, grid.getPrefHeight() / HEIGHT);
					buttons[i][j].setOnAction(new EventHandler<ActionEvent>() {
					    @Override public void handle(ActionEvent e) {
					    	handleButton(e);
					    }
					});
				}
				else {
				buttons[i][j] = new Button(signs[index]);
				index++;
				buttons[i][j].setPrefSize(grid.getPrefWidth() / WIDTH, grid.getPrefHeight() / HEIGHT);
				buttons[i][j].setOnAction(new EventHandler<ActionEvent>() {
				    @Override public void handle(ActionEvent e) {
				    	handleButton(e);
				    }
				});
				}
				grid.add(buttons[i][j], j, i);
				
				
			}
		}
		
				
    }
		
    private void handleButton(ActionEvent e) {
    	Button button=(Button)e.getSource();
    	String keyText = button.getText();
    	switch (keyText) {
    		case "0":
    		case "1":
    		case "2":
    		case "3":
    		case "4":
    		case "5":
    		case "6":
    		case "7":
    		case "8":
    		case "9":
    			calculator.number(keyText);
    			break;
    		case "+":
    			calculator.operation("add");
    			break;
    		case "-":
    			calculator.operation("substract");
    			break;
    		case "*":
    			calculator.operation("multiply");
    			break;
    		case "/":
    			calculator.operation("divide");
    			break;
    		case "=":
    			calculator.equal();
    			break;
    		case ".":
    			calculator.comma();
    			break;
    		case "+/-":
    			calculator.changeSign();
    			break;
    		default:
    			break;
    	}
    	displayResult();
    }
    	
    
    @FXML
    void ClearPressed(ActionEvent event) {
    	calculator.reset();
		displayResult();
    }
    
    
	

    private void displayResult() {
	text.setText(calculator.getCurrentResult());
    }

}


