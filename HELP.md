# Getting Started

### Run APP
mvn clean compile spring-boot:run

### Stop APP
mvn spring-boot:stop

### Test Requests:
#### POST localhost:8080/postData
Body:
<?xml version = "1.0"?>
<dataset>
    <emails>
        <email>user1@comeon.com</email>
        <email>user1@comeon.com</email>
        <email>user2@comeon.com</email>
        <email>user3@not-so-cool.com</email>
    </emails>
    <resources>
        <url>http://test.com/new-xml</url>
        <url>http://test.com/duplicate-emails</url>
        <url>http://test.com/duplicate-emails</url>
        <url>https://lux-buy.com/lux-buy-test/test_comeon_remove_me.xml</url>
    </resources>
</dataset>

Headers, Content-Type: application/xml

#### GET localhost:8080/getAllEmailsData

#### GET localhost:8080/getEmailCount/user1@comeon.com

## Known issues
* Some data not processed in rare edge cases, row was added before startTime.
* Possible endless recursive call of method if data points to same data. Not fixed, processed as is by requirements. 
* Big data not tested, Super real data not tested.
* Test covered 76% classes, 48% lines. 

