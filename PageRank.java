import java.util.Scanner;
import java.util.ArrayList;
import java.util.HashMap;
import java.io.FileNotFoundException;
import java.io.File;

public class PageRankTest {

  public static void rank(int pageCount, ArrayList<Page> pages, int iterations, double equilibrium) {
    double dampingFactor = 0.85;
    Page page;
    double newRank = 1 / pageCount;
    double outRank;
    // for each page, set their starting rank of 1/n
    for (int i = 0; i < pageCount; i++) {
	    pages.get(i).rank = newRank;
    }
	  int iter = 0;
	  // has the algorithm reached an equilibrium
	  boolean equilMet = false;
	  while (iter < iterations && equilMet == false) { 
		  equilMet = true;
		  // divide each page's rank amongst its out pages
		  for (int i = 0; i < pageCount; i++) {
			  page = pages.get(i);
			  
			  // if page is a sink
			  if (page.numOutPages == 0) {
				  outRank = page.rank / pageCount;
				  // distribute page rank among all pages
				  for (int j = 0; j < pageCount; j++) {
					  //if (pages.get(j) != page) {
						  pages.get(j).newRank += outRank;
					  //}
				  }
			  }
			  else { // not a sink
				  //System.out.println("no sink here");
				  outRank = page.rank / page.numOutPages;
				  for (int j = 0; j < page.numOutPages; j++) {
					  page.outPages.get(j).newRank += outRank;
				  }
			  }
		  } // end i loop 0
		  
		  // calculate each page's new rank
		  for (int i = 0; i < pageCount; i++) {
			  page = pages.get(i);
			  newRank = ((1-dampingFactor) / pageCount) + (dampingFactor * page.newRank);
			  // if the difference between old and new rank is > equil., the equil. hasn't been met
			  if (Math.abs(page.rank - newRank) > equilibrium) {
				  equilMet = false;
			  }
			  page.rank = newRank;
			  // reset newRank val
			  page.newRank = 0;
		  } // end i loop 1
		  
		  iter++;
	  } // end while loop
    System.out.println("iterations hit: " + iter);
  } // end rank()
  
  /*
   * Sorts pages in decreasing order based on their rank and prints out the result
   * @param: int pageCount, the # of nodes, or pages, in the inputed ArrayList
   * @param: ArrayList<Page> pages, the ArrayList containing the nodes of a graph
   */
  public static void finalRank(int pageCount, ArrayList<Page> pages) {
	  Page[] rankedPages = new Page[pageCount];
	  for (int i = 0; i < pageCount ; i++) {
		  rankedPages[i] = pages.get(i);
	  }
	  // sorts them in ascending
	  PageSort ob = new PageSort();
	  ob.sort(rankedPages, 0, pageCount - 1);
	  // print pages and their score, ordered by rank asc (score desc)
	  for (int i = 0; i < pageCount; i++) {
		  System.out.println(" " + (i+1) + "  id: " + rankedPages[i].id + " rankScore: " + rankedPages[i].rank);
	  }
  }

  public static void main (String[] args) throws FileNotFoundException {
    String filename = "karateDir.csv";
    Scanner scanner = new Scanner(new File(filename));
    ArrayList<Page> pages = new ArrayList<>();
    // keeps track of individual pages
    HashMap<String, Page> idPageDict = new HashMap<>();
    int pageCount = 0;
    int iterations = 100;
    double equilibrium = 0.0002;
  
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
    rank(pageCount, pages, iterations, equilibrium);
    finalRank(pageCount, pages);
    /*
    for (int i = 0; i < 5; i++) {
    	System.out.println(pages.get(i).toString());
    }
    System.out.println(pageCount);
    */
  } // end main
} // end PageRank class

// for each node:
// divide rank + increase newRank for each outgoing link
// set self.newRank to 0
// foor each node:
// rank = newRank
