package alter.alter_core.dto;


import org.springframework.http.HttpStatus;


public class ResultDTO<T> {
    private int statusCode;  // HTTP 상태 코드
    private String message;  // 결과 메시지
    private T data;

    public ResultDTO(int statusCode, String message, T data) {
        this.statusCode = statusCode;
        this.message = message;
        this.data = data;
    }

    // Static Factory Method for Success Response
    public static <T> ResultDTO<T> success(T data) {
        return new ResultDTO<>(HttpStatus.OK.value(), "Success", data);
    }

    // Static Factory Method for Error Response
    public static <T> ResultDTO<T> error(HttpStatus status, String message) {
        return new ResultDTO<>(status.value(), message, null);
    }


}
