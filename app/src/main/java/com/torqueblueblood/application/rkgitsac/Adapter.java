package com.torqueblueblood.application.rkgitsac;

public class Adapter {
    private String names;
    private String eventdetails;

    public Adapter(String names, String eventdetails) {
        this.names = names;
        this.eventdetails = eventdetails;
    }

    public String getNames() {
        return names;
    }

    public void setNames(String names) {
        this.names = names;
    }

    public String getEventdetails() {
        return eventdetails;
    }

    public void setEventdetails(String eventdetails) {
        this.eventdetails = eventdetails;
    }
}
