// Creates Page class for pageRank
public class Page {
  String id;
  double rank;
  ArrayList<Integer> outPages;
  int numOutPages;
  double newRank;
  
  // constructor
  public Page (String id) {
    this.id = id;
    this.rank = 0;
    this.outPages = new ArrayList<>();
    this.numOutPages = 0;
    this.newRank = 0;
  } // end constructor
} // end Page class
