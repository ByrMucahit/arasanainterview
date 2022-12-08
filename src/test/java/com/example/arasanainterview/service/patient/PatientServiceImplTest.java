package com.example.arasanainterview.service.patient;

import com.example.arasanainterview.api.request.PatientRequest.SavePatientRequest;
import com.example.arasanainterview.api.request.PatientRequest.UpdatePatientRequest;
import com.example.arasanainterview.domain.PatientUser;
import com.example.arasanainterview.repository.PatientRepository;
import com.example.arasanainterview.service.patient.impl.PatientServiceImpl;
import org.junit.Rule;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.rules.ExpectedException;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.BDDAssertions.then;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;
import static org.junit.Assert.assertThrows;


@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = {PatientService.class})
public class PatientServiceImplTest {

    @InjectMocks
    private PatientServiceImpl patientService;

    @Mock
    private PatientRepository patientRepository;

    @Rule
    ExpectedException thrown = ExpectedException.none();

    @Test
    public void givenParam_whenFindOnePatient_thenReturnList() {
        // given
        PatientUser expected = preparePatientUser("mucahit", "bayar");
        // when
        when(patientRepository.findById(1)).thenReturn(Optional.of(expected));

        PatientUser response = patientService.findOnePatient(1);

        // assert
        assertThat(response.getName()).isEqualTo("mucahit");
        assertThat(response.getSurname()).isEqualTo("bayar");
    }

    @Test
    public void givenRequestParam_WhenUpdatePatient_thenShouldOk() {
        //given
        UpdatePatientRequest request = UpdatePatientRequest.builder().name("mucahit").surname("bayar").build();

        // when
        patientService.updatePatient(request);
    }

    @Test
    public void it_whenCreatePatient_thenShouldOkey() {
        // given

        // when
        patientService.createPatient(SavePatientRequest.builder().name("mucahit").surname("bayar").build());

    }

    @Test
    public void it_whenFindAllPatientUser_thenReturnList() {
        // given

        // when
        when(patientRepository.findAll()).thenReturn(List.of(preparePatientUser("mucahit", "bayar")));

        List<PatientUser> response = patientService.getAllPatientUsers();

        // then
        then(response).isNotNull();
        then(response.size()).isEqualTo(1);
        PatientUser user = response.get(0);
        then(user.getName()).isEqualTo("mucahit");
        then(user.getSurname()).isEqualTo("bayar");
    }

    private PatientUser preparePatientUser(String name, String surname) {
        PatientUser patientUser = new PatientUser();
        patientUser.setName(name);
        patientUser.setSurname(surname);

        return patientUser;
    }
}
