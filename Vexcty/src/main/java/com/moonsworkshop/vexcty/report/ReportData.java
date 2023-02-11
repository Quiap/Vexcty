package com.moonsworkshop.vexcty.report;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter @Setter
public class ReportData {

    private UUID reportID, reported, reporter;
    private ReportState reportState;
    private String reason;

    public ReportData(UUID reportID, UUID reported, UUID reporter, String reason) {
        this.reportID = reportID;
        this.reported = reported;
        this.reporter = reporter;
        this.reason = reason;
        this.reportState = ReportState.WAITING;
    }

}