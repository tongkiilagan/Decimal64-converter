import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.io.*;
public class Controller implements ActionListener {
  private GUI gui;
  private String hex_ans, binary_ans;
	public Controller(GUI gui) {
		
		this.gui = gui;
	
		gui.addListener(this);
	}
  public void actionPerformed(ActionEvent e) {
    JButton btn;
    JTextArea txtArea;

    if(e.getActionCommand().equals("Base 2")) {
      gui.showInterface("Base2");
      gui.clearTextFields();
    } 
    else 
    if(e.getActionCommand().equals("Base 10")) {
      gui.showInterface("Base10");
      gui.clearTextFields();
    }
    else 
    if(e.getActionCommand().equals("Calculate")) {
      btn = (JButton) e.getSource();
      txtArea = gui.getTextArea("final_ans");

      if(btn.getName().equals("Base10_calculate")) {
        gui.showInterface("Answer");
        binary_ans = "0000000000000000000000000000000000000000011111111111111111111111";
        hex_ans= "0000FFFF0000AAAA";
        txtArea.setText(binary_ans);
      }
      else 
      if(btn.getName().equals("Base2_calculate")) {
        gui.showInterface("Answer");
        binary_ans = "1111111111111111111111111110111111111111111111111111111111101111";
        hex_ans = "FFFFFFFFFFFFFFFF";
        txtArea.setText(binary_ans);        
      }
       /*
          TODO: 
          1. Perform calculations depending if Base 10 or 2
          2. Store both answers to hex_ans and binary_ans
          2. Display as binary (as default)
       */
    }
    else 
    if(e.getActionCommand().equals("hex")) {
      // TODO: change the JLabel to hex_ans 
      txtArea = gui.getTextArea("final_ans");
      txtArea.setText(hex_ans);
      btn = gui.getButton("Save to text");
      btn.setText("Save to text");
      btn.setEnabled(true);
    }
    else 
    if(e.getActionCommand().equals("binary")) {
      // TODO: change the JLabel to binary_ans
      txtArea = gui.getTextArea("final_ans");
      txtArea.setText(binary_ans);
      btn = gui.getButton("Save to text");
      btn.setText("Save to text");
      btn.setEnabled(true);
    }    
    else 
    if(e.getActionCommand().equals("Save to text")) {
      // TODO: get JLabel and append it to output.txt
      txtArea = gui.getTextArea("final_ans");
      writeFile(txtArea.getText());
      btn = (JButton) e.getSource();
      btn.setText("Success!");
      btn.setEnabled(false);
    }
    else
    if(e.getActionCommand().equals("Exit")) {
      System.exit(0);
    }
  }
  public void displayAnswer() {

  }
  public static void writeFile(String str) {
    try {
      FileWriter fw = new FileWriter("Output.txt",true);
      PrintWriter pw = new PrintWriter(fw);
      pw.print(str+"\n");
      pw.close();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
