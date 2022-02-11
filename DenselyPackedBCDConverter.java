public class DenselyPackedBCDConverter{

    /* Constructor */
    DenselyPackedBCDConverter(String s){
        printBCD(convertBCD(s));
    }

    /* Prints the converted densely packed BCD */
	public static void printBCD (String s)
    {
		System.out.println(s);                       
    }

    /* Binarizes the initial string input into an unpacked BCD */
    public static String convertBCD(String s)
    {
    	String BCD = "";
    	String dense = "";
    	String[] splitBCD;
    	String t;
        int n = Integer.parseInt(s);
        int m = Integer.parseInt(s);
    	int i;

    	while (n != 0)
    	{
    		i = n%10;
    		t = Integer.toBinaryString(i);
    		while(t.length() < 4)
    			t = "0" + t;
    		BCD = t + BCD;
    		n /= 10;
    	}

        if (m < 100)
        {
            BCD = "0000".concat(BCD);
            if(m < 10)
                BCD = "0000".concat(BCD);
            if(m == 0)
                BCD = "000000000000";
        }

        //System.out.println(BCD);
    	splitBCD = BCD.split("");
    	dense = String.join("", denselyPack (splitBCD));
    	return dense;
    }

    /* Converts the unpacked BCD string array into a 
       Densely packed BCD output */
    public static String[] denselyPack(String[] bcd)
    {
    	String[] dense = new String[] {"0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0"};
    	int[] i = new int[3];

    	i[0] = Integer.parseInt(bcd[0]);
    	i[1] = Integer.parseInt(bcd[4]);
    	i[2] = Integer.parseInt(bcd[8]);

        dense[0] = bcd[0];
        dense[1] = bcd[4];
        dense[2] = bcd[8];

        /*
        System.out.println(i[0]);
        System.out.println(i[1]);
        System.out.println(i[2]);
        */

    	if (i[0] == 0 && i[1] == 0 && i[2] == 0)
    	{
    		dense[9] = "0";
    		dense[3] = bcd[1];
        	dense[4] = bcd[2];
        	dense[5] = bcd[3];
       		dense[6] = bcd[5];
        	dense[7] = bcd[6];
        	dense[8] = bcd[7];
        	dense[10] = bcd[9];
        	dense[11] = bcd[10];
        	dense[12] = bcd[11];
    	}

    	else if (i[0] == 0 && i[1] == 0 && i[2] == 1)
    	{
    		dense[9] = "1";
    		dense[5] = bcd[3];
        	dense[8] = bcd[7];
        	dense[12] = bcd[11];
       		dense[3] = bcd[1];
        	dense[4] = bcd[2];
        	dense[6] = bcd[5];
        	dense[7] = bcd[6];
        	dense[10] = "0";
        	dense[11] = "0";
    
    	}

    	else if (i[0] == 0 && i[1] == 1 && i[2] == 0)
    	{
    		dense[9] = "1";
    		dense[5] = bcd[3];
        	dense[8] = bcd[7];
        	dense[12] = bcd[11];
       		dense[3] = bcd[1];
        	dense[4] = bcd[2];
        	dense[6] = bcd[9];
        	dense[7] = bcd[10];
        	dense[10] = "0";
        	dense[11] = "1";
    
    	}

    	else if (i[0] == 1 && i[1] == 0 && i[2] == 0)
    	{
    		dense[9] = "1";
    		dense[5] = bcd[3];
        	dense[8] = bcd[7];
        	dense[12] = bcd[11];
       		dense[3] = bcd[9];
        	dense[4] = bcd[10];
        	dense[6] = bcd[5];
        	dense[7] = bcd[6];
        	dense[10] = "1";
        	dense[11] = "0";
    
    	}

    	else if (i[0] == 0 && i[1] == 1 && i[2] == 1)
    	{
    		dense[9] = "1";
    		dense[5] = bcd[3];
        	dense[8] = bcd[7];
        	dense[12] = bcd[11];
       		dense[3] = bcd[1];
        	dense[4] = bcd[2];
        	dense[6] = "1";
        	dense[7] = "0";
        	dense[10] = "1";
        	dense[11] = "1";
    
    	}

    	else if (i[0] == 1 && i[1] == 0 && i[2] == 1)
    	{
    		dense[9] = "1";
    		dense[5] = bcd[3];
        	dense[8] = bcd[7];
        	dense[12] = bcd[11];
       		dense[3] = bcd[5];
        	dense[4] = bcd[6];
        	dense[6] = "0";
        	dense[7] = "1";
        	dense[10] = "1";
        	dense[11] = "1";
    
    	}

    	else if (i[0] == 1 && i[1] == 1 && i[2] == 0)
    	{
    		dense[9] = "1";
    		dense[5] = bcd[3];
        	dense[8] = bcd[7];
        	dense[12] = bcd[11];
       		dense[3] = bcd[9];
        	dense[4] = bcd[10];
        	dense[6] = "0";
        	dense[7] = "0";
        	dense[10] = "1";
        	dense[11] = "1";
    
    	}

    	else if (i[0] == 1 && i[1] == 1 && i[2] == 1)
    	{
    		dense[9] = "1";
    		dense[5] = bcd[3];
        	dense[8] = bcd[7];
        	dense[12] = bcd[11];
       		dense[3] = "0";
        	dense[4] = "0";
        	dense[6] = "1";
        	dense[7] = "1";
        	dense[10] = "1";
        	dense[11] = "1";
    	}

    	dense[0] = "";
    	dense[1] = "";
    	dense[2] = "";

    return dense;
    }
}