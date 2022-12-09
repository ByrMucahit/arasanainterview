package com.example.arasanainterview.api.controller;

import com.example.arasanainterview.api.request.patient.SavePatientRequest;
import com.example.arasanainterview.api.request.patient.UpdatePatientRequest;
import com.example.arasanainterview.domain.Examination;
import com.example.arasanainterview.domain.PatientUser;
import com.example.arasanainterview.domain.Visit;
import com.example.arasanainterview.service.patient.PatientService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = PatientContoller.class)
@AutoConfigureMockMvc(addFilters = false)
public class PatientControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PatientService patientService;

    @Test
    public void getGivenValidRequestParameters_WhenFindOnePatient_thenReturnResource() throws Exception {
        given(this.patientService.findOnePatient(1)).willReturn(preparePatientUser("mucahit", "bayar"));

        this.mockMvc.perform(MockMvcRequestBuilders.get("/api/patient/find-one/{id}", 1)
                        .accept(MediaType.APPLICATION_JSON)).andDo(print())
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("mucahit"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.surname").value("bayar"));
    }

    @Test
    public void givenValidRequestParameters_whenCreatePatient_thenShouldOk() throws Exception {
        SavePatientRequest request = SavePatientRequest.builder().name("mucahit").surname("bayar").build();

        this.mockMvc.perform(MockMvcRequestBuilders.post("/api/patient/create")
                        .content(new ObjectMapper().writeValueAsString(request)).contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void givenValidRequestParameters_whenListPatient_thenShouldOk() throws Exception {

        given(this.patientService
                .getAllPatientUsers())
                .willReturn(Collections
                        .singletonList(preparePatientUser("mucahit", "bayar")));

        this.mockMvc.perform(MockMvcRequestBuilders.get("/api/patient/list")
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].name").value("mucahit"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].surname").value("bayar"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].visiting[0].localDate").value("2022-12-09"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].visiting[0].patientUserName").value("mucahit"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].visiting[0].patientUserSurname").value("bayar"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].visiting[0].listExaminationResourceList[0].treatment").value("headache"));
    }

    @Test
    void givenValidRequestParameters_whenUpdatePatient_thenReturnResource() throws Exception {
        UpdatePatientRequest request = UpdatePatientRequest.builder().name("mucahit").surname("bayar").build();

        this.mockMvc.perform(MockMvcRequestBuilders.
                        put("/api/patient/update")
                        .content(new ObjectMapper().writeValueAsString(request))
                        .contentType(MediaType.APPLICATION_JSON)).andDo(print())
                .andExpect(status().isOk());
    }

    private List<Examination> prepareExaminations(Visit visit) {
        Examination examination = new Examination();
        examination.setVisit(visit);
        examination.setTreatment("headache");
        return Collections.singletonList(examination);
    }

    private PatientUser preparePatientUser(String name, String surname) {
        PatientUser user = new PatientUser();
        user.setName(name);
        user.setSurname(surname);
        user.setList(prepareVisitList(user));
        return user;
    }

    private List<Visit> prepareVisitList(PatientUser user) {
        Visit visit = new Visit();
        visit.setPatientUser(user);
        visit.setVisitingDate(LocalDate.of(2022, 12, 9));
        visit.setExaminations(prepareExaminations(visit));

        return Collections.singletonList(visit);
    }
}
