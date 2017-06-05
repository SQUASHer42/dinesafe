package com.example.joshhan.dinesafe;

import java.io.Serializable;

/**
 * Created by panda on 2017-06-04.
 */

public class UsefulReview implements Serializable{
    private int inspectionID;
    private String infractionDetails;
    private String inspectionDate;
    private String severity;
    private String action;
    private String courtOutcome;
    private double amountFined;

    public UsefulReview(int inspectionID, String infractionDetails, String inspectionDate, String severity,
                        String action, String courtOutcome, double amountFined) {
        this.inspectionID = inspectionID;
        this.infractionDetails = infractionDetails;
        this.inspectionDate = inspectionDate;
        this.severity = severity;
        this.action = action;
        this.courtOutcome = courtOutcome;
        this.amountFined = amountFined;
    }

    public String getComments() {
        return inspectionDate + ": " +
                "\n Problem: " + infractionDetails + " " +
                "\n Action: " + action +
                "\n Fined: $" + String.valueOf(amountFined);
    }

    public String getSeverity() {
        return severity;
    }
}
