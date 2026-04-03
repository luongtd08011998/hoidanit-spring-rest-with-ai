package vn.hoidanit.springrestwithai.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ApiResponse<T> {

    private int status;
    private String message;
    private T data;
    private List<String> details;

    private ApiResponse(int status, String message, T data) {
        this.status = status;
        this.message = message;
        this.data = data;
    }

    private ApiResponse(int status, String message, T data, List<String> details) {
        this.status = status;
        this.message = message;
        this.data = data;
        this.details = details;
    }

    // SUCCESS methods

    public static <T> ApiResponse<T> success(T data) {
        return new ApiResponse<>(200, "Thành công", data);
    }

    public static <T> ApiResponse<T> success(String message, T data) {
        return new ApiResponse<>(200, message, data);
    }

    public static <T> ApiResponse<T> created(T data) {
        return new ApiResponse<>(201, "Tạo mới thành công", data);
    }

    public static <T> ApiResponse<T> created(String message, T data) {
        return new ApiResponse<>(201, message, data);
    }

    // ERROR methods

    public static <T> ApiResponse<T> notFound(String message) {
        return new ApiResponse<>(404, message, null);
    }

    public static <T> ApiResponse<T> badRequest(String message) {
        return new ApiResponse<>(400, message, null);
    }

    public static <T> ApiResponse<T> badRequest(String message, List<String> details) {
        return new ApiResponse<>(400, message, null, details);
    }

    public static <T> ApiResponse<T> conflict(String message) {
        return new ApiResponse<>(409, message, null);
    }

    public static <T> ApiResponse<T> unauthorized(String message) {
        return new ApiResponse<>(401, message, null);
    }

    public static <T> ApiResponse<T> forbidden(String message) {
        return new ApiResponse<>(403, message, null);
    }

    public static <T> ApiResponse<T> error(String message) {
        return new ApiResponse<>(500, message, null);
    }

    // Getters

    public int getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

    public T getData() {
        return data;
    }

    public List<String> getDetails() {
        return details;
    }
}
