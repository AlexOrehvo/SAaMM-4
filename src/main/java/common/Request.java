package common;

import java.sql.Time;
import java.util.Date;

public class Request {

    private boolean wasInLine = false;

    private Date bornTime;

    private Date deadTime;

    private Date startOfQueue;

    private Date endOfQueue;

    public Request() {
        bornTime = new Date();
    }

    public boolean isWasInLine() {
        return wasInLine;
    }

    public void setWasInLine(boolean wasInLine) {
        this.wasInLine = wasInLine;
    }

    public Date getBornTime() {
        return bornTime;
    }

    public void setBornTime(Date bornTime) {
        this.bornTime = bornTime;
    }

    public Date getDeadTime() {
        return deadTime;
    }

    public void setDeadTime(Date deadTime) {
        this.deadTime = deadTime;
    }

    public Date getStartOfQueue() {
        return startOfQueue;
    }

    public void setStartOfQueue(Date startOfQueue) {
        this.startOfQueue = startOfQueue;
    }

    public Date getEndOfQueue() {
        return endOfQueue;
    }

    public void setEndOfQueue(Date endOfQueue) {
        this.endOfQueue = endOfQueue;
    }
}
