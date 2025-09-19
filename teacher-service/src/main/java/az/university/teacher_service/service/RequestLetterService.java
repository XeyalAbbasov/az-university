package az.university.teacher_service.service;

import az.university.teacher_service.client.RequestLetterClient;
import az.university.teacher_service.client.RequestLetterClientService;
import az.university.teacher_service.request.UpdateRequestLetterStatus;
import az.university.teacher_service.util.RequestStatus;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class RequestLetterService {

    private ModelMapper modelMapper;
    private RequestLetterClientService requestLetterClientService;
    private RequestLetterClient requestLetterClient;

    public RequestLetterService(ModelMapper modelMapper, RequestLetterClientService requestLetterClientService,
                                RequestLetterClient requestLetterClient) {
        this.modelMapper = modelMapper;
        this.requestLetterClientService = requestLetterClientService;
        this.requestLetterClient = requestLetterClient;
    }

    public void updateStatusApproved(Long letterId, UpdateRequestLetterStatus request) {

        request.setStatus(RequestStatus.APPROVED);
        requestLetterClientService.sendUpdateLetterStatusApproved(letterId, request);
//        requestLetterClient.approve(letterId, request);

    }

    public void updateStatusRejected(Long letterId, UpdateRequestLetterStatus request) {

        request.setStatus(RequestStatus.REJECTED);
        requestLetterClientService.sendUpdateLetterStatusRejected(letterId, request);
    }
}
