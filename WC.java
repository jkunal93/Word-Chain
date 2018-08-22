import java.io.*;
import java.util.*;


public class WC{

	
	public static void main(String args[]){
		
		boolean ch = false;
		boolean sw = false;
		String start_word = "";
		String final_word = "";
		
		
		
		do{
				Scanner choice = new Scanner(System.in);  
				
				System.out.println("\nChoose the type of Word-Chain: \n1. 4-letter\n2. 345-letter\n3. Exit");
				int i = choice.nextInt();
				
				if(i==3){
					ch = true;
					System.out.println("Exit");
					System.exit(0);
				}else if(i<=0 || i>3){
					System.out.println("Invalid Choice. Try again....!!");
				}
			
				do{
		            Scanner in = new Scanner(System.in);
		            System.out.println("Please enter the starting word: ");
		            start_word = in.nextLine();
		            System.out.println("Please enter the target word: ");
		            final_word = in.nextLine();
	          
		            
		            if(start_word.equals(final_word)) {
		            	System.out.println("Error: Start-word and Final-word are same. Enter different words.\n");
		            }else {
		            	sw = true;
		            	
		            }
				
				}while(!sw);
				
				if(i==1){
					fourLetter(start_word, final_word);
				}else if(i==2){
					multipleLetter(start_word, final_word);
				}
	            
		}while(!ch);
	
	}		

// 4-letter word-chain main method	
public static void fourLetter(String start, String end){
	
	System.out.println("Four Letter Word Chain:\n");
	if(start.length()!=end.length() || start.length()!=4 || end.length()!=4){
		System.out.println("Invalid Words. Please type 4-letter word");
		//return false;
	}else{
	
		FourLetter fl = new FourLetter(start,end);
		List<String> chain = fl.findPath(start,end);
		
		if(chain == null){
			
			System.out.println("-------------Word Chain doesn't exist-------------");
		
		}else{	//Print the chain
			
	    	for (int i = 0; i < chain.size(); i++) {
	    		System.out.println(chain.get(i));}
	    	}
	    	
			System.out.println("\nChain Length: "+(chain.size()-1));
		
		}
	}

// 345-letter word-chain main method
public static void multipleLetter(String start, String end){
			System.out.println("345-Letter Word Chain:\n");
			
		if(start.length()!=end.length() || start.length()!=4 || end.length()!=4){
			System.out.println("Invalid Words. Please type 4-letter word");
			//return false;
		}else{	
			
			MultipleLetter ml = new MultipleLetter(start,end);
			List<String> mchain = ml.findChain(start,end);
			
			if(mchain == null){
				
				System.out.println("-------------Word Chain doesn't exist-------------");
			
			}else{	//Print the chain
				
		    	for (int i = 0; i < mchain.size(); i++) {
		    		System.out.println(mchain.get(i));}
		    	}
		    	
				System.out.println("\nChain Length: "+(mchain.size()-1));
		}
}
			
}
	
	
	
	
	
