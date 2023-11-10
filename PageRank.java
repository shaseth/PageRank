import java.util.Scanner;
import java.util.ArrayList;

public class PageRank {

  // start with initial values, do pagerank - keep updated values but don't update yet
  // at end damping factor, then update scores
  // 0.002
  // 100 iterations first, see if it works then keep going
  public static void rank() {

  } // end rank()

  public static void main (String[] args) {
    Scanner scanner = new Scanner(System.in);
    ArrayList<Page> pages = new ArrayList<>();
    int pageCount = 0;
  
    // read in values and create page instances
    while (scanner.hasNextLine() == true) {
      String line = scanner.nextLine();
      String[] spLine = line.split(",");
      String s1 = spLine[0];
      String s2 = spLine[2];
      Page p1 = new Page(s1);
      Page p2 = new Page(s2);
  
      // don't even have to check if its directed or not because 
      // both always lists the outgoing page first
      p1.outPages.add(p2);
      p1.numOutPages++;
      pages.add(p1);
      pageCount++;
    } // end while loop
  } // end main
} // end PageRank class

// for each node:
// divide rank + increase newRank for each outgoing link
// set self.newRank to 0
// foor each node:
// rank = newRank
