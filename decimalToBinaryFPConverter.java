
public class decimalToBinaryFPConverter {

	private int exponent, MSD, signedBit, ePrime;
	private double doubleInput;
	private String stringInput, normalizedString, binaryMSD, binaryEPrime, combinationField, exponentContinuation, binaryOutput;
	
	public static void main(String[] args) {
		//input here
		decimalToBinaryFPConverter converter = new decimalToBinaryFPConverter("-7123645123675431", 15);
		converter.printAnswer();
	}
	
	public void printAnswer() {
		// prints binary output
		System.out.println(this.getAnswer());
		
		// prints the 16digit string
		System.out.println(this.getNormalizedString());
	}

	public String getAnswer() {
		this.initialize();
		return this.getBinaryOutput();
	}
		
	
	public decimalToBinaryFPConverter(String base, int exponent) {
		this.stringInput = base;
		this.exponent = exponent;
	}
	
	public void initialize() {
		this.setDoubleInput(Double.parseDouble(stringInput));
		this.Normalize();
		this.setePrime(exponent + 398);
		this.setBinaryEPrime(String.format("%010d", Integer.parseInt(Integer.toBinaryString(this.ePrime))));
		this.setExponentContinuation(this.getBinaryEPrime().substring(2));
		this.setBinaryMSD(String.format("%04d", Integer.parseInt(Integer.toBinaryString(MSD))));
		
		if(this.MSD >= 0 && this.MSD < 8) {
			this.setCombinationField(this.getBinaryEPrime().substring(0, 2) + this.getBinaryMSD().substring(1));
		}
		else if(this.MSD >= 8 && this.MSD <= 9) {
			this.setCombinationField("11" + this.getBinaryEPrime().substring(0, 2) + this.getBinaryMSD().substring(3));
		}
		
		if(this.getCombinationField().equals("11110")) {
			this.setBinaryOutput("Infinity");
		}
		else if(this.getCombinationField().equals("11111")) {
			this.setBinaryOutput("NaN");
		}
		else {
			this.setBinaryOutput(this.getSignedBit() + " " + this.getCombinationField() + " " + this.getExponentContinuation());
		}
	}
	
	public void Normalize() {
		
		while(doubleInput % 1 != 0) {
			doubleInput *= 10;
			exponent -= 1;
		}
		if(doubleInput < 0) {
			normalizedString = String.format("%017.0f", doubleInput);
			this.setMSD(Character.getNumericValue(normalizedString.charAt(1)));
			this.setSignedBit(1);
		}
		else {
			normalizedString = String.format("%016.0f", doubleInput);
			this.setMSD(Character.getNumericValue(normalizedString.charAt(0)));
			this.setSignedBit(0);
		}
	}

	private String getNormalizedString() {
		return normalizedString;
	}
	
	public String getBinaryOutput() {
		return binaryOutput;
	}

	public String getExponentContinuation() {
		return exponentContinuation;
	}

	public String getBinaryMSD() {
		return binaryMSD;
	}
	
	public String getBinaryEPrime() {
		return binaryEPrime;
	}

	public String getCombinationField() {
		return combinationField;
	}
	
	public int getSignedBit() {
		return signedBit;
	}

	public void setSignedBit(int signedBit) {
		this.signedBit = signedBit;
	}

	public int getMSD() {
		return MSD;
	}

	public void setMSD(int mSD) {
		MSD = mSD;
	}

	public int getePrime() {
		return ePrime;
	}

	public void setePrime(int ePrime) {
		this.ePrime = ePrime;
	}

	public void setBinaryEPrime(String binaryEPrime) {
		this.binaryEPrime = binaryEPrime;
	}
	
	public void setBinaryMSD(String binaryMSD) {
		this.binaryMSD = binaryMSD;
	}


	public void setCombinationField(String combinationField) {
		this.combinationField = combinationField;
	}

	public double getDoubleInput() {
		return doubleInput;
	}

	public void setDoubleInput(double doubleInput) {
		this.doubleInput = doubleInput;
	}

	public void setExponentContinuation(String exponentContinuation) {
		this.exponentContinuation = exponentContinuation;
	}

	public void setBinaryOutput(String binaryOutput) {
		this.binaryOutput = binaryOutput;
	}
	
}
