// start with initial values, do pagerank - keep updated values but don't update yet
// at end damping factor, then update scores
// 0.002
// 100 iterations first, see if it works then keep going
public class Page {
  double rank;
  int[] outPages;
  int numOutPages;
  double newRank;
  
  // constructor
  public Page (int startRank) {
    this.rank = startRank;
  }
} // end Page class

public class pageRank {
  // read in values and create page instances

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

