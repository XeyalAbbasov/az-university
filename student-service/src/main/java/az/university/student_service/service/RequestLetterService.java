package az.university.student_service.service;

import az.university.student_service.client.AuthenticationClient;
import az.university.student_service.exception.RequestLetterNotFoundException;
import az.university.student_service.model.RequestLetter;
import az.university.student_service.repository.RequestLetterRepository;
import az.university.student_service.request.CreateRequestLetter;
import az.university.student_service.request.UpdateRequestLetterStatus;
import az.university.student_service.response.RequestLetterResponse;
import az.university.student_service.util.RequestStatus;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class RequestLetterService {

    private RequestLetterRepository requestLetterRepository;
    private ModelMapper modelMapper;
    private AuthenticationClient authenticationClient;

    public RequestLetterService(RequestLetterRepository requestLetterRepository, ModelMapper modelMapper, AuthenticationClient authenticationClient) {
        this.requestLetterRepository = requestLetterRepository;
        this.modelMapper = modelMapper;
        this.authenticationClient = authenticationClient;
    }


    @Value("${internal.api.key}")
    private String internalApiKey;


    public RequestLetterResponse createRequest(String username,CreateRequestLetter request) {

        Long studentId=authenticationClient.getUserIdByUsername(username,internalApiKey);

        RequestLetter requestLetter = new RequestLetter();
        requestLetter.setCreatedAt(LocalDateTime.now());
        requestLetter.setStatus(RequestStatus.PENDING);
        requestLetter.setStudentId(studentId);
        requestLetter.setTitle(request.getTitle());
        requestLetter.setContent(request.getContent());

        requestLetterRepository.save(requestLetter);

        RequestLetterResponse response = new RequestLetterResponse();
        response.setRequestId(requestLetter.getId());
        return response;
    }

    public void updateStatusApproved(Long requestId) {

        RequestLetter letter = findLetterById(requestId);
        letter.setStatus(RequestStatus.APPROVED);
        letter.setAnsweredAt(LocalDateTime.now());
        requestLetterRepository.save(letter);

    }

    public void updateStatusRejected(Long id) {

        RequestLetter letter = findLetterById(id);
        letter.setStatus(RequestStatus.REJECTED);
        letter.setAnsweredAt(LocalDateTime.now());
        requestLetterRepository.save(letter);

    }


    protected RequestLetter findLetterById(Long requestId) {
        return requestLetterRepository.findById(requestId).orElseThrow(() -> new RequestLetterNotFoundException("Letter could not be found by following ! "));
    }


}
