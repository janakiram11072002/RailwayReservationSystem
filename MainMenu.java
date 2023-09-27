import java.util.Scanner;

public class MainMenu {
    static Scanner s = new Scanner(System.in);
    static boolean looping = true;

    public static void main(String[] args) {
        RailwayReservationBooking Railway = new RailwayReservationBooking();

        while (looping) 
        {
            System.out.println("\n1.Book Ticket" +
                    "\n2.Calcel Ticket" +
                    "\n3.TicketAvailability" +
                    "\n4.PrepareChart" +
                    "\n5.Exit");
            System.out.println("\n Enter Yout Choice : ");
            var choice = s.nextInt();
            switch (choice) 
            {
                case 1: 
                    {
                        System.out.println("Enter Passanger Name : ");
                        s.next();
                        String name = s.nextLine();
                        System.out.println("Enter Age of the Passanger : ");
                        int age = s.nextInt();
                        System.out.println("Please Enter Your Gender : ");
                        char gender = s.next().charAt(0);
                        System.out.println("A - Ac Coach\nN - Non-Ac Coach\nS - Sitter\nEnter your preferred Coach : ");
                        char coach = s.next().charAt(0);
                        System.out.println("Mobile Number : ");
                        long mobileNo = s.nextLong();
                        if(Railway.Bookticket(name,age,gender,coach,mobileNo))
                        {
                            System.out.println("Ticket is Confrimed");
                        }
                    }
                    break;
                case 2: 
                    {
                        System.out.println("Enter your Passanger Id  : ");
                        int PassangerId = s.nextInt();
                        if(Railway.TicketCancellation(PassangerId))
                        {
                            System.out.println("Your Ticket is Cancelled.");
                        }
                    }
                    break;
                case 3: 
                    {
                        Railway.TicketAvailability();
                    }
                    break;
                case 4: 
                    {
                        Railway.prepareChart();
                    }
                    break;
                case 5: 
                    {
                        looping = false;
                    }
                    break;
                default:
                    System.out.println("Enter a Valid Choice.");
                    break;
            }
        }
    }
}