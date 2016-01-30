/* Rabin-Karp for string search.
 * Avg time O(n), with rolling hash function.
 * Worst time O(mn), where n = length(txt), 
 * m = length(pat)
 */
public class RabinKarp {

	private String pat; //pattern
	private String txt; //text
	//some prime for hash function
	private final int PRIME = 877;
	
	public RabinKarp(String pat, String txt){
		this.pat = pat;
		this.txt = txt;
	}
	
	/* Rolling hash function in order
	 * to achive time better than O(mn)
	 * @param prev_hs is hash key for string
	 * one char shifted to left from s.
	 * @param c is that shifted character
	 */
	private int hash(String str, char c, int prev_hs){
		int str_len = str.length();
		//use ASCII values to compute hash
		return (prev_hs - (int)c + 
				(int)str.charAt(str_len-1)) % PRIME;
	}
	
	/* Initial hash function 
	 */
	private int hash(String str){
		int hs = 0;
		for(int i = 0; i < str.length(); i++){
			hs = hs + (int)str.charAt(i);
		}
		return hs % PRIME;
	}
	
	/* Returns position of first match
	 * 
	 */
	public int find_match(){
		int pat_hs = hash(pat);
		int txt_hs = hash(txt.substring(0, pat.length()));
		int txt_len = txt.length();
		int pat_len = pat.length();
		
		for(int i = 0; i < txt_len - pat_len; i++){
			if(pat_hs == txt_hs){
				boolean found = true;
				//check each character
				for(int j = 0; j < pat.length(); j++){
					if(txt.charAt(i+j) != pat.charAt(j)){
						found = false;
					}					
				}
				//found match
				if(found)
					return i;				
			}
			//to avoid array out of bounds for next line
			if(i == txt_len-pat_len-1)
				break;
			//use rolling hash function
			txt_hs = hash(txt.substring(i+1, i+1+pat_len),
					txt.charAt(i), txt_hs);
		}
		return -1;
	}
	
}
