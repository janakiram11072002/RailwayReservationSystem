public interface ReservationSystem
{
    public Boolean Bookticket(String Name, int Age, char Gender, char Coach, long MobileNo);
    public Boolean TicketCancellation(int TicketId);
    public void TicketAvailability();
    public void prepareChart();
    public void TicketDbUpdate();
}