package by.htp.ex.util.validation;

public interface UserDataValidation {
      
       boolean checkUserData(String email, String password) throws ValidationException;
}
