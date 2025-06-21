package com.poly.services;

import java.util.UUID;

import com.poly.dto.response.PageResponse;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.poly.dto.request.FeedbackRequest;
import com.poly.dto.response.product.feedback.FeedbackResponse;
import com.poly.entity.Product;
import com.poly.entity.Feedback;
import com.poly.entity.User;
import com.poly.exception.AppException;
import com.poly.exception.ErrorCode;
import com.poly.mapper.FeedbackMapper;
import com.poly.repositories.ProductRepository;
import com.poly.repositories.FeedbackRepository;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class FeedbackService {
    UserService userService;

    FeedbackRepository feedbackRepository;
    ProductRepository productRepository;

    FeedbackMapper feedbackMapper;

    public FeedbackResponse create(FeedbackRequest request) {
        Product product = productRepository.findById(request.getProductId())
                .orElseThrow(() -> new AppException(ErrorCode.PRODUCT_NOT_FOUND));

        User user = userService.getCurrentUser();

        Feedback feedback = feedbackMapper.toFeedback(request);
        feedback.setProduct(product);
        feedback.setUser(user);

        // Lưu feedback vào database
        feedbackRepository.save(feedback);

        return feedbackMapper.toFeedbackResponse(feedbackRepository.save(feedback));
    }

    public PageResponse<FeedbackResponse> getByProduct(UUID productId, int page, int size) {
        Sort sort = Sort.by(Sort.Direction.ASC, "createTime");
        Pageable pageable = PageRequest.of(page - 1, size, sort);

        var pageResult = feedbackRepository.findAllByProductId(productId, pageable);
        return feedbackMapper.toPageResponse(pageResult);
    }

    public void delete(UUID id) {
        try {
            feedbackRepository.deleteById(id);
        } catch (EmptyResultDataAccessException ex) {
            throw new AppException(ErrorCode.FEEDBACK_NOT_FOUND);
        }
    }


}
