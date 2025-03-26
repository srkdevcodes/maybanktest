package com.example.maybank.controller;

import com.example.maybank.dto.CustomerRequest;
import com.example.maybank.entity.Customer;
import com.example.maybank.service.CustomerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.*;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/customer")
public class CustomerJobController {

  private final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private JobLauncher jobLauncher;

    @Autowired
    private Job job;

    @Autowired
    private CustomerService customerService;



    @PostMapping("/importData")
    public String jobLauncher()  {
        final JobParameters jobParameters = new JobParametersBuilder()
                .addLong("startAt", System.currentTimeMillis()).toJobParameters();

        try{
            final JobExecution jobExecution = jobLauncher.run(job, jobParameters);

            return jobExecution.getStatus().toString();
        }catch (JobExecutionAlreadyRunningException | JobRestartException | JobInstanceAlreadyCompleteException
                | JobParametersInvalidException e) {
            logger.error(e.getMessage());
            return "Job failed with exception: " + e.getMessage();
        }
    }


    @GetMapping("/all")
    public Page<Customer> getCustomers(@RequestParam(value = "offset", required = false) Integer offset,
                                   @RequestParam(value = "pageSize", required = false) Integer pageSize,
                                   @RequestParam(value = "sortBy", required = false) String sortBy) {
        if(null == offset) offset = 0;
        if(null == pageSize) pageSize = 10;
        if(StringUtils.isEmpty(sortBy)) sortBy ="id";
        return (customerService.getCustomerList(PageRequest.of(offset, pageSize, Sort.by(sortBy))));
    }

    @GetMapping("/search")
    public Page<Customer> findCustomers(@RequestParam(value = "customerId", required = false) String customerId,
                                       @RequestParam(value = "customerAccount", required = false) Long customerAccount,
                                       @RequestParam(value = "description", required = false) String description,
                                        @RequestParam(value = "offset", required = false) Integer offset,
                                        @RequestParam(value = "pageSize", required = false) Integer pageSize,
                                        @RequestParam(value = "sortBy", required = false) String sortBy) {
        if(null == offset) offset = 0;
        if(null == pageSize) pageSize = 10;
        if(StringUtils.isEmpty(sortBy)) sortBy ="id";
        return (customerService.findCustomer(customerId, customerAccount, description,PageRequest.of(offset, pageSize, Sort.by(sortBy))));
    }

    @PutMapping("/update")
    public Customer updateCustomer(@RequestBody CustomerRequest customerRequest){
       return customerService.updateCustomer(customerRequest);
    }
}
