package az.university.teacher_service.service;

import az.university.teacher_service.client.RequestLetterClientService;

import org.springframework.stereotype.Service;

@Service
public class RequestLetterService {

    private RequestLetterClientService requestLetterClientService;

    public RequestLetterService(RequestLetterClientService requestLetterClientService) {
        this.requestLetterClientService = requestLetterClientService;
    }

    public void updateStatusApproved(Long letterId) {

        requestLetterClientService.sendUpdateLetterStatusApproved(letterId);

    }

    public void updateStatusRejected(Long letterId) {

        requestLetterClientService.sendUpdateLetterStatusRejected(letterId);
    }
}
