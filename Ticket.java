public class Ticket {
    private String ticketNumber;
    private String offenderName;
    private String offenderDOB;
    private String offenderDLN;
    private String vehiclePlate;
    private String dateOfOffence;
    private String paymentDueDate;
    private String courtDate;
    private String offenceLocation;
    private String offenceDescription;
    private double totalDue;
    private String officerName;
    private String officerBadge;
    private String officerPrecinct;
    private boolean paid;

    public Ticket(String ticketNumber, String offenderName, String offenderDOB, String offenderDLN, String vehiclePlate,
                  String dateOfOffence, String paymentDueDate, String courtDate, String offenceLocation,
                  String offenceDescription, double totalDue, String officerName, String officerBadge,
                  String officerPrecinct, boolean paid) {
        this.ticketNumber = ticketNumber;
        this.offenderName = offenderName;
        this.offenderDOB = offenderDOB;
        this.offenderDLN = offenderDLN;
        this.vehiclePlate = vehiclePlate;
        this.dateOfOffence = dateOfOffence;
        this.paymentDueDate = paymentDueDate;
        this.courtDate = courtDate;
        this.offenceLocation = offenceLocation;
        this.offenceDescription = offenceDescription;
        this.totalDue = totalDue;
        this.officerName = officerName;
        this.officerBadge = officerBadge;
        this.officerPrecinct = officerPrecinct;
        this.paid = paid;
    }

    public String getTicketNumber() {
        return ticketNumber;
    }

    public String getOffenderName() {
        return offenderName;
    }

    public String getOffenderDOB() {
        return offenderDOB;
    }

    public String getOffenderDLN() {
        return offenderDLN;
    }

    public String getVehiclePlate() {
        return vehiclePlate;
    }

    public String getDateOfOffence() {
        return dateOfOffence;
    }

    public String getPaymentDueDate() {
        return paymentDueDate;
    }

    public String getCourtDate() {
        return courtDate;
    }

    public String getOffenceLocation() {
        return offenceLocation;
    }

    public String getOffenceDescription() {
        return offenceDescription;
    }

    public double getTotalDue() {
        return totalDue;
    }

    public String getOfficerName() {
        return officerName;
    }

    public String getOfficerBadge() {
        return officerBadge;
    }

    public String getOfficerPrecinct() {
        return officerPrecinct;
    }

    public boolean isPaid() {
        return paid;
    }

    public void setPaid(boolean paid) {
        this.paid = paid;
    }
}

