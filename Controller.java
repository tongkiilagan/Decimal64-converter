import javax.swing.*;
import java.awt.event.*;
import java.io.*;
import java.math.BigInteger;

public class Controller implements ActionListener {
  private GUI gui;
  private String hex_ans, binary_ans, normalizedNum, rounding_field;
  private StringBuilder sb;
	public Controller(GUI gui) {
		
		this.gui = gui;
	
		gui.addListener(this);
	}
  public void actionPerformed(ActionEvent e) {
    JButton btn;
    JTextArea txtArea;
    JFrame frame;
    String mantissa;
    int exponent;

    if(e.getActionCommand().equals("Base 10")) {
      gui.showInterface("Base10");
      gui.clearTextFields();
      btn = gui.getButton("Save to text");
      btn.setText("Save to text");
      btn.setEnabled(true);      
    }
    else 
    if(e.getActionCommand().equals("Calculate")) {
      btn = (JButton) e.getSource();
      txtArea = gui.getTextArea("final_ans");

      if(btn.getName().equals("Base10_calculate")) {
        sb = new StringBuilder();
        mantissa = gui.getTextField("Base10_mantissa").getText();
        exponent = Integer.parseInt(gui.getTextField("Base10_exponent").getText());

        if(mantissa.length() > 16) {
          if(!gui.getBox("rounding_fields").getSelectedItem().equals("--Select Rounding Method--")) {
            gui.showInterface("Answer");
            rounding_field = (String) gui.getBox("rounding_fields").getSelectedItem();
            System.out.println("str length > 16, mantissa: " + mantissa);
            Rounding round = new Rounding(mantissa);
            if(rounding_field.equals("Towards 0")) {
              mantissa = round.round(1);
            }
            else 
            if(rounding_field.equals("Ceiling")) {
              mantissa = round.round(2);
            }
            else
            if(rounding_field.equals("Floor")) {
              mantissa = round.round(3);
            }
            else
            if(rounding_field.equals("RTN-TE")) {
              mantissa = round.round(4);
            }
              decimalToBinaryFPConverter dbfpc = new decimalToBinaryFPConverter(mantissa, exponent);
              sb.append(dbfpc.getAnswer());
              if(sb.toString() != "Infinity" || sb.toString() != "NaN") { 
                normalizedNum = dbfpc.getNormalizedString();
                String bcd = getFullDenselyPackedBCD(normalizedNum);
                sb.append(" ");
                sb.append(bcd);
                hex_ans = new BigInteger(sb.toString().replaceAll("\\s+", ""), 2).toString(16).toUpperCase();
                binary_ans = sb.toString();
                txtArea.setText(binary_ans);
              } else {
                binary_ans = sb.toString();
                hex_ans = sb.toString();
              }     
          } else {
            frame = new JFrame();
            JOptionPane.showMessageDialog(frame, "Please enter a rounding method");
          }
        } else {
          gui.showInterface("Answer");
          decimalToBinaryFPConverter dbfpc = new decimalToBinaryFPConverter(mantissa, exponent);
          sb.append(dbfpc.getAnswer());
          if(sb.toString() != "Infinity" || sb.toString() != "NaN") { 
            normalizedNum = dbfpc.getNormalizedString();
            String bcd = getFullDenselyPackedBCD(normalizedNum);
            sb.append(" ");
            sb.append(bcd);
            hex_ans = new BigInteger(sb.toString().replaceAll("\\s+", ""), 2).toString(16).toUpperCase();
            binary_ans = sb.toString();
            txtArea.setText(binary_ans);
          } else {
            binary_ans = sb.toString();
            hex_ans = sb.toString();
          }
        }
      }
    }
    else 
    if(e.getActionCommand().equals("hex")) {
      txtArea = gui.getTextArea("final_ans");
      txtArea.setText(hex_ans);
      btn = gui.getButton("Save to text");
      btn.setText("Save to text");
      btn.setEnabled(true);
    }
    else 
    if(e.getActionCommand().equals("binary")) {
      txtArea = gui.getTextArea("final_ans");
      txtArea.setText(binary_ans);
      btn = gui.getButton("Save to text");
      btn.setText("Save to text");
      btn.setEnabled(true);
    }    
    else 
    if(e.getActionCommand().equals("Save to text")) {
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
