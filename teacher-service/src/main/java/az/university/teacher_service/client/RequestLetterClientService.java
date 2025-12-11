package az.university.teacher_service.client;


import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class RequestLetterClientService {

    private RestTemplate restTemplate;

    public RequestLetterClientService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public void sendUpdateLetterStatusApproved(Long id) {

        HttpHeaders headers = new HttpHeaders();
        headers.set("X-USER-ROLES", "ROLE_CONTROL_REQUEST"); // yalnız bu hüququ ötürürük

        HttpEntity<Void> entity = new HttpEntity<>(headers);

        restTemplate.exchange(
                "http://localhost:8081/requests/" + id + "/approved",
                HttpMethod.PUT,
                entity,
                Void.class
        );

    }

    public void sendUpdateLetterStatusRejected(Long id) {

        HttpHeaders headers = new HttpHeaders();
        headers.set("X-USER-ROLES", "ROLE_CONTROL_REQUEST"); // yalnız bu hüququ ötürürük

        HttpEntity<Void> entity = new HttpEntity<>(headers);
        restTemplate.exchange(
                "http://localhost:8081/requests/" + id + "/rejected",
                HttpMethod.PUT,
                entity,
                Void.class
        );
    }

}
