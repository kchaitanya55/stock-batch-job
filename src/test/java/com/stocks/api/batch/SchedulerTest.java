package com.stocks.api.batch;

import com.stocks.api.batch.scheduler.StockScheduler;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.batch.core.*;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class SchedulerTest {
    @InjectMocks
    private StockScheduler stockScheduler;

    @Mock
    private Job job;


    @Mock
    private JobLauncher jobLauncher;

    @Mock
    JobExecution jobExecution;


    @Test
    void SchedulerTest() throws JobInstanceAlreadyCompleteException, JobExecutionAlreadyRunningException, JobParametersInvalidException, JobRestartException {
        jobExecution=jobLauncher.run(job,new JobParametersBuilder().addString("JobID",String.valueOf(System.currentTimeMillis())).toJobParameters());
        Assertions.assertDoesNotThrow(()->stockScheduler.processStockData());

    }

}
