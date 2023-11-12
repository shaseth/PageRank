import java.util.Scanner;
import java.util.ArrayList;
import java.util.HashMap;
import java.io.FileNotFoundException;
import java.io.File;

public class PageRank {

    // start with initial values, do pagerank - keep updated values but don't update yet
    // at end damping factor, then update scores
    // 0.002
    // 100 iterations first, see if it works then keep going
    public static void rank(double pageCount; ArrayList<Page> pages; int iterations) {
        double dampingFactor = 0.85;
        Page page;
    	double newRank = 1 / pageCount;
    	double outRank;
    	// for each page, set their starting rank of 1/n
    	for (int i = 0; i < pageCount; i++) {
	    pages.get(i).rank = newRank;
    	}
    	int iter = 0;
    	while (iter < iterations) { // add in equilibrium here
    	// divide each page's rank amongst its out pages
    	for (int i = 0; i < pageCount; i++) {
	    page = pages.get(i);
	    outRank = page.rank / page.numOutPages;
	    for (int j = 0; j < page.numOutPages; j++) {
		page.outPages.get(j).newRank += outRank;
   	    }
	} // end i loop 0
	// calculate each page's new rank
	for (int i = 0; i < pageCount; i++) {
	    page = pages.get(i);
  	    newRank = ((1 - dampingFactor) / pageCount) + (dampingFactor * page.newRank);
	    page.rank = newRank;
	    // reset newRank val
	    page.newRank = 0;
	} // end i loop 1
	iter++;
	} // end while loop
    } // end rank()

    public static void main (String[] args) throws FileNotFoundException {
	String filename = "dolphins.csv";
    	Scanner scanner = new Scanner(new File(filename));
    	ArrayList<Page> pages = new ArrayList<>();
    	HashMap<String, Page> idPageDict = new HashMap<>(); // keeps track of individual pages
    	int pageCount = 0;
    	int iterations = 100000;
  
    	// read in values and create page instances
    	while (scanner.hasNextLine() == true) {
      	    String line = scanner.nextLine();
      	    String[] spLine = line.split(",");
      	    String s1 = spLine[0];
      	    String s2 = spLine[2];
      	    Page p1 = new Page(s1);
      	    Page p2 = new Page(s2);

      	    // check if pages already exist, if not add them
      	    if (idPageDict.containsKey(s1)) {
        	p1 = idPageDict.get(s1);
     	    }
      	    else { // adding p1
		p1 = new Page(s1);
		pages.add(p1);
		pageCount++;
		idPageDict.put(s1,p1);
      	    }
	    
      	    if (idPageDict.containsKey(s2)) {
    		p2 = idPageDict.get(s2);
	    }
      	    else { // adding p2
		p2 = new Page(s2);
		pages.add(p2);
		pageCount++;
		idPageDict.put(s2,p2);
      	    }
  
      	    // don't even have to check if its directed or not because 
      	    // both always lists the outgoing page first
      	    p1.outPages.add(p2);
      	    p1.numOutPages++;
    	} // end while loop
    rank(pageCount, pages, iterations);
    } // end main
} // end PageRank class

// for each node:
// divide rank + increase newRank for each outgoing link
// set self.newRank to 0
// foor each node:
// rank = newRank
