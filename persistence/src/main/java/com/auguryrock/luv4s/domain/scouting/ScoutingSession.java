package com.auguryrock.luv4s.domain.scouting;

import com.auguryrock.luv4s.domain.Objective;
import com.auguryrock.luv4s.domain.scouting.User;

import java.util.Date;

public class ScoutingSession {
    protected Date start;
    protected Date end;
    protected String description;
    protected User user;
    protected Objective objective;
}
