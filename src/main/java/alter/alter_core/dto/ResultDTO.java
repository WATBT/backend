package alter.alter_core.dto;


import org.springframework.http.HttpStatus;


public class ResultDTO<T> {
    private int statusCode;  // HTTP 상태 코드
    private String message;  // 결과 메시지
    private T data; // 결과 데이터

    public static <T> ResultDTO<T> success(int statusCode, String message, T data) {
        return new ResultDTO(statusCode, "Request successful: " + message, data);
    }

    public ResultDTO(int statusCode, String message, T data) {
        this.statusCode = statusCode;
        this.message = message;
        this.data = data;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
