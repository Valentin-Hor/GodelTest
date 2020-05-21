package by.horunzhyn.godel.Exceptions;

import org.springframework.http.HttpStatus;

public class ApiError {

    private int errorType;
    private HttpStatus status;
    private String message;

    ApiError(){};

    public ApiError(HttpStatus status) {
        this();
        this.status = status;
    }


    public ApiError (HttpStatus status, Throwable ex){
        this();
        this.status= status;
        this.message = ex.getLocalizedMessage();
    }

     public ApiError (HttpStatus status, String message, Throwable ex){
        this();
        this.status= status;
        this.message = message;
    }

    public int getErrorType() {
        return errorType;
    }

    public void setErrorType(int errorType) {
        this.errorType = errorType;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public void setStatus(HttpStatus status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
