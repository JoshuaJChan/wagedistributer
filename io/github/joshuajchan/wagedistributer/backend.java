/*=============================================================================
 * Project: WageDistributer
 * Start Date: April 11
 * Purpose: Input 3 Files, one grid of wages, one file of names per line, and one 
 file of jobs per line, and the program will recursively find the most optimal 
 wage distribution for the company - there will be more than one option available
 ==i===========================================================================
 */

package io.github.joshuajchan.wagedistributer;	//import specific package 
import java.io.IOException; 			//Import IOException
import java.util.ArrayList; 			//import the ArrayList class
import java.io.File;				//Able to represent file type
import java.io.FileReader;			//able to read files
import java.io.InputStreamReader;		//Able to read bytes and decodes them into characters using a specified charset
import java.io.BufferedReader; 			//able to read text from char.-input stream

//main class
public class backend {
	public static void main(String[] args) throws IOException{
		System.out.print("How many staff do you have?");
		int staff = intPracticalInput(); 		//# of staff
		System.out.print("How many jobs do you have?");
		int jobs = intPracticalInput();			//# of jobs
		StaffWages sw = new StaffWages(jobs, staff);
	}
	public static Integer intPracticalInput() throws IOException{ //takes natural number input
		int num;
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		while(true){			//loop to prevent nonsense values
			try { 			//try catch type error
				while(true){ 		//loops if num =< 0
					num = Integer.parseInt(br.readLine());
					if(num > 0) 	//only if number > 0, acceptable
						return num;
					System.out.println("How can you have less than 1 quantity? Please input a number greater than 0.");
				}
			} 
			catch(Exception all){
				System.out.println("Please give an integer answer");
			}
		}
	}
}

/*class: StaffWages
Purpose: takes a file and immediately give out the most viable solution
*/
class StaffWages{
	int jobs; 				
	int staff;
	//contructor to initialize # of jobs and staff
	StaffWages(int jobs, int staff){
		this.jobs = jobs;		//# of jobs
		this.staff = staff;		//# of staff
	}
	//takes string name of file, and converts it to file type
	public File fileNameReader(String s) throws IOException{
		File fileInDirectory = new File(s);
		return fileInDirectory;
	} 
	//takes file and splits each line into an arraylist of int arrays;Matrix
	public ArrayList<int[]> wageToMat(File file) throws IOException{
		BufferedReader filebr = new BufferedReader(new FileReader(file)); //reads file contents 
		ArrayList<int[]> wageMat = new ArrayList<int[]>();		//array of jobs vs staff
		String currLine;

		while((currLine = filebr.readLine()) != null){ 			//declare current line as a String and the value musn't be null
			String[] temp = currLine.split(" ");			//can not file.readLine() again, else will go to next line, must tokenize
			int[] wageRow = new int[temp.length];			//array for the tokens of a row of correct type int
			for(int i = 0;i<temp.length;i++)
				wageRow[i] = Integer.parseInt(temp[i]);		//parse to int
			wageMat.add(wageRow);		//add to list of arrays
		}
		return wageMat;

	}
}
