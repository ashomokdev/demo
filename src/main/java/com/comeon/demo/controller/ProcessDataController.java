package com.comeon.demo.controller;

import com.comeon.demo.dto.DatasetDTO;
import com.comeon.demo.model.Email;
import com.comeon.demo.service.DataFeederService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProcessDataController {

    @Autowired
    private DataFeederService dataFeederService;

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
            List<Email> data = dataFeederService.getAll();
            return new ResponseEntity(data, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("You failed to post data. " + e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "/getEmailCount", method = RequestMethod.GET)
    public
    @ResponseBody
    ResponseEntity getEmailCount() {
        String[] jsonResult = new String[]{"hhh", "hhh7"};
        return new ResponseEntity(jsonResult, HttpStatus.ACCEPTED);
    }




//    @RequestMapping(value = "/getFullEmailsInfo", method = RequestMethod.GET)
//    @ResponseStatus(HttpStatus.OK)
//    public String pdfFileForm(Model model) {
//        PdfFileData pdfFile = generator.getPdfFileData();
//        model.addAttribute("pdfFile", pdfFile);
//        return "pdfFileInputForm";
//    }
//
//    @RequestMapping(value = "/pdfFileInputForm", method = RequestMethod.POST, produces = {"application/pdf"})
//    @ResponseBody
//    public FileSystemResource getFile(@ModelAttribute PdfFileData pdfFile) {
//        File file = generator.generatePdf(pdfFile, fontFile);
//        generator.updateLastId(pdfFile);
//        return new FileSystemResource(file);
//    }


}
