import javax.swing.*;
import java.awt.event.*;
import java.io.*;
import java.math.BigInteger;

public class Controller implements ActionListener {
  private GUI gui;
  private String hex_ans, binary_ans, normalizedNum;
  private StringBuilder sb;
	public Controller(GUI gui) {
		
		this.gui = gui;
	
		gui.addListener(this);
	}
  public void actionPerformed(ActionEvent e) {
    JButton btn;
    JTextArea txtArea;
    String mantissa;
    int exponent;

    if(e.getActionCommand().equals("Base 10")) {
      gui.showInterface("Base10");
      gui.clearTextFields();
    }
    else 
    if(e.getActionCommand().equals("Calculate")) {
      btn = (JButton) e.getSource();
      txtArea = gui.getTextArea("final_ans");

      if(btn.getName().equals("Base10_calculate")) {
        sb = new StringBuilder();
        gui.showInterface("Answer");
        mantissa = gui.getTextField("Base10_mantissa").getText();
        exponent = Integer.parseInt(gui.getTextField("Base10_exponent").getText());

        if(mantissa.length() > 16) {
          System.out.println("str length > 16");
          Rounding round = new Rounding(mantissa);
          mantissa = round.round();
          System.out.println(mantissa + " [debug] mantissa");
        }
        decimalToBinaryFPConverter dbfpc = new decimalToBinaryFPConverter(mantissa, exponent);
        sb.append(dbfpc.getAnswer());
        normalizedNum = dbfpc.getNormalizedString();
        String bcd = getFullDenselyPackedBCD(normalizedNum);
        sb.append(" ");
        sb.append(bcd);
        hex_ans = new BigInteger(sb.toString().replaceAll("\\s+", ""), 2).toString(16).toUpperCase();
        binary_ans = sb.toString();
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
  public String getFullDenselyPackedBCD(String str) {
    String bcd1, bcd2, bcd3, bcd4, bcd5;
    StringBuilder sb = new StringBuilder();
    bcd1=str.substring(1, 4);
    bcd2=str.substring(4, 7);
    bcd3= str.substring(7, 10);
    bcd4=str.substring(10, 13);
    bcd5=str.substring(13, 16);
    
    sb.append(DenselyPackedBCDConverter.convertBCD(bcd1));
    sb.append(" ");
    sb.append(DenselyPackedBCDConverter.convertBCD(bcd2));
    sb.append(" ");
    sb.append(DenselyPackedBCDConverter.convertBCD(bcd3));
    sb.append(" ");
    sb.append(DenselyPackedBCDConverter.convertBCD(bcd4));
    sb.append(" ");
    sb.append(DenselyPackedBCDConverter.convertBCD(bcd5));

    return sb.toString();
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
