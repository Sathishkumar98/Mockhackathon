import java.util.*;
import java.io.*;
import java.text.DecimalFormat;
import java.math.RoundingMode;
import java.io.FileNotFoundException;
import org.json.JSONObject;
public class Memory {

	public static void main(String[] args) throws IOException 
	{
		double[] a = new double[10000];
		JSONObject obj  = new JSONObject();
		JSONObject obj1 = new JSONObject();
		File fileIn = new File("F:\\Memory.txt");
		try (BufferedReader bf = new BufferedReader(new FileReader(fileIn))) 
        {
            String readLine;
            int li=0;
            int i=0;
            double temp;
            double sum=0.0;
            double max = 0.0;
            while ((readLine = bf.readLine()) != null) 
            {
                if (li % 2 != 0) {
                	String str=readLine;
                	str=str.replaceAll("[^0-9]","");
                    str=str.trim();
                    temp=Integer.parseInt(str);
                    a[i++]=temp/10000;
                     }
                li++;
            }
            String str;
            for(int j=0;j<938;j++)
            {
            	str = String.format("%d",j);
            	obj1.put(str + "s", a[j]);
            	if(max<a[j])
            		max=a[j];
            	sum=sum+a[j];
            }
            double avg=sum/938;
            DecimalFormat df = new DecimalFormat("#.###");
            df.setRoundingMode(RoundingMode.CEILING);
            obj.put("AverageMemory(MB)", df.format(avg));
            obj.put("values: ", obj1);
            obj.put("MaximumMemory(MB)", df.format(max));
            System.out.println(obj);
         }
 }
}
