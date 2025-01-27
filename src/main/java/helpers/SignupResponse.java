package helpers;

public class SignupResponse {
	private Boolean success;
	private String message;
	private String token;
	
	public SignupResponse() {
		this.message = "";
        this.token = "";
        this.success = false;
	}
	
	public SignupResponse(String message, String token) {
        this.message = message;
        this.token = token;
        this.success = false;
    }
	
	public SignupResponse(String message, String token, Boolean success) {
        this.message = message;
        this.token = token;
        this.success = success;
    }
	
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}

	public Boolean getSuccess() {
		return success;
	}

	public void setSuccess(Boolean success) {
		this.success = success;
	}
}
