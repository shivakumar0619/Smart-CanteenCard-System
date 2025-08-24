import java.util.Scanner;

class Canteen 
{
    String customerName;
    long cardId;           
    double bal;            
    String[] boughtItems;
    int itemCount;
    double totalExpended;

    Canteen(String name, long id, double bal, int maxItems) 
    {
        this.customerName = name;
        this.cardId = id;
        this.bal = bal;
        this.boughtItems = new String[maxItems];
        this.itemCount = 0;
        this.totalExpended = 0;
    }

    void buyItem(String item, double price, int quantity) 
    {
        double totalPrice = price * quantity;

        if (bal >= totalPrice) 
        {
            bal -= totalPrice;
            totalExpended += totalPrice;
            boughtItems[itemCount] = "Item: " + item + "  Qty: " + quantity + "  Price: " + totalPrice + "/-";
            itemCount++;
            System.out.println(customerName + " (Card ID: " + cardId + ") bought " + quantity + " " + item + " for " + totalPrice + "/-");
            System.out.println("Remaining Balance: " + bal + "/-");
        } 
        else 
        {
            System.out.println("Insufficient Balance! " + bal + "/- Cannot buy " + item);
        }
    }

    void showReceipt() 
    {
        System.out.println("\n  Receipt ");
        System.out.println("Customer: " + customerName);
        System.out.println("Card ID: " + cardId);
        if (itemCount == 0) 
        {
            System.out.println("No items bought.");
        } 
        else 
        {
            System.out.println("Items Bought:");
            for (int i = 0; i < itemCount; i++) 
            {
                System.out.println(" - " + boughtItems[i]);
            }
        }
        System.out.println("Total Spent: " + totalExpended + "/-");
        System.out.println("Balance Left: " + bal + "/-");
    }
}

public class CanteenSystem1
{
    public static void main(String[] args) 
    {
        Scanner sc = new Scanner(System.in);

        String[] items = {"", "Tea", "Coffee", "Sandwich", "Juice", "Puffs", "Samosa", "Cakes"};
        double[] prices = {0, 10, 15, 30, 25, 20, 14, 40};

        System.out.println("Canteen Menu:");
        for (int i = 1; i < items.length; i++) 
        {
            System.out.println(i + ". " + items[i] + " - " + prices[i] + "/-");
        }

        System.out.println("\nWelcome to the Canteen System!");
        System.out.print("Enter your name: ");
        String name = sc.nextLine();

        System.out.print("Enter your Card ID Number (7 digits): ");
        long id = sc.nextLong();
        while (String.valueOf(id).length() != 7) {
            System.out.println("Card ID must be exactly 7 digits! Please enter again: ");
            id = sc.nextLong();
        }

        System.out.print("Enter initial balance of your Canteen Card: ");
        double bal = sc.nextDouble();

        Canteen card = new Canteen(name, id, bal, 50);

        int choice = 1;
        while (choice != 0 && card.bal > 0) 
        {
            System.out.println("\nMenu:");
            for (int i = 1; i < items.length; i++) 
            {
                System.out.println(i + ". " + items[i] + " - " + prices[i] + "/-");
            }
            System.out.println("0. Exit");
            System.out.print("\nEnter item number to buy (or 0 to exit): ");
            choice = sc.nextInt();

            if (choice == 0) break;
            if (choice >= 1 && choice < items.length) 
            {
                System.out.print("Enter quantity: ");
                int qty = sc.nextInt();
                if (qty > 0) 
                {
                    card.buyItem(items[choice], prices[choice], qty);
                } 
                else 
                {
                    System.out.println("Quantity must be greater than 0.");
                }
            } 
            else 
            {
                System.out.println("Invalid choice!");
            }
        }

        card.showReceipt();
        sc.close();
    }
}
