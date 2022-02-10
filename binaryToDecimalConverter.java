
public class binaryToDecimalConverter {

	private String stringInput, decimalOutput;
	private double doubleInput;
	private int exponent;

	// method to get final answer
	public String getAnswer() {
			
		this.initialize();
		return this.getDecimalOutput();
	}
		
	public binaryToDecimalConverter(String stringInput, int exponent) {
		this.setStringInput(stringInput);
		this.exponent = exponent;
	}
	
	public void printOutput() {
		// print output
		System.out.println(this.getDecimalOutput());
	}
	
	public void initialize() {
		this.setDoubleInput(Double.parseDouble(stringInput));
		this.Normalize();
	}
	
	public void Normalize() {
		
		int length = stringInput.length();
		double temp;
		if(doubleInput < 0) {
			temp = binaryToDecimal(stringInput.substring(1), length - 1);
		}
		else {
			temp = binaryToDecimal(stringInput, length);
		}
		
		if(this.exponent < 0) {
			for(int i = 0; i > exponent; i--) {
				temp /= 2;
			}
		}
		else if(this.exponent > 0) {
			for(int i = 0; i < exponent; i++) {
				temp *= 2;
			}
		}
		
		if(doubleInput < 0) {
			this.setDecimalOutput("-" + String.valueOf(temp));
		}
		else {
			this.setDecimalOutput(String.valueOf(temp));
		}
	}

	// creds to gfg
	public double binaryToDecimal(String binary, int len) {
		
		// Fetch the radix point
		int point = binary.indexOf('.');
		
		// Update point if not found
		if (point == -1)
		point = len;
		
		double intDecimal = 0,
		fracDecimal = 0,
		twos = 1;
		
		// Convert integral part of binary to decimal
		// equivalent
		for(int i = point - 1; i >= 0; i--)
		{
		intDecimal += (binary.charAt(i) - '0') * twos;
		twos *= 2;
		}
		
		// Convert fractional part of binary to
		// decimal equivalent
		twos = 2;
		for(int i = point + 1; i < len; i++)
		{
		fracDecimal += (binary.charAt(i) - '0') / twos;
		twos *= 2.0;
		}
		
		// Add both integral and fractional part
		return intDecimal + fracDecimal;
	}
	
	public String getStringInput() {
		return stringInput;
	}

	public void setStringInput(String stringInput) {
		this.stringInput = stringInput;
	}

	public double getDoubleInput() {
		return doubleInput;
	}

	public void setDoubleInput(double doubleInput) {
		this.doubleInput = doubleInput;
	}

	public String getDecimalOutput() {
		return decimalOutput;
	}

	public void setDecimalOutput(String decimalOutput) {
		this.decimalOutput = decimalOutput;
	}
}
