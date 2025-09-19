package az.university.teacher_service.client;

import az.university.teacher_service.request.UpdateRequestLetterStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class RequestLetterClientService {

    private RestTemplate restTemplate;

    public RequestLetterClientService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public void sendUpdateLetterStatusApproved (Long letterId, UpdateRequestLetterStatus request) {

        restTemplate.put("http://localhost:8081/requests/"
                +letterId+"/approved", request,Void.class);

    }

    public void sendUpdateLetterStatusRejected (Long letterId, UpdateRequestLetterStatus request) {

        restTemplate.put("http://localhost:8081/requests/"
                +letterId+"/rejected", request,Void.class);

    }

}
