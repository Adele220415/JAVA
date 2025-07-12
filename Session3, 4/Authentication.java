public abstract class Authentication {
    public abstract String Login(String username, String password);
    public abstract void Logout();
    public abstract String register(String username, String password, String email);

}
// implementation with email and password authentication
public class EmailPasswordAuthentication extends Authentication(
    @Override
    public String Login(String username, String password){
        String token = "urehufhjdsh8438q567894398ruijf";
        System.out.println("Login successful");
        return token;
    };

    @Override
    public void Logout(){
        System.out.println("Logout successful");
    };

    @Override
    public String register(String username, String password, String email){
        String token = "ythjgfnmi49895839u5terjkdfnmvnf";
        System.out.println("Registered soccessfolly");
    };
)
