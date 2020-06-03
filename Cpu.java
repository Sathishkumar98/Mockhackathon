import java.io.*;
import java.util.*;
import org.json.simple.JSONObject;
import org.json.simple.JSONArray;
import java.text.DecimalFormat;
import java.io.FileNotFoundException;
import java.math.RoundingMode;

public class CPU {

	public static void main(String[] args) throws IOException {
		JSONObject obj = new JSONObject();
		JSONObject obj1 = new JSONObject();
		JSONObject obj2 = new JSONObject();
		double[] array = new double[1000];
		File file = new File("F:\\cpu.txt");
		try (BufferedReader br = new BufferedReader(new FileReader(file))) {
			String readLine, str, str1, value;
			String result;
			int line = 0;
			int i = 0;
			int j;
			int val = 0;
			int start = 0;
			int count = 0;
			double temp;
			double max = 0.0;
			double sum = 0.0;
			while ((readLine = br.readLine()) != null) {
				count++;
				StringTokenizer st = new StringTokenizer(readLine);
				while (st.hasMoreTokens()) {
					str = st.nextToken();
					start++;
					if (start == 9) {
						value = str;
						temp = Double.parseDouble(value);
						array[i++] = temp;
						start = 0;
						break;
					}
				}
			}
			for (j = 0; j <= count - 1; j++) {
				val = j + 1;
				str1 = String.format("%d", val);
				result = String.format("%.1f", array[j]);
				obj1.put(str1 + "s", result);
				if (array[j] > max) {
					max = array[j];
				}
				sum = sum + array[j];
				val = 0;
			}
			double average = sum / count;
			String avg = String.format("%.2f", average);
			String maximum = String.format("%.2f", max);
			obj.put("AverageCpu", avg);
			obj.put("values: ", obj1);
			obj.put("MaximumCpu", maximum);
			obj2.put("sampletransaction", obj);
			JSONArray jsonList = new JSONArray();
			jsonList.add(obj2);

			try (FileWriter file1 = new FileWriter("F://cpuOutput.json")) {
				file1.write(jsonList.toString());
				file1.flush();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
