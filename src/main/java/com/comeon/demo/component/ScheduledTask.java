package com.comeon.demo.component;

import com.comeon.demo.service.DataProcessorService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;

@Slf4j
@Component
public class ScheduledTask {
    //    public static final long interval = 300000; //5 mins
    public static final long interval = 5000;
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
    @Autowired
    private DataProcessorService dataProcessorService;

    /**
     * process batch on collected date, marked with timestamp in last 5 mins interval
     */
    @Scheduled(fixedRate = interval)
    public void processBatch() {
        long currentTimeMillis = System.currentTimeMillis();
        Timestamp batchEnd = new Timestamp(currentTimeMillis);
        Timestamp batchStart = new Timestamp(currentTimeMillis - interval);
        dataProcessorService.processData(batchStart, batchEnd);
    }


}