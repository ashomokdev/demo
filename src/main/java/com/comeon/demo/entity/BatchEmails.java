package com.comeon.demo.entity;

import lombok.Builder;

import java.sql.Timestamp;

//todo implement https://www.infoworld.com/article/3373652/java-persistence-with-jpa-and-hibernate-part-1-entities-and-relationships.html
//https://betacode.net/11893/integrating-spring-boot-jpa-and-h2-database

public class BatchEmails {
    @Builder.Default
    private Timestamp butchCreatedStartTime; //todo compare timestamp https://stackoverflow.com/questions/7913264/compare-two-timestamp-in-java

    @Builder.Default
    private Long butchId;
}
