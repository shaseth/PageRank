import java.util.ArrayList;

public class Page {
  String id;
  double rank;
  ArrayList<Page> outPages;
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
  
  // for debugging purposes
  public String toString() {
    return "Id: " + this.id + "\nRank: " + this.rank + "\n# of Out Pages: " + this.numOutPages;
  }

} // end Page class
