import java.io.Console;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

public class RailwayReservationBooking implements ReservationSystem
{
    private HashMap<Integer,Passanger> passangerList;
    private ArrayList<Integer> AcCoach;
    private ArrayList<Integer> AcCoachAvailability;
    private ArrayList<Integer> NonAcCoach;
    private ArrayList<Integer> NonAcCoachAvailability;
    private ArrayList<Integer> SittingCoach;
    private ArrayList<Integer> SittingCoachAvailability;
    private Queue<Integer> WaitingList;
    final static int CoachLength = 60;
    final static int waitingListSize = 10;
    int PassangerId = 1;
    int TicketNo = 12345;

    public RailwayReservationBooking()
    {
        this.passangerList = new HashMap<>();
        this.AcCoach = new ArrayList<>(CoachLength);
        this.NonAcCoach = new ArrayList<>(CoachLength);
        this.SittingCoach = new ArrayList<>(CoachLength);

        // For Available seat num after cancelling tickets
        this.AcCoachAvailability = new ArrayList<>();
        this.NonAcCoachAvailability = new ArrayList<>();
        this.SittingCoachAvailability = new ArrayList<>();

        //Waiting List
        this.WaitingList = new LinkedList<>();

    }
    @Override
    public Boolean Bookticket(String Name, int Age, char Gender, char Coach, long MobileNo) {
        
        if(Coach == 'A')
        {
            if(AcCoach.size()<CoachLength)
            {
                AcCoach.add(PassangerId);
                Passanger p = new Passanger(PassangerId,Name,Age,Gender,Coach,AcCoach.indexOf(PassangerId)+1,TicketNo,MobileNo,true);
                this.passangerList.put(PassangerId, p);
                TicketNo++;
                PassangerId++;
            }
            else if(WaitingList.size() < waitingListSize)
            {
                WaitingList.add(PassangerId);
                Passanger p = new Passanger(PassangerId,Name,Age,Gender,Coach,-1,-1,MobileNo,false);
                this.passangerList.put(PassangerId, p);
                PassangerId++;
            }
            else
            {
                System.out.println("Sorry, No Ticket Not Available in Ac Coach");
                return false;
            }
        }
        else if(Coach == 'N')
        {
            if(AcCoach.size()<CoachLength)
            {
                NonAcCoach.add(PassangerId);
                Passanger p = new Passanger(PassangerId,Name,Age,Gender,Coach,NonAcCoach.indexOf(PassangerId)+1,TicketNo,MobileNo,true);
                this.passangerList.put(PassangerId, p);
                TicketNo++;
                PassangerId++;
            }
            else if(WaitingList.size() < waitingListSize)
            {
                WaitingList.add(PassangerId);
                Passanger p = new Passanger(PassangerId,Name,Age,Gender,Coach,-1,-1,MobileNo,false);
                this.passangerList.put(PassangerId, p);
                PassangerId++;
            }
            else
            {
                System.out.println("Sorry, No Ticket Not Available in Non-Ac Coach");
                return false;
            }
        }
        else if(Coach == 'S')
        {
            if(SittingCoach.size()<CoachLength)
            {
                AcCoach.add(PassangerId);
                Passanger p = new Passanger(PassangerId,Name,Age,Gender,Coach,SittingCoach.indexOf(PassangerId)+1,TicketNo,MobileNo,true);
                this.passangerList.put(PassangerId, p);
                TicketNo++;
                PassangerId++;
            }
            else if(WaitingList.size() < waitingListSize)
            {
                WaitingList.add(PassangerId);
                Passanger p = new Passanger(PassangerId,Name,Age,Gender,Coach,-1,-1,MobileNo,false);
                this.passangerList.put(PassangerId, p);
                PassangerId++;
            }
            else
            {
                System.out.println("Sorry, No Ticket Not Available in Sitter Coach");
                return false;
            }
        }
        
        return true;
    }

