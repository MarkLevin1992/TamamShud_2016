/* This is the main function of this program.
   This file contains the following contents:
   1. Read files in and define the somerton man's code
   2. De-accent letters
   3. Extract first letter from each word
   4. Divide the string into groups of a fixed length 
   5. Call Levenshtein method to get the Levenshtein Distance
   6. Call Simhash method to get the Simhash result
*/

import java.util.Scanner;
import java.io.*;
import java.util.Arrays;
import java.text.Normalizer;
import java.util.regex.Pattern;
import java.util.InputMismatchException;






public class Main {

	public static void main(String args[]) {

		String somertonCode_v1 = "WRGOABABDMLIAOIWTBIMPANETPMLIABOAIAQCITTMTSAMSTGAB";
		String somertonCode_v2 = "MRGOABABDMLIAOIMTBIMPANETPMLIABOAIAQCITTMTSAMSTGAB";
		//somertonCode_v2 = new String(new char[24]).replace("\0",somertonCode_v2);
		Main f = new Main();
		
		divideWords divided = new divideWords();
		Levenshtein l = new Levenshtein();

		//Read all files in

		//Here read all the War and Peace texts in
		File s_En_l = new File("/home/Mark_Levin/Final/long_en.txt");		
		File s_It_l = new File("/home/Mark_Levin/Final/long_ita.txt");
		File s_Ge_l = new File("/home/Mark_Levin/Final/long_ge.txt");
		File s_Fr_l = new File("/home/Mark_Levin/Final/long_fra.txt");
		File s_Pq_l = new File("/home/Mark_Levin/Final/long_port.txt");
		File s_La_l = new File("/home/Mark_Levin/Final/long_latin.txt");
		File s_Sp_l = new File("/home/Mark_Levin/Final/long_span.txt");
		File s_Tr_l = new File("/home/Mark_Levin/Final/long_turk.txt");
		File s_Po_l = new File("/home/Mark_Levin/Final/long_poli.txt");
		
		//Here read all the UDHR testx in
		File s_It = new File("/home/Mark_Levin/Final/itn.txt");
		File s_Ge = new File("/home/Mark_Levin/Final/ger.txt");
		File s_Tr = new File("/home/Mark_Levin/Final/trk.txt");
		File s_Fr = new File("/home/Mark_Levin/Final/frn.txt");
		File s_En = new File("/home/Mark_Levin/Final/eng.txt");
		File s_Pq = new File("/home/Mark_Levin/Final/pql.txt");
		File s_La = new File("/home/Mark_Levin/Final/ltn.txt");
		File s_Sp = new File("/home/Mark_Levin/Final/spn.txt");
		File s_Po = new File("/home/Mark_Levin/Final/pol.txt");
		

		//Get first letter of each word
		String firstLetters_En = f.extractLetters(f.readText(s_En));
		String firstLetters_It = f.extractLetters(f.deAccent(f.readText(s_It)));
		String firstLetters_Ge = f.extractLetters(f.deAccent(f.readText(s_Ge)));
		String firstLetters_Fr = f.extractLetters(f.deAccent(f.readText(s_Fr)));
		String firstLetters_Pq = f.extractLetters(f.deAccent(f.readText(s_Pq)));
		String firstLetters_La = f.extractLetters(f.deAccent(f.readText(s_La)));
		String firstLetters_Sp = f.extractLetters(f.deAccent(f.readText(s_Sp)));
		String firstLetters_Po = f.extractLetters(f.deAccent(f.readText(s_Po)));
		String firstLetters_Tr = f.extractLetters(f.deAccent(f.readText(s_Tr)));

		String firstLetters_En_l = f.extractLetters(f.readText(s_En_l));
		String firstLetters_It_l = f.extractLetters(f.deAccent(f.readText(s_It_l)));
		String firstLetters_Ge_l = f.extractLetters(f.deAccent(f.readText(s_Ge_l)));
		String firstLetters_Tr_l = f.extractLetters(f.deAccent(f.readText(s_Tr_l)));
		String firstLetters_Fr_l = f.extractLetters(f.deAccent(f.readText(s_Fr_l)));
		String firstLetters_Pq_l = f.extractLetters(f.deAccent(f.readText(s_Pq_l)));
		String firstLetters_La_l = f.extractLetters(f.deAccent(f.readText(s_La_l)));
		String firstLetters_Sp_l = f.extractLetters(f.deAccent(f.readText(s_Sp_l)));
		String firstLetters_Po_l = f.extractLetters(f.deAccent(f.readText(s_Po_l)));
	        
 		//Destruct the firstletter string into fixed length groups
		String parts_En[] = firstLetters_En.split("(?<=\\G.{50})");
		String parts_It[] = firstLetters_It.split("(?<=\\G.{50})");		
		String parts_Fr[] = firstLetters_Fr.split("(?<=\\G.{50})");	
		String parts_Ge[] = firstLetters_Ge.split("(?<=\\G.{50})");
		String parts_Pq[] = firstLetters_Pq.split("(?<=\\G.{50})");
		String parts_La[] = firstLetters_La.split("(?<=\\G.{50})");
		String parts_Sp[] = firstLetters_Sp.split("(?<=\\G.{50})");
		String parts_Tr[] = firstLetters_Tr.split("(?<=\\G.{50})");
		String parts_Po[] = firstLetters_Po.split("(?<=\\G.{50})");



		String parts_En_l[] = firstLetters_En_l.split("(?<=\\G.{50})");
		String parts_It_l[] = firstLetters_It_l.split("(?<=\\G.{50})");		
		String parts_Fr_l[] = firstLetters_Fr_l.split("(?<=\\G.{50})");	
		String parts_Ge_l[] = firstLetters_Ge_l.split("(?<=\\G.{50})");
		String parts_Pq_l[] = firstLetters_Pq_l.split("(?<=\\G.{50})");
		String parts_La_l[] = firstLetters_La_l.split("(?<=\\G.{50})");
		String parts_Sp_l[] = firstLetters_Sp_l.split("(?<=\\G.{50})");
		String parts_Tr_l[] = firstLetters_Tr_l.split("(?<=\\G.{50})");
		String parts_Po_l[] = firstLetters_Po_l.split("(?<=\\G.{50})");

		// This part is for the Simhash Algorithm		
		// Get the input string tokenized 
		String SH_En = divided.tokens(firstLetters_En_l);
		String SH_It = divided.tokens(firstLetters_It_l);
		String SH_Fr = divided.tokens(firstLetters_Fr_l);
		String SH_Ge = divided.tokens(firstLetters_Ge_l);
		String SH_Pq = divided.tokens(firstLetters_Pq_l);
		String SH_La = divided.tokens(firstLetters_La_l);
		String SH_Sp = divided.tokens(firstLetters_Sp_l);
		String SH_Tr = divided.tokens(firstLetters_Tr_l);
		String SH_Po = divided.tokens(firstLetters_Po_l);
		String SH_code_v2 = divided.tokens(somertonCode_v1);		
	        
		// Calculate the HammingdDistance of Simhashed strings
		System.out.println(s.hammingDistance(SH_code_v2,SH_En));
		System.out.println(s.hammingDistance(SH_code_v2,SH_It));
		System.out.println(s.hammingDistance(SH_code_v2,SH_Fr));
		System.out.println(s.hammingDistance(SH_code_v2,SH_Ge));
		System.out.println(s.hammingDistance(SH_code_v2,SH_Pq));
		System.out.println(s.hammingDistance(SH_code_v2,SH_La));
		System.out.println(s.hammingDistance(SH_code_v2,SH_Sp));
		System.out.println(s.hammingDistance(SH_code_v2,SH_Tr));
		System.out.println(s.hammingDistance(SH_code_v2,SH_Po));


		
		//This part is for the Levenshtein Distance calculate, comment if not needed
		double levDis[][] = new double[9][100];
			for(int i = 1; i<20; i+=2){
				for(int j = 22;j<32; j+=1){
					levDis[0][(i-1)*5+j-22] = l.distance(parts_En_l[i], parts_En_l[j]);
					levDis[1][(i-1)*5+j-22] = l.distance(parts_En_l[i], parts_It_l[j]);
					levDis[2][(i-1)*5+j-22] = l.distance(parts_En_l[i], parts_Fr_l[j]);
					levDis[3][(i-1)*5+j-22] = l.distance(parts_En_l[i], parts_Ge_l[j]);
					levDis[4][(i-1)*5+j-22] = l.distance(parts_En_l[i], parts_Pq_l[j]);
					levDis[5][(i-1)*5+j-22] = l.distance(parts_En_l[i], parts_La_l[j]);
					levDis[6][(i-1)*5+j-22] = l.distance(parts_En_l[i], parts_Sp_l[j]);
					levDis[7][(i-1)*5+j-22] = l.distance(parts_En_l[i], parts_Tr_l[j]);
					levDis[8][(i-1)*5+j-22] = l.distance(parts_En_l[i], parts_Po_l[j]);
				}
			}
	
		try(  PrintWriter out = new PrintWriter( "filename.txt" )  ){
			for(int i = 0;i<100;i++){ 
				String s = Arrays.toString(levDis[i]);
				System.out.println(s);
				out.println(s);
			}
		}
		catch(FileNotFoundException ex){}
		}
		//Read the Levenshtein result into a txt file
		String readText(File fileName){
		BufferedReader br = null;
		String text = "";
		try {
			String sCurrentLine;
			br = new BufferedReader(new FileReader(fileName));
			while ((sCurrentLine = br.readLine()) != null) {
				text = text.concat(""+sCurrentLine);
			}
		} 
		catch (IOException e) {e.printStackTrace();}
		finally {
			try {
				if (br != null)br.close();
			} 
			catch (IOException ex) {
				ex.printStackTrace();
			}
		}
		return text;
	
	}
	
	
	//Method for removing accent letters in some languages
	String deAccent(String text) {
		String nfdNormalizedString = Normalizer.normalize(text, Normalizer.Form.NFD); 
		Pattern pattern = Pattern.compile("\\p{InCombiningDiacriticalMarks}+");
		return pattern.matcher(nfdNormalizedString).replaceAll("");
	}
	
	//Method for getting first letter of each word in a text
	String extractLetters(String text) {
		String firstLetters = "";
		text = text.replaceAll("[^\\p{ASCII}]", "").replaceAll(".“–…(,:\"\'!)--;”/?›»«", "").replaceAll("[^a-zA-Z]", " ").toUpperCase(); 
		//System.out.println(text);
		for(String s : text.split(" "))
			 {	
				try{
				firstLetters += s.charAt(0);
				}
				catch(StringIndexOutOfBoundsException e){}
			 }
		return firstLetters;
	}
		
}


		  

