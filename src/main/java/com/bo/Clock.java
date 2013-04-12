package com.bo;

import org.directwebremoting.Browser;
import org.directwebremoting.ScriptSessions;
import org.directwebremoting.impl.DaemonThreadFactory;

import java.util.Date;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Class Clock.
 *
 * @author Boris Pronin (<a href="mailto:bpronin@bttprime.com">bpronin@bttprime.com</a>)
 */
public class Clock implements Runnable {

    protected volatile boolean active = false;

    public Clock() {
    }

    public void init() {
        ScheduledThreadPoolExecutor executor = new ScheduledThreadPoolExecutor(1, new DaemonThreadFactory());
        executor.scheduleAtFixedRate(this, 1, 1, TimeUnit.SECONDS);
    }

    public void run() {
        if (active) {
            updateClockDisplay();
        }
    }

    public void updateClockDisplay() {
        Browser.withAllSessions(new Runnable() {
            public void run() {
                try {
                    ScriptSessions.addFunctionCall("updateClockDisplay", new Date().toString());
                } catch (Exception x) {
                    x.printStackTrace();
                }
            }
        });
    }

    public synchronized void toggle() {
        active = !active;
    }
}
