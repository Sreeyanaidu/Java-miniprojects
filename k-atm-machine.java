import java.util.*;
class Customer
{
    String Name;
    private int PIN;
    Double Balance;
    ArrayList<String> Transactions;
    public Customer(String Name,int PIN,Double Balance)
    {
        this.Name = Name;
        this.PIN = PIN;
        this.Balance = Balance;
        Transactions = new ArrayList<String>();
    }
    public void Update(String Statement)
    {
        Transactions.add(Statement);
    }
    public void Update_Balance(double Withdrawn)
    {
        Balance-=Withdrawn;
    }
    public void set_PIN(int PIN)
    {
        this.PIN = PIN;
    }
    public boolean PIN_Verification(int veri_PIN)
    {
        if(veri_PIN==PIN){return true;}
        else{return false;}
    }
    public String toString()
    {
        return "-----DETAILS------------"+"\nName: "+Name+"\nRemaining Balance: "+Balance;
    }
}

class ATM
{
    Double Machine_Balance;
    ArrayList<String> Machine_Transactions_History;
    Double Customer_Request;
    Scanner s = new Scanner(System.in);
    public boolean Resource_Request_Algorithm(Double Request,Customer c)
    {
        if(Request<=c.Balance)
        {
            if(Request<=Machine_Balance)
            {
                return true;
            }
            else
            {
                System.out.println("Insufficient Machine Balance!...");
                return false;
            }
        }
        else
        {
            System.out.println("Insufficient Account Balance!....");
            return false;
        }
    }
    public ATM()
    {
        Machine_Balance = 1000000.0;
        Machine_Transactions_History = new ArrayList<String>();
    }

    synchronized public void Withdrawal(Customer c)
    {
        System.out.println("-----WITHDRAWAL------");
        System.out.print("Enter the Amount: ");
        Customer_Request = s.nextDouble();
        System.out.print("Enter Your PIN(XXXX): ");
        int veri_PIN = s.nextInt();
        boolean result = c.PIN_Verification(veri_PIN);
        if (result==true)
        {
            System.out.println("VALID PIN ENTRY!.....");
        }
        else
        {
            System.out.println("-----INVALID PIN------");
            return;
        }
        boolean veri_Req = Resource_Request_Algorithm(Customer_Request,c);
        if(veri_Req == true)
        {
            System.out.println("TRANSACTION GRANTED.....");
            System.out.println("WithDrawing the Requested Amount.....");
            c.Update_Balance(Customer_Request);
            Machine_Balance-=Customer_Request;
            Machine_Transactions_History.add("Name: "+c.Name+" Amt Withdrawn: "+Customer_Request);
            c.Transactions.add("Amt Withdrawn: "+Customer_Request);
            System.out.println(c);
            return;
        }
        else
        {
            return;
        }
    }
}


public class Project_atm
{
    public static void main(String[] args)
    {
          Scanner s  = new Scanner(System.in);
          int opt;
          ATM Machine  = new ATM();
          Customer c1 = new Customer("User", 1234,500000.0);
          boolean use = true;
          while (use)
          {
              System.out.println("-----WELCOME TO K_BANK_ATM------");
              System.out.println("-----AVAILABLE SERVICES------");
              System.out.print("1) Enter 1 for Amount Withdrawal\n2) Enter 2 for Account Details\n3) Enter 3 for viewing Account Transaction History\n4) Enter 4 for viewing ATM Transaction History\n5) Enter 5 for Exit");
              System.out.print("\nEnter your choice: ");
              opt = s.nextInt();
              switch(opt)
              {
                  case 1:
                    Machine.Withdrawal(c1);
                    break;
                  case 2:
                    System.out.println(c1);
                    break;
                  case 3:
                    System.out.println("-----ACCOUNT TRANSACTION HISTORY-----");
                    System.out.println(c1.Transactions);
                    break;
                  case 4:
                  System.out.println("-----ATM MACHINE TRANSACTION HISTORY-----");
                  System.out.println(Machine.Machine_Transactions_History);
                    break;
                  case 5:
                    use = false;
                    System.out.println("-----THANKYOU FOR USING-----");
                    break;
                  default:
                    System.out.println("----INVALID OPTION-----");
                    break;
              }
          }
          s.close();

    }
}
