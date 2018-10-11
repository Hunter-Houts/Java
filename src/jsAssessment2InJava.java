import java.util.HashMap;
import java.util.Arrays;
import java.util.ArrayList;
public class jsAssessment2InJava {
	public static boolean isTen(int num){
		if(num == 10){
			return true;
		} else {
			return false;
		}
	}
	public static int double1(int num){
		return num + num;
	}

	public static ArrayList<Integer> remove9s(ArrayList<Integer> arr){
		ArrayList<Integer> output = new ArrayList<>();
		for(int i = 0; i < arr.size(); i++){
			if(arr.get(i) != 9){
				output.add(arr.get(i));
			}
		}
		return output;
	}
	public static String capitalizeName(String name){
		String output = "";
		if(name.contains(" ")){
			String [] arr = name.split(" ");
			for(String str : arr){
				output += str.substring(0,1).toUpperCase() + str.substring(1);
				output += " ";
			}
			String outputStr = output.trim();
			return outputStr;
		}
		return name.substring(0,1).toUpperCase() + name.substring(1);
	}
	public static String[] capitalizeAllNames(String[] arr){
		String[] output = new String[arr.length];
		for(int i = 0; i < arr.length; i++){
			String name = capitalizeName(arr[i]);
			output[i] = name;
		}
		return output;
	}
	public static int countVowels(String str){
		String lower = str.toLowerCase();
		int count = 0;
		for(int i = 0; i < str.length(); i++){
		if(lower.toCharArray()[i] == 'a' || lower.toCharArray()[i] == 'e' || lower.toCharArray()[i] == 'i' || lower.toCharArray()[i] == 'o' || lower.toCharArray()[i] == 'u'){
			count++;
		}
	}
		return count;
	}
	public static double averageSales(double[] arr){
		double sales = 0.0;
		for(int i = 0; i < arr.length; i++){
			sales += arr[i];
		}
		return sales / arr.length;

	}
	public static ArrayList<HashMap> analyzeWord(String str){
		ArrayList<HashMap> output = new ArrayList<>();
		HashMap<String,String> name = new HashMap<>();
		HashMap<String,Integer> numOfLetters = new HashMap<>();
		HashMap<String,Integer> numOfVowels = new HashMap<>();
		name.put("Word",str);
		numOfLetters.put("numberOfLetters",str.length());
		numOfVowels.put("NumberOfvowels",countVowels(str));
		output.add(name);
		output.add(numOfLetters);
		output.add(numOfVowels);
		return output;
	}
	public static ArrayList<ArrayList> analyzeAllWords(String[] arr){
		ArrayList<ArrayList> output = new ArrayList<>();
		for(String str : arr){
			output.add(analyzeWord(str));
		}
		return output;
	}
	public static ArrayList<String> padArray(String[] arr, int len,String fill){
		ArrayList<String> output = new ArrayList<>();
		for(String str : arr){
			output.add(str);
		}
		for(int i = 0; i < len; i++){
			if(output.size() <= len && output.size() >= len == false){
				output.add(fill);
			}
		}
		return output;
	}
	public static void main(String[] args){
		System.out.println(isTen(10));
		System.out.println(double1(2));
		System.out.println(capitalizeName("hunter houts"));
		String[] names = {"hunter","jean"};
		System.out.println(Arrays.toString(capitalizeAllNames(names)));
		System.out.println(countVowels("aeyA"));
		double[] sales = {2.0,5.0,5.0};
		System.out.println(averageSales(sales));
		System.out.println(analyzeWord("Hunter"));
		System.out.println(analyzeAllWords(names));
		System.out.println(padArray(names,10,"Filler"));
		ArrayList<Integer> numbers = new ArrayList<>();
		numbers.add(1);
		numbers.add(2);
		numbers.add(3);
		numbers.add(9);
		numbers.add(9);
		System.out.println(remove9s(numbers));
	}

}