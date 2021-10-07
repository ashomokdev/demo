package com.comeon.demo.controller;

import com.comeon.demo.dto.DatasetDTO;
import com.comeon.demo.service.DataProcessorServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class ProcessDataController {

    @Autowired
    private DataProcessorServiceImpl dataProcessorImpl;

    @RequestMapping(value = "/getAllEmailsCount", method = RequestMethod.GET)
    public
    @ResponseBody
    ResponseEntity getAllEmailsCount() {
        String[] jsonResult = new String[]{"hhh", "hhh7"};
        return new ResponseEntity(jsonResult, HttpStatus.ACCEPTED);
    }

    @RequestMapping(value = "/getEmailCount", method = RequestMethod.GET)
    public
    @ResponseBody
    ResponseEntity getEmailCount() {
        String[] jsonResult = new String[]{"hhh", "hhh7"};
        return new ResponseEntity(jsonResult, HttpStatus.ACCEPTED);
    }

    @RequestMapping(value = "/postData", method = RequestMethod.POST,
            consumes = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
    public
    @ResponseBody
    ResponseEntity postData(@RequestBody DatasetDTO datasetDTO) {
        if (datasetDTO != null) {
            try {
                dataProcessorImpl.post(datasetDTO);
                return new ResponseEntity("Your data posted and will be processed in schedule. Call GET for getting the result", HttpStatus.ACCEPTED);
            } catch (Exception e) {
                return new ResponseEntity("You failed to post data. " + e.getMessage(), HttpStatus.BAD_REQUEST);
            }
        } else {
            return new ResponseEntity("You failed to post data because the input was empty. ", HttpStatus.BAD_REQUEST);
        }
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
