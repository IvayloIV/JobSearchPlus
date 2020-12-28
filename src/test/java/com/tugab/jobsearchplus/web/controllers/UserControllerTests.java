package com.tugab.jobsearchplus.web.controllers;

import com.tugab.jobsearchplus.domain.entities.JobStatus;
import com.tugab.jobsearchplus.domain.entities.Specialty;
import com.tugab.jobsearchplus.domain.enums.JobPosition;
import com.tugab.jobsearchplus.repository.JobStatusRepository;
import com.tugab.jobsearchplus.repository.SpecialtyRepository;
import com.tugab.jobsearchplus.repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
public class UserControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private SpecialtyRepository specialtyRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JobStatusRepository jobStatusRepository;

    @BeforeEach
    public void init() {
        this.jobStatusRepository.deleteAll();
        this.specialtyRepository.deleteAll();
        this.userRepository.deleteAll();
    }

    @Test
    @WithMockUser("spring")
    public void registerUser_withValidArgs_expectToSaveUser() throws Exception {
        JobStatus jobStatus = new JobStatus();
        jobStatus.setId(1L);
        jobStatus.setName(JobPosition.Unemployed);
        this.jobStatusRepository.save(jobStatus);

        Specialty specialty = new Specialty();
        specialty.setId(1L);
        specialty.setName("KST");
        this.specialtyRepository.saveAndFlush(specialty);

        this.mockMvc
            .perform(
                MockMvcRequestBuilders.post("/user/register")
                    .param("facultyNumber", "29304530")
                    .param("password", "1234")
                    .param("name", "Ivana")
                    .param("surname", "Ivanova")
                    .param("egn", "9234763440")
                    .param("phone", "0884335454")
                    .param("email", "ivana@abv.bg")
                    .param("specialtyId", specialty.getId().toString())
                    .param("studyType", "REGULAR")
                    .param("grade", "5.4")
            );

        Long expectedUserCount = 1L;
        Long actualUserCount = this.userRepository.count();
        Assertions.assertEquals(expectedUserCount, actualUserCount);
    }
}
