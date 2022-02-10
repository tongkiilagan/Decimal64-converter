import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.io.*;
import java.math.BigInteger;

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
    String mantissa;
    int exponent;

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
        mantissa = gui.getTextField("Base10_mantissa").getText();
        exponent = Integer.parseInt(gui.getTextField("Base10_exponent").getText());
        decimalToBinaryFPConverter dbfpc = new decimalToBinaryFPConverter(mantissa, exponent);
        binary_ans = dbfpc.getAnswer();
        hex_ans = new BigInteger(binary_ans.replaceAll("\\s+", ""), 2).toString(16);

        txtArea.setText(binary_ans);
      }
      else 
      if(btn.getName().equals("Base2_calculate")) {
        gui.showInterface("Answer");
        mantissa = gui.getTextField("Base2_mantissa").getText();
        exponent = Integer.parseInt(gui.getTextField("Base2_exponent").getText());
        binaryToDecimalConverter bdc = new binaryToDecimalConverter(mantissa, exponent);
        decimalToBinaryFPConverter dbfpc = new decimalToBinaryFPConverter(bdc.getAnswer(), 0);
        binary_ans = dbfpc.getAnswer();
        hex_ans = new BigInteger(binary_ans.replaceAll("\\s+", ""), 2).toString(16);

        txtArea.setText(binary_ans);        
      }
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
