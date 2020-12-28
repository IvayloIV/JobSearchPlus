package com.tugab.jobsearchplus.service;

import com.tugab.jobsearchplus.domain.entities.JobHistory;
import com.tugab.jobsearchplus.domain.entities.JobStatus;
import com.tugab.jobsearchplus.domain.entities.User;
import com.tugab.jobsearchplus.domain.enums.JobPosition;
import com.tugab.jobsearchplus.domain.models.services.UserServiceModel;
import com.tugab.jobsearchplus.repository.JobHistoryRepository;
import com.tugab.jobsearchplus.repository.JobRepository;
import com.tugab.jobsearchplus.repository.JobStatusRepository;
import com.tugab.jobsearchplus.repository.UserRepository;
import com.tugab.jobsearchplus.service.impl.JobServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
public class JobServiceTests {

    @Autowired
    private JobRepository jobRepository;

    @Mock
    private JobHistoryRepository jobHistoryRepository;

    @Autowired
    private JobStatusRepository jobStatusRepository;

    @Autowired
    private UserRepository userRepository;

    private JobService jobService;
    private ModelMapper modelMapper;

    @BeforeEach
    public void init() {
        this.modelMapper = new ModelMapper();
        this.jobService = new JobServiceImpl(this.jobRepository, this.jobStatusRepository,
                this.userRepository, this.jobHistoryRepository, this.modelMapper, null);
    }

    @Test
    public void getJobsHistoryByUser_withValidJobStatus_expectToFindJobsHistory() {
        User user = new User();
        user.setFacultyNumber("21607543");
        user.setName("Ivan");

        JobStatus jobStatus1 = new JobStatus();
        jobStatus1.setName(JobPosition.Applied);
        JobHistory jobHistory1 = new JobHistory();
        jobHistory1.setNewStatus(jobStatus1);

        JobStatus jobStatus2 = new JobStatus();
        jobStatus2.setName(JobPosition.Left);
        JobHistory jobHistory2 = new JobHistory();
        jobHistory2.setNewStatus(jobStatus2);

        List<JobHistory> expectedJobHistoryList = Arrays.asList(jobHistory1, jobHistory2);
        Mockito.when(this.jobHistoryRepository.findAllByUserOrderByCreatedDateDesc(Mockito.any()))
                .thenReturn(expectedJobHistoryList);

        UserServiceModel userServiceModel = this.modelMapper.map(user, UserServiceModel.class);
        List<JobHistory> actualJobHistoryList = this.jobService
                .getJobsHistoryByUser(userServiceModel)
                .stream()
                .map(j -> this.modelMapper.map(j, JobHistory.class))
                .collect(Collectors.toList());

        JobPosition jobPosition1 = actualJobHistoryList.get(0).getNewStatus().getName();
        JobPosition jobPosition2 = actualJobHistoryList.get(1).getNewStatus().getName();

        Assertions.assertEquals(expectedJobHistoryList.size(), actualJobHistoryList.size());
        Assertions.assertEquals(jobStatus1.getName(), jobPosition1);
        Assertions.assertEquals(jobStatus2.getName(), jobPosition2);
    }
}
