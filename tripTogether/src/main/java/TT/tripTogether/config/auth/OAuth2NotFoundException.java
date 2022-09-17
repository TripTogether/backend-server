package TT.tripTogether.config.auth;

public class OAuth2NotFoundException extends RuntimeException{
    public OAuth2NotFoundException(String message){
        super(message);
    }
}
