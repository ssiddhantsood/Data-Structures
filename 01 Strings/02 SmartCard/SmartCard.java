import java.text.DecimalFormat;

public class SmartCard {
  public final static DecimalFormat df = new DecimalFormat("$0.00");
  public final static double MIN_FARE = 0.5;
  /* enter the private fields */
  private double balance;
  private Station boardedAt;
  private boolean boarded;
  /* the one-arg constructor */
  public SmartCard(double startBalance) {
     this.boardedAt = null;
     this.balance = startBalance;
     this.boarded = false;
        
          }

 // these three getter methods only return your private data
  // they do not make any changes to your data
    public boolean getIsBoarded() {
      return boarded;
    }

    public double getBalance() {
      return balance;
    }

    public Station getBoardedAt() {
      return boardedAt;
    }

    public String getFormattedBalance() {
      return df.format(balance);
    }

    /* write the instance methods */


    
    public void board(Station all) {
      if (boarded) {
        System.out.println("Error: already boarded?!");
                     
        } 
    else if (balance < MIN_FARE) {
        
     System.out.println("Insufficient funds to board. Please add more money.");
               
   } 
    else {
            
     this.boarded = true;
     this.boardedAt = all;
     }
      return;
   }
   
   public void addMoney(double newMoney) {
     balance = balance + newMoney;
   }


   public double cost(Station all) {
      double x = 0.50 + 0.75 * (Math.abs(boardedAt.getZone() - all.getZone()));
      return x;
    }

   public void exit(Station all) {
     if (!(boarded)) {
        System.out.println("Error: Did not board?!");
            }
        
     else if (cost(all) > balance) {
     System.out.println("Insufficient funds to exit. Please add more money.");
       } 
        
     else {
     balance = balance- cost(all);
     System.out.println("From " + boardedAt.getName() + " to " + all.getName() + " costs " + df.format(cost(all)) + " Smartcard has " + getFormattedBalance());
        boardedAt = null;
        boarded = false;
     }
           }

   

}

// ***********  start a new class.  The new class does NOT have public or private.  ***/
class Station {


    public int zone;
    public String name;

    public String getName() {
        return name;
    }


    public int getZone() {
        return zone;
    }

    

    public Station() {
        this.zone = 1;
        this.name = null;
        
    }

    public Station(String n, int z) {
        this.name = n;
        this.zone = z;
    }

    



}