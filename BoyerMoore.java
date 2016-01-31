/* Boyer Moore algorithm for substring
 * search. Avg time O(n+m), where n is
 * length of text, and m length of pattern.
 * The m in time comes from pre-processing.
 * Worst time O(nm).
 * This is independent of alphabet size.
 * Space complexity is ALPH_LEN*m used for
 * pre-processing pattern.
 */

public class BoyerMoore {

	//table of the indices of char occurance
	//first is index in alphabet
	//second is index immediately to left of char 
	//in pattern 
	private int[][] look_up;
	
	//length of alphabet. In this case
	//size of ASCII table
	private int ALPH_LEN = 256;
	private String txt;
	private String pat;
	private char first_char;
	
	/* @param txt is text to search in
	 * @param pat is pattern to match
	 */
	public BoyerMoore(String txt, String pat){
		this.txt = txt;
		this.pat = pat;
		this.look_up = new int[ALPH_LEN][pat.length()];
		this.first_char = pat.charAt(0);
		
		//pre-processes pattern to fill in look_up
		
		//fill in the right most index for occurance
		//of each char in pat
		for(int j = 0; j < pat.length(); j++){
			look_up[pat.charAt(j)][pat.length() - 1] = j;
		}
		
		for(int i = pat.length()-2; i > -1; i--){			
			int j = pat.length() - 1;
			//char automatically cast into int in index
			while(look_up[pat.charAt(i)][j] != 0){				
				j = look_up[pat.charAt(i)][j];
			}
			look_up[pat.charAt(i)][j] = i;
		}	
	}
	
	//returns starting index of match
	public int get_index(){
		
		int skip;
		for(int i = 0; i < txt.length() - pat.length(); i += skip){
			boolean match = true;
			skip = 1;
			for(int j = 0; j < pat.length(); j++){
				if(txt.charAt(i+j) != pat.charAt(j)){
					match = false;
					//skip to the next occurance of the char
					//in pat if applicable
					if(j == 0)
						skip = 1;
					else if(look_up[pat.charAt(i)][j] != 0){
						//index of nearest occurence of char to the left
						skip = j - look_up[pat.charAt(i)][j];
						//in this case skip is positive by construction						
					}else if(pat.charAt(j) == first_char){
						//The case when the 0 means the actual index.
						//This approach rather than filling look_up
						//with -1 saves time and makes time complexity 
						//independent of ALPH_LEN
						if(j == 1)
							skip = 1;
						else
							skip = j -1;
					}else
						//skip the part already covered
						skip = j;
					break;
				}				
			}
			if(match)
				return i;
		}
		return -1;
	}
	
}
