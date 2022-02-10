import java.util.*;
import java.awt.*;
import javax.swing.*;
import java.awt.event.ActionListener;

public class GUI extends JFrame{
	private CardLayout cards = new CardLayout();
  private GridBagConstraints gbc = new GridBagConstraints();  
  private JPanel buttonPanel, interfacePanel;

  private ArrayList<JPanel> arrPanels;
	private ArrayList<JButton> arrButtons;
	private ArrayList<JLabel> arrLabels;
	private ArrayList<JTextArea> arrTextAreas;
	private ArrayList<JTextField> arrTextFields;

  public GUI() {
    super("Decimal 64 Floating Point Converter");

		arrPanels = new ArrayList<JPanel>();
		arrButtons = new ArrayList<JButton>();
		arrLabels = new ArrayList<JLabel>();
		arrTextFields = new ArrayList<JTextField>();
		arrTextAreas = new ArrayList<JTextArea>();
    
    buttonPanel = new JPanel();
    buttonPanel.setLayout(cards);
    interfacePanel = new JPanel();
    interfacePanel.setLayout(cards);   

    setLayout(new GridBagLayout());
    setSize(600, 250);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    init_emptyPanel();
    init_btn_controls();
    init_base10_fields();
    init_base2_fields();
    init_answer_panel();

    gbc.insets = new Insets(5, 5, 5, 5);
		gbc.gridx = 0;
		gbc.gridy = 0;
		buttonPanel.setPreferredSize(new Dimension(200, 150));
		buttonPanel.setBorder(BorderFactory.createLineBorder(Color.black));
		add(buttonPanel, gbc);

		gbc.gridx = 1;
		gbc.gridy = 0;
		interfacePanel.setPreferredSize(new Dimension(300, 150));
		interfacePanel.setBorder(BorderFactory.createLineBorder(Color.black));
		add(interfacePanel, gbc);

		setLocationRelativeTo(null);
		setVisible(true);    
  }
	public void init_btn_controls() {
		JPanel panel = new JPanel();
		JButton btn;
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		
		panel.add(Box.createRigidArea(new Dimension(0, 15)));
		
		btn = new JButton();
		btn.setName("Base 2");
		btn.setText("Base 2");
		btn.setAlignmentX(CENTER_ALIGNMENT);
		arrButtons.add(btn);
		panel.add(btn);
		
		panel.add(Box.createRigidArea(new Dimension(0, 5)));
		
		btn = new JButton();
		btn.setName("Base 10");
		btn.setText("Base 10");
		btn.setAlignmentX(CENTER_ALIGNMENT);
		arrButtons.add(btn);
		panel.add(btn); 
		
		panel.add(Box.createRigidArea(new Dimension(0, 5)));
		
		btn = new JButton();
		btn.setName("Exit");
		btn.setText("Exit");
		btn.setAlignmentX(CENTER_ALIGNMENT);
		arrButtons.add(btn);
		panel.add(btn);
		
		panel.setName("mainMenu");
		arrPanels.add(panel);
		buttonPanel.add(panel, "mainMenu");
	}  
	public void init_base10_fields() {
		JPanel panel1 = new JPanel();
		JPanel panel2 = new JPanel();
		JLabel lbl;
		JTextField txtField;
		JButton btn;
		
		panel1.setLayout(new BorderLayout());
		panel2.setLayout(new GridBagLayout());
		
		gbc.gridx = 0;
		gbc.gridy = 0;
		txtField = new JTextField(13);
		txtField.setName("Base10_mantissa");
		arrTextFields.add(txtField);
		panel2.add(txtField, gbc);

		gbc.gridx = 1;
		gbc.gridy = 0;
		lbl = new JLabel();
		lbl.setText("x10^");
		panel2.add(lbl, gbc);

    gbc.gridx = 2;
		gbc.gridy = 0;
		txtField = new JTextField(3);
		txtField.setName("Base10_exponent");
		arrTextFields.add(txtField);
		panel2.add(txtField, gbc);
		
		panel1.add(panel2, BorderLayout.CENTER);
		
		panel2 = new JPanel();
		panel2.setLayout(new FlowLayout());
		btn = new JButton();
		btn.setName("Base10_calculate");
		btn.setText("Calculate");
		arrButtons.add(btn);
		panel2.add(btn);
		
		panel1.add(panel2, BorderLayout.SOUTH);

    lbl = new JLabel();
    lbl.setText("Base 10");
    panel1.add(lbl, BorderLayout.NORTH);

		panel1.setName("Base10");
		interfacePanel.add(panel1, "Base10");
	}
	public void init_base2_fields() {
		JPanel panel1 = new JPanel();
		JPanel panel2 = new JPanel();
		JLabel lbl;
		JTextField txtField;
		JButton btn;
		
		panel1.setLayout(new BorderLayout());
		panel2.setLayout(new GridBagLayout());
		
		gbc.gridx = 0;
		gbc.gridy = 0;
		txtField = new JTextField(13);
		txtField.setName("Base2_mantissa");
		arrTextFields.add(txtField);
		panel2.add(txtField, gbc);

		gbc.gridx = 1;
		gbc.gridy = 0;
		lbl = new JLabel();
		lbl.setText("x2^");
		panel2.add(lbl, gbc);

    gbc.gridx = 2;
		gbc.gridy = 0;
		txtField = new JTextField(3);
		txtField.setName("Base2_exponent");
		arrTextFields.add(txtField);
		panel2.add(txtField, gbc);
		
		panel1.add(panel2, BorderLayout.CENTER);
		
		panel2 = new JPanel();
		panel2.setLayout(new FlowLayout());
		btn = new JButton();
		btn.setName("Base2_calculate");
		btn.setText("Calculate");
		arrButtons.add(btn);
		panel2.add(btn);
		
		panel1.add(panel2, BorderLayout.SOUTH);

    lbl = new JLabel();
    lbl.setText("Base 2");
    panel1.add(lbl, BorderLayout.NORTH);

		panel1.setName("Base2");
		interfacePanel.add(panel1, "Base2");
	}
	public void init_answer_panel() {
    JPanel panel1 = new JPanel();
		JPanel panel2 = new JPanel();
		JLabel lbl;
		JButton btn;
    JTextArea txtArea;
    JScrollPane jsp;
		
		panel1.setLayout(new BorderLayout());
		panel2.setLayout(new FlowLayout());

		txtArea = new JTextArea(4,20);
		jsp = new JScrollPane(txtArea, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
						  	          JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
    txtArea.setLineWrap(true);
		txtArea.setEditable(false);
		txtArea.setName("final_ans");
		arrTextAreas.add(txtArea);
		panel2.add(jsp);
		
		panel1.add(panel2, BorderLayout.CENTER);
		
		panel2 = new JPanel();
		panel2.setLayout(new FlowLayout());
		btn = new JButton();
		btn.setName("Save to text");
		btn.setText("Save to text");
		arrButtons.add(btn);
		panel2.add(btn);
		
		panel1.add(panel2, BorderLayout.SOUTH);

    panel2 = new JPanel();
		panel2.setLayout(new BoxLayout(panel2, BoxLayout.X_AXIS));
    lbl = new JLabel();
    lbl.setText("Show as: ");
    panel2.add(lbl);
		panel2.add(Box.createRigidArea(new Dimension(5, 0)));
		btn = new JButton();
		btn.setName("hex");
		btn.setText("hex");
		arrButtons.add(btn);
		panel2.add(btn);
		panel2.add(Box.createRigidArea(new Dimension(5, 0)));
		btn = new JButton();
		btn.setName("binay");
		btn.setText("binary");
		arrButtons.add(btn);
		panel2.add(btn);

    panel1.add(panel2, BorderLayout.NORTH);

		panel1.setName("Answer");
		interfacePanel.add(panel1, "Answer");
	}  
	public void init_emptyPanel() {
		JPanel panel = new JPanel();
		panel.setName("emptyPanel");
		interfacePanel.add(panel, "emptyPanel");
	}
	public void addListener(ActionListener al) {
		for(JButton btn: arrButtons)
			btn.addActionListener(al);
	}
  public void showInterface(String interface_name) {
    cards.show(interfacePanel, interface_name);
  }
	public JLabel getLabel(String lbl) {
		for(JLabel label: arrLabels)
			if(label.getName().equals(lbl))
				return label;
		return null;
	}
	public JTextArea getTextArea(String txtArea) {
		for(JTextArea txtAreas: arrTextAreas)
			if(txtAreas.getName().equals(txtArea))
				return txtAreas;
		
		return null;
	}
	public JTextField getTextField(String field) {
		for(JTextField fields: arrTextFields)
			if(fields.getName().equals(field))
				return fields;
		
		return null;
	}  
  public JButton getButton(String btn)
	{
		for(JButton btns: arrButtons)
			if(btns.getName().equals(btn))
				return btns;
		
		return null;
	}
  public void clearTextFields() {
		for(JTextField txtField: arrTextFields)
			txtField.setText("");    
  }
}
