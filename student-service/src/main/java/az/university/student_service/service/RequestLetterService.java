package az.university.student_service.service;

import az.university.student_service.exception.RequestLetterNotFoundException;
import az.university.student_service.model.RequestLetter;
import az.university.student_service.repository.RequestLetterRepository;
import az.university.student_service.request.CreateRequestLetter;
import az.university.student_service.request.RequestLetterStatusUpdateRequest;
import az.university.student_service.response.RequestLetterResponse;
import az.university.student_service.util.RequestStatus;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class RequestLetterService {

    private RequestLetterRepository requestLetterRepository;
    private ModelMapper modelMapper;

    public RequestLetterService (RequestLetterRepository requestLetterRepository, ModelMapper modelMapper) {
        this.requestLetterRepository = requestLetterRepository;
        this.modelMapper = modelMapper;
    }

    public RequestLetterResponse createRequest(CreateRequestLetter request) {

        RequestLetter requestLetter = new RequestLetter();
        requestLetter.setCreatedAt(LocalDateTime.now());
        requestLetter.setStatus(RequestStatus.PENDING);

        modelMapper.map(request, requestLetter);

        requestLetterRepository.save(requestLetter);

        RequestLetterResponse response =new  RequestLetterResponse();
        response.setRequestId(requestLetter.getId());
        return  response;
    }

    public RequestLetterResponse updateStatusApproved(Long requestId, RequestLetterStatusUpdateRequest request) {

        RequestLetter letter=findLetterById(requestId);
        letter.setStatus(request.getStatus());
        letter.setAnsweredAt(LocalDateTime.now());
        requestLetterRepository.save(letter);

        RequestLetterResponse response =new  RequestLetterResponse();
        response.setRequestId(requestId);

        return response;
    }
    public RequestLetterResponse updateStatusRejected(Long id, RequestLetterStatusUpdateRequest request) {

        RequestLetter letter=findLetterById(id);
        letter.setStatus(request.getStatus());
        requestLetterRepository.save(letter);

        RequestLetterResponse response =new  RequestLetterResponse();
        response.setRequestId(id);

        return response;
    }



    protected RequestLetter findLetterById(Long requestId) {
        return requestLetterRepository.findById(requestId).orElseThrow(()-> new RequestLetterNotFoundException("Letter could not be found by following ! "));
    }


}
