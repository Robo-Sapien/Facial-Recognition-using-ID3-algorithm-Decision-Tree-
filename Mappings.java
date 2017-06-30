import java.io.*;
import java.util.*;
//class to map values of every attributes to the resulting attributes that to be used in Entropy Formula

class Mappings 
	{
		public ArrayList<String> mappingvalues = new ArrayList<String>();
		public int maximum;
		public double entropy;
		public String s_name;
		
	//Boolean Function to read data from file
		
	public boolean ReadFile(String fname, ArrayList<DataSet> ds, boolean r_trainig) 
	{
		Scanner fileScanner;
		try 
		{
			fileScanner = new Scanner(new File(fname));
		}
		catch (FileNotFoundException e) 
		{
			return false;
		}

		this.parsing(fileScanner, ds, r_trainig);
		return true;
	}

	//Parsing Function to split data into tokens, Maintaining counters
	
	public void parsing(Scanner fileScanner, ArrayList<DataSet> ds,boolean data_training) 
	{
		//Trim Function to avoid leading and Trailing white spaces
		
		String line = fileScanner.nextLine().trim();
		String tokens;
		StringTokenizer st = new StringTokenizer(line);
		int i =0;
		int c = 0;
		
		while (st.hasMoreTokens()) 
		{
			tokens = st.nextToken();
			Mappings attribute = new Mappings();
			if (data_training) 
			{
				attribute.s_name = tokens;
			}
			
			//Keeping record of Maximum size of tokens
			
			int maxval = Integer.parseInt(st.nextToken());
			
			if (data_training) 
			{
				attribute.maximum = maxval;
				ID3.att.add(attribute);
				i++;
				c++;
			}

		}

		for ( int p=0; p < ID3.att.size(); p++)
		{
			Mappings attribute;
			attribute = ID3.att.get(p);
			
		}

		if (data_training)
			ID3.Att_size = c;
		//Reading tokens of data using FileScanner class
		
		while (fileScanner.hasNextLine()) 
		{
			line = fileScanner.nextLine().trim();
			st = new StringTokenizer(line);
			i = 0;
			String tempAttribute = null;
			DataSet r = new DataSet();
			c = 0;
			
			while (c < ID3.Att_size) 
			{
				tempAttribute = st.nextToken();
				r.attributeValue[c] = (tempAttribute);
				c++;
			}

			r.label = st.nextToken();
			ds.add(r);//adding r i.e. each line to the dataset array
		}

		
		for (i = 0; i < c; i++)//i is getting one attribute at a time
		{
				Mappings attribute = ID3.att.get(i);

				attribute.getMappingValues(ID3.trainingDataset, i);
		}

		for (i =0; i < c; i++)
		{
			Mappings attribute = ID3.att.get(i);
			for (int a =0; a < attribute.maximum; a++)
			{
				String temp = attribute.mappingvalues.get(a);
		//	System.out.println("\n"+temp);
			}
		}
	
	}
	
	//Function to get mapping values of each column with resulting column which is being used in Formula
	
	public void getMappingValues(ArrayList<DataSet> ds, int ind) 
	{
		DataSet temp2;
		//System.out.println(ds.size());
		// ds.size is the number of training examples
		for (int i = 0; i < ds.size(); i++) 
		{
			temp2 = ds.get(i);//at+tributValue[ind] gives the value of the ind th attribute on a given line

			if (!mappingvalues.contains(temp2.attributeValue[ind])) //mappingvalues is an arraylist of strings
			{
				mappingvalues.add(temp2.attributeValue[ind]);

			}

	}
	}

	
	public int getIndex(String val) 
	{
		String temp3;
		for (int i = 0; i < this.mappingvalues.size(); i++) 
		{
			temp3 = this.mappingvalues.get(i);
			if (temp3.equals(val)) 
			{
				return i;
			}
		}
		
		return -999;
	}
}
