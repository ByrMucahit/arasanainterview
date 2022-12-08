package com.example.arasanainterview.service.patient;

import com.example.arasanainterview.api.request.SavePatientRequest;
import com.example.arasanainterview.domain.PatientUser;
import com.example.arasanainterview.repository.PatientRepository;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import static org.assertj.core.api.BDDAssertions.then;
import java.util.List;

import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = {PatientService.class})
public class PatientServiceImplTest {

    @InjectMocks
    private PatientService patientService;

    @Mock
    private PatientRepository patientRepository;


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
        when(patientRepository.findAll()).thenReturn(List.of(preparePatientUser("mucahit","bayar")));

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
