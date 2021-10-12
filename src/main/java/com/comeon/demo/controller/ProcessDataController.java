package com.comeon.demo.controller;

import com.comeon.demo.dto.DatasetDTO;
import com.comeon.demo.model.EmailResult;
import com.comeon.demo.service.DataFeederService;
import com.comeon.demo.service.DataProcessorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProcessDataController {

    @Autowired
    private DataFeederService dataFeederService;

    @Autowired
    private DataProcessorService dataProcessorService;

    @RequestMapping(value = "/postData", method = RequestMethod.POST)
    public
    @ResponseBody
    ResponseEntity postData(@RequestBody DatasetDTO datasetDTO) {
        if (datasetDTO != null) {
            try {
                dataFeederService.post(datasetDTO);
                return new ResponseEntity("Your data posted and will be processed in schedule. Call GET for getting the result", HttpStatus.ACCEPTED);
            } catch (Exception e) {
                return new ResponseEntity("You failed to post data. " + e.getMessage(), HttpStatus.BAD_REQUEST);
            }
        } else {
            return new ResponseEntity("You failed to post data because the input was empty. ", HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "/getAllEmailsData", method = RequestMethod.GET)
    public
    @ResponseBody
    ResponseEntity getAllEmailsCount() {
        try {
            List<EmailResult> data = dataProcessorService.getAllCounted();
            return new ResponseEntity(data, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Unknown error. " + e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }


    @GetMapping("/getEmailCount/{email}")
    @ResponseBody
    public ResponseEntity getEmailCount(@PathVariable String email) {
        try {
            long count = dataProcessorService.getCountByEmail(email);
            return new ResponseEntity(count, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Unknown error. " + e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

}
