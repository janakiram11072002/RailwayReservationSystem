public class Passanger {
    int PassangerId;
    String Name;
    int Age;
    long MobileNo;
    char Gender;
    char Coach; //A - AC, N - nonAc, S - Sitter
    int SeatNo;
    int TicketNo;
    boolean Status;

    public Passanger(int PassangerId, String name, int age,char gender,char coach,int seatNo,int ticketNo, long mobileNo, boolean status)
    {
        this.PassangerId = PassangerId;
        this.Name = name;
        this.Age = age;
        this.Gender = gender;
        this.Coach = coach;
        this.SeatNo = seatNo;
        this.MobileNo = mobileNo;
        this.TicketNo = ticketNo;
        this.Status = status;

    }
    public void printDetails()
    {
        System.out.println("PassangerId : " + this.PassangerId);
        System.out.println("Name : " + this.Name);
        System.out.println("Age : " + this.Age);
        System.out.println("Gender : " + this.Gender);
        System.out.println("Mobile number : " + this.MobileNo);
        System.out.println("Coach : " + this.Coach);
        if(Status)
        {
            System.out.println("Seat Number : " + this.SeatNo);
            System.out.println("Ticket number : " + this.TicketNo);
            System.out.println("Status : Ticket Conformed");
        }
        else
        {
            System.out.println("Status : Waiting List");
        }
        
    }
}
