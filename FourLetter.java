import java.io.*;
import java.util.*;

public class FourLetter{
	
	List<String> list;
	
	public FourLetter(String start, String end){
		//System.out.println(start.length());
		list = fromdict(start.length());	
	}
	
	// Reads all the words from dictionary
	List<String> fromdict(int l){
		
		List<String> words = new ArrayList<String>();
		
		try (BufferedReader br = new BufferedReader(new FileReader("old_four.txt"))) {
            
			String w = "";
            while ((w = br.readLine()) != null) {                
                
				if (w.length() == l) {
                    words.add(w);
                }
			}
			
        } catch (IOException ioe) {
            ioe.printStackTrace();
        } 

        	return words; 
	}
	
	// Finds Path from Start-Word to Final-Word
	List<String> findPath(String start, String end){
		
		List<String> path = new ArrayList<>();
		Map<String, String> hash = new HashMap<>();
		Queue<String> queue = new LinkedList<>();
		
		queue.add(start);
		String w1 = null;
		
		while (!queue.isEmpty() && !(w1 = queue.remove()).equals(end)) {
			
			List<String> mid_words = getWords(w1);
			
			for (String w2 : mid_words) {
				
				if (!hash.containsKey(w2)) {
					
					hash.put(w2, w1);
					queue.add(w2);
				}
			}
		}
		
		if (!(hash.containsKey(end))) {
			System.out.println("inside null");
			return null;	
		}

		String ww = end;
		
		while (!ww.equals(start)) {
			path.add(0, ww);
			ww = hash.get(ww);
		}

		path.add(0, start);
		
		return path;
		
	}
	int k = Integer.MAX_VALUE;
	// Gets the words that has 1-letter difference from the given word
	List<String> getWords(String word){
		
		List<String> valid_words = new ArrayList<>();
		
		for (String this_word : list) {
			
			if (diff(word, this_word) == 1) {
				valid_words.add(this_word);
			}
		}

		return valid_words;
	}
	
	// Calculates the difference between two words
	int diff(String w1, String w2){
		
		int d=0;
		for (int n = 0; n < w1.length(); n++) {
			
			if (w1.charAt(n) != w2.charAt(n)) {
				d++;
			}
		}

		return d;
	}
	
	
}
