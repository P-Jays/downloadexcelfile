package pj.isthebest.downloadexcelfile.controllers;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.compress.utils.IOUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import pj.isthebest.downloadexcelfile.exporter.ExcelFIleExporter;
import pj.isthebest.downloadexcelfile.models.Customer;

@Controller
public class DownloadExcelController {
    
    @RequestMapping
    public String index(){
        return "index";
    }

    @GetMapping("/download/customers.xlsx")
    public void downloadexcelfile(HttpServletResponse response) throws IOException{
        
        response.setContentType("application/octet-stream");
        response.setHeader("Content-Disposition", "attachment; filename=customers.xlsx");

        ByteArrayInputStream inputStream = ExcelFIleExporter.exportCustomerListToExcelFile(createTestData());

        IOUtils.copy(inputStream, response.getOutputStream());

    }

    private List<Customer> createTestData(){
        // creating list customer for testing
        List<Customer> customers = new ArrayList<Customer>();
        customers.add(new Customer("Berti","O'Cornol", "8197271601", "boconnolly0@1-reg.co.uk" ));
        customers.add(new Customer("Berti2","O'Cornol2", "8197271602", "boconnolly0@2-reg.co.uk" ));
        customers.add(new Customer("Berti3","O'Cornol3", "8197271603", "boconnolly0@3-reg.co.uk" ));
        customers.add(new Customer("Berti4","O'Cornol4", "8197271604", "boconnolly0@4-reg.co.uk" ));
        return customers;
    }
}
