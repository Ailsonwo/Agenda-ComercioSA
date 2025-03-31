package br.com.comerciosa.agenda.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ApiResponseDTO<T> {
    private boolean success;
    private T data;
    private String message;
    private LocalDateTime timestamp;
    private Integer status;


    public static <T> ApiResponseDTO<T> success(T data) {
        ApiResponseDTO<T> response = new ApiResponseDTO<>();
        response.setSuccess(true);
        response.setData(data);
        response.setTimestamp(LocalDateTime.now());
        return response;
    }

    public static ApiResponseDTO<?> error(String message, HttpStatus status) {
        ApiResponseDTO<?> response = new ApiResponseDTO<>();
        response.setSuccess(false);
        response.setMessage(message);
        response.setStatus(status.value());
        response.setTimestamp(LocalDateTime.now());
        return response;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}