    @Override
    public void TicketAvailability() 
    {
        // TODO Auto-generated method stub
        System.out.println("Available Seats in Ac Coach : "+(CoachLength-AcCoach.size()));
        System.out.println("Available Seats in Non-Ac Coach : "+(CoachLength-NonAcCoach.size()));
        System.out.println("Available Seats in seatter Coach : "+(CoachLength-SittingCoach.size()));
        System.out.println("Available Seats in WaitingList Coach : "+(waitingListSize-WaitingList.size()));
        
    }

    @Override
    public Boolean TicketCancellation(int PassangerId) {
        // TODO Auto-generated method stub
        if(!passangerList.containsKey(PassangerId))
        {
            System.out.println("Invalid Passanger Id");
            return false;
        }
        Passanger p = passangerList.get(PassangerId);
        if(p.Status)
        {
            if(p.Coach == 'A')
            {
                AcCoachAvailability.add(p.SeatNo);
                AcCoach.remove(AcCoach.indexOf(PassangerId));
                passangerList.remove(PassangerId);
            }
            if(p.Coach == 'N')
            {
                NonAcCoachAvailability.add(p.SeatNo);
                NonAcCoach.remove(NonAcCoach.indexOf(PassangerId));
                passangerList.remove(PassangerId);
            }
            if(p.Coach == 'S')
            {
                SittingCoachAvailability.add(p.SeatNo);
                SittingCoach.remove(SittingCoach.indexOf(PassangerId));
                passangerList.remove(PassangerId);
            }
        }
        else
        {
            WaitingList.remove(PassangerId);
            passangerList.remove(PassangerId);
        }
        TicketDbUpdate();
        return true;
    }

    
    @Override
    public void prepareChart() 
    {
        for(int i=0;i<AcCoach.size();i++)
        {
            passangerList.get(AcCoach.get(i)).printDetails();
            System.out.println("\n-------------------------------------------\n");
        }
        for(int i=0;i<NonAcCoach.size();i++)
        {
            passangerList.get(NonAcCoach.get(i)).printDetails();
            System.out.println("\n-------------------------------------------\n");
        }
        for(int i=0;i<SittingCoach.size();i++)
        {
            passangerList.get(SittingCoach.get(i)).printDetails();
            System.out.println("\n-------------------------------------------\n");
        }
        for(int i=0;i<WaitingList.size();i++)
        {
            passangerList.get(AcCoach.toArray()[i]).printDetails();
            System.out.println("\n-------------------------------------------\n");
        }
    }
    @Override
    public void TicketDbUpdate() 
    {
        if(AcCoachAvailability.size()>0 && WaitingList.size() > 0)
        {
            Passanger p = null;
            for(int i : WaitingList)
            {
                p = passangerList.get(i);
                if(p.Coach=='A')
                {
                    p.SeatNo = AcCoachAvailability.get(0);
                    p.TicketNo = TicketNo;
                    TicketNo++;
                    AcCoach.add(p.PassangerId);
                    WaitingList.remove(p.PassangerId);
                }
            }
        }
        else if(NonAcCoachAvailability.size()>0  && WaitingList.size() > 0)
        {
            Passanger p = null;
            for(int i : WaitingList)
            {
                p = passangerList.get(i);
                if(p.Coach=='N')
                {
                    p.SeatNo = NonAcCoachAvailability.get(0);
                    p.TicketNo = TicketNo;
                    TicketNo++;
                    NonAcCoach.add(p.PassangerId);
                    WaitingList.remove(p.PassangerId);
                }
            }
        }
        else if(SittingCoachAvailability.size()>0  && WaitingList.size() > 0)
        {
            Passanger p = null;
            for(int i : WaitingList)
            {
                p = passangerList.get(i);
                if(p.Coach=='A')
                {
                    p.SeatNo = SittingCoachAvailability.get(0);
                    p.TicketNo = TicketNo;
                    TicketNo++;
                    SittingCoach.add(p.PassangerId);
                    WaitingList.remove(p.PassangerId);
                }
            }
        }

        if((AcCoachAvailability.size()>0 || 
           NonAcCoachAvailability.size()>0 || 
           SittingCoachAvailability.size()>0) && 
           WaitingList.size()>0)
           {
            TicketDbUpdate();
           }
    }

    

}
