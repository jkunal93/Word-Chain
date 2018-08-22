import java.io.*;
import java.util.*;

public class MultipleLetter{
	
	List<String> m3list;
	List<String> m4list;
	List<String> m5list;
	
	public MultipleLetter(String start, String end){
		
		//System.out.println("Inside function");
		
		m3list = dictgraph(start.length(), start, "m3");
		m4list = dictgraph(start.length(), start, "m4");
		m5list = dictgraph(start.length(), start, "m5");
		//System.out.println(mlist);
		
	}
	
	// Get words from dictionary
	List<String> dictgraph(int l, String start, String m){
		
		List<String> words = new ArrayList<String>();
		
		if(m == "m4"){
			
			try (BufferedReader br4 = new BufferedReader(new FileReader("dict/old_four.txt"))) {
			            
						String w = "";
			            while ((w = br4.readLine()) != null) {                
			                
			            	words.add(w);
			                
						}
						
	        } catch (IOException ioe) {
	        	System.out.println("four letter dict loading error");
	            ioe.printStackTrace();
	        } 
			
		}
		
		if(m == "m3"){
			
			try (BufferedReader br3 = new BufferedReader(new FileReader("dict/three_lc.txt"))) {
	            
				String w = "";
	            while ((w = br3.readLine()) != null) {                
	                
	                    words.add(w);
	                    //System.out.println(w);
	                
				}
				
			} catch (IOException ioe) {
				System.out.println("three letter dict loading error");
			    ioe.printStackTrace();
			}
			
		}
		
		if(m == "m5"){
			
			try (BufferedReader br = new BufferedReader(new FileReader("dict/five_lc.txt"))) {
	            
				String w = "";
	            while ((w = br.readLine()) != null) {                
	               
	                    words.add(w);
	            }
				
			} catch (IOException ioe) {
				System.out.println("five letter dict loading error");
			    ioe.printStackTrace();
			}
			//System.out.println(words);
			
		}
		return words;
	}
	
	
	List<String> findChain(String start, String end){
		
		Map<String, String> hash = new HashMap<>();
		Queue<String> q = new LinkedList<>();
		List<String> path = new ArrayList<>();
		HashMap hp = new HashMap();
		
		q.add(start);
		//System.out.println("q= "+q);
		String w1 = null;
		int i=0;
		List<String> ls = new ArrayList<String>();
		while (!q.isEmpty() && !(w1 = q.remove()).equals(end)) {
			//System.out.println("Current Word "+w1);		
			List<String> mid_words = getValidWords(w1,end);
			//System.out.println("mid_words:"+i+" "+mid_words);
			//ls = mid_words;
			for (String w2 : mid_words) {
				
				if (!hash.containsKey(w2)) {
					//ystem.out.println("Pushed Word(w2) "+w2+" "+w1);
					hash.put(w2, w1);
					//System.out.println(hash.get("flea"));
					q.add(w2);
				}
			}i++;
		}
		//System.out.println(" "+ ls);
		
		//System.out.println(hash);
		
		if (!(hash.containsKey(end))) {
			System.out.println("inside null");
			return null;	
		}
		
		String last = end;
		
		while (!last.equals(start)) {
			path.add(0, last);
			last = hash.get(last);
		}

		path.add(0, start);
		//System.out.println(q);
		System.out.println(path.size());
		return path;
		
		
	}
	
	
	List<String> getValidWords(String word, String end){
		
		List<String> valid = new ArrayList<>();
		
		//if(word=="tic"){System.out.println("I got here");}
		for (String this_word : m3list) {
			if(dif(word, this_word)==10){
				//System.out.println(word);
				valid.add(this_word);
				//System.out.println(valid);
			}
			if(dif(word, this_word)==1){
				valid.add(this_word);
				//System.out.println(valid);
			}
		}
		
		for (String this_word : m4list) {
			
			if(dif(word, this_word)==10){
				valid.add(this_word);
				//System.out.println(valid);
			}
			if(dif(word, this_word)==1){
				valid.add(this_word);
				//System.out.println(valid);
			}
		}
		
		for (String this_word : m5list) {
			
			if(dif(word, this_word)==10){
				valid.add(this_word);
				//System.out.println(valid);
			}
			if(dif(word, this_word)==1){
				valid.add(this_word);
				//System.out.println(valid);
			}
		}
		//System.out.println(valid);
		
		return valid;
		
	}
	
	// Calculates difference between two words
		int dif(String w1, String w2){
			
			int d=0;
			if ((w1.length()==4 && w2.length()==3)||(w1.length()==5 && w2.length()==4)){
				String w="";
				for (int n = 0; n < w1.length(); n++) {
					w = w1.substring(0,n)+w1.substring(n+1);
					if (w.equals(w2)) {
						d=10;
					}
				}
			}
			
			if (w1.length()==w2.length()){
				
				//System.out.println(w1+" "+w2);
				for (int n = 0; n < w1.length(); n++) {
					
					if (w1.charAt(n) != w2.charAt(n)) {
						d++;
					}
				}
			}

			if ((w1.length()==4 && w2.length()==5)||(w1.length()==3 && w2.length()==4)){
				String w="";
				for (int n = 0; n < w2.length(); n++) {
					w = w2.substring(0, n)+w2.substring(n+1);
					if (w.equals(w1)) {
						d=10;
					}
				}
			}
			
			return d;
			
		}
		
	
	
}