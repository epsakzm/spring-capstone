package project.capstone.fick.security;

public class UserNotActivatedException extends RuntimeException{

	public UserNotActivatedException(String message) {
		super(message);
	}
}
