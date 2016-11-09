/* Calculate the Levenshtein distance between two 
   input strings
*/

public class Levenshtein{
	 
        public int distance(String s1, String s2) {
	
	//Check if equal
        if (s1.equals(s2)){
        return 0;}

        int v0[] = new int[s2.length() + 1];
        int v1[] = new int[s2.length() + 1];
        int vtemp[];
	//Set the initial values of v0[]
        for (int i = 0; i < v0.length; i++) {
            v0[i] = i;
        }
	//Loop through every element in two strings
        for (int i = 0; i < s1.length(); i++) {
            v1[0] = i + 1;
            for (int j = 0; j < s2.length(); j++) {
		//check each bit, record to cost
                int cost = (s1.charAt(i) == s2.charAt(j)) ? 0 : 1;
		//Find the minumim operations 
                v1[j + 1] = Math.min(
                        v1[j] + 1, 		//Insertion Times             
                        Math.min(
                                v0[j + 1] + 1,  //Deletion Times 
                                v0[j] + cost)); //Subsititution Times
            }
            
            // copy v1 (current row) to v0 (previous row) for next iteration
            //System.arraycopy(v1, 0, v0, 0, v0.length);
            
            // Exchange two strings' values
            vtemp = v0;
            v0 = v1;
            v1 = vtemp;
                
        }
	//Return the last bit of v0 as the Levenshtein Distance
        return v0[s2.length()];
    }
}
