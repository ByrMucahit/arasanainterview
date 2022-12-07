package com.example.arasanainterview.api.controller;

import ch.qos.logback.core.net.ObjectWriter;
import com.example.arasanainterview.api.request.SavePatientRequest;
import com.example.arasanainterview.service.patient.PatientService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.junit.jupiter.api.Test;
import static org.mockito.BDDMockito.given;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.nio.charset.Charset;

import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = PatientContoller.class)
@AutoConfigureMockMvc(addFilters = false)
public class PatientControllerTest {
    public static final MediaType APPLICATION_JSON_UTF8 = new MediaType(MediaType.APPLICATION_JSON.getType(), MediaType.APPLICATION_JSON.getSubtype(), Charset.forName("utf8"));

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PatientService patientService;

    @Test
    public void givenValidRequest_whenCreatePatient_thenShouldOk() throws Exception {
        SavePatientRequest request = SavePatientRequest.builder().name("mucahit").surname("bayar").build();

        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);

        ObjectWriter ow = (ObjectWriter) mapper.writer().withDefaultPrettyPrinter();
        String requestJson=ow.writeValueAsString(anObject );


        this.mockMvc.perform(post("/create").contentType(APPLICATION_JSON_UTF8).content(request)).andDo(print()).andExpect(status().isOk());
    }

}
