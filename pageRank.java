// start with initial values, do pagerank - keep updated values but don't update yet
// at end damping factor, then update scores
// 0.002
// 100 iterations first, see if it works then keep going

import java.util.Scanner;

public class Page {
  double rank;
  ArrayList<Integer> outPages;
  int numOutPages;
  double newRank;
  
  // constructor
  public Page () {
    this.rank = 0;
    this.outPages = new ArrayList<>();
    this.numOutPages = 0;
    this.newRank = 0;
  } // end constructor
} // end Page class

public class pageRank {
  Scanner scanner = new Scanner(System.in);
  ArrayList<Page> pages = new ArrayList<>;
  int pageCount = 0;
  
  // read in values and create page instances
  while (scanner.hasNextLine == True) {
    String line = scanner.nextLine();
    String[] spLine = line.split(",");
    String p1 = spLine[0];
    String p2 = spLine[2];
    Page p1 = new Page();
    Page p2 = new Page();
  
    // don't even have to check if its directed or not because 
    // both always lists the outgoing page first
    p1.outPages.add(p2);
    p1.numOutPages++;
    pages.add(p1);
    pageCount++;
  } // end while loop

  public static void rank() {

  } // end rank()

  public static void main (String[] args) {
    
  }
} // end pageRank class

// for each node:
// divide rank + increase newRank for each outgoing link
// set self.newRank to 0
// foor each node:
// rank = newRank

