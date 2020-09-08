package dgsw.b1cami.cocode.Service;

import dgsw.b1cami.cocode.Domain.User;
import dgsw.b1cami.cocode.Exception.UserException;
import dgsw.b1cami.cocode.Repository.TokenRepository;
import dgsw.b1cami.cocode.Repository.UserRepository;
import dgsw.b1cami.cocode.json.LoginResponse;
import dgsw.b1cami.cocode.json.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.net.Inet4Address;
import java.net.UnknownHostException;
import java.security.MessageDigest;
import java.util.Properties;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    TokenRepository tokenRepository;

    @Override
    public Response sendEmail(User user) {
        String sendEmail = "dgswbook@gmail.com"; // 보내는 email 주소
        String password = "12qwerdbookqwer34!"; // 보내는 email password
        String serverSMTP = "smtp.gmail.com";
        int serverPORT = 465;

        //SMTP 정보
        Properties prop = new Properties();
        prop.put("mail.smtp.host", serverSMTP);
        prop.put("mail.smtp.port", serverPORT);
        prop.put("mail.smtp.auth", "true");
        prop.put("mail.smtp.ssl.enable", "true");
        prop.put("mail.smtp.ssl.trust", serverSMTP);

        Session session = Session.getDefaultInstance(prop, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(sendEmail, password);
            }
        });

        try {
            String email = user.getEmail();
            String userPassword = user.getPassword();
            String name = user.getName();

            if (email == null)
                throw new UserException(400, "Requires Email");
            if (userPassword == null)
                throw new UserException(400, "Requires Password");
            if(name == null)
                throw new UserException(400, "Requires Name");

            if(email.length() > 320)
                throw new UserException(401, "Email Must Be Shorter Than 320");
            if(userPassword.length() < 8 || userPassword.length() > 25)
                throw new UserException(401, "Password Must Be Longer Than 8 And Shorter Than 25");
            if(name.length() > 100)
                throw new UserException(401, "Name Must Be Shorter Than 100");

            Pattern pattern = Pattern.compile("(^(?=.*[0-9])(?=.*[a-zA-Z]).*$)");
            Matcher matcher = pattern.matcher(userPassword);
            if(!matcher.find())
                throw new UserException(401, "Password Must Contain Number And English Character");

            if (!findUser(email).getEmail().equals("Undefined"))
                throw new UserException(400, "User Already Exists");

            MimeMessage message = new MimeMessage(session);

            message.setFrom(new InternetAddress(sendEmail));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(email));
            message.setSubject("[CoCode] 인증번호"); //메일 제목을 입력

            String code;

            while(true) {
                int random = (int) (Math.random() * 8999 + 1000);
                code = Integer.toString(random);

                User objUser = userRepository.findByUserCertifyCode(code).orElse(null);

                if(objUser == null)
                    break;
            }

            // Text
            message.setText("인증번호 [" + code + "] 를 입력해주세요.");

            // Send
            Transport.send(message);

            user.setPassword(convertSHA256(user.getPassword()));
            userRepository.save(user);

            return new Response(200, "Success sendEmail");
        } catch (UserException e) {
            return new Response(e.getStatus(), e.getMessage());
        } catch (MessagingException e) {
            e.printStackTrace();
            return new Response(500, e.getMessage());
        }
    }

    @Override
    public LoginResponse login(User user) {
        return null;
    }

    @Override
    public Response signUp(User user) {
        return null;
    }

    @Override
    public User findUser(String email) {
        return userRepository.findByUserEmail(email).orElseGet(() -> new User("Undefined"));
    }

    public String makeToken(){
        StringBuilder token = new StringBuilder();
        Random random = new Random();

        for (int i = 0; i < 16; i++) {
            token.append((char) ((int) (random.nextInt(26)) + 97));
        }

        return token.toString();
    }

    public String findIp() {
        String ipaddr = null;

        try {
            ipaddr = Inet4Address.getLocalHost().getHostAddress();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }

        return ipaddr;
    }

    private String convertSHA256(String password) {
        try{
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(password.getBytes("UTF-8"));
            StringBuilder hexString = new StringBuilder();

            for (byte b : hash) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) hexString.append('0');
                hexString.append(hex);
            }
            return hexString.toString();
        } catch(Exception ex){
            throw new RuntimeException(ex);
        }
    }

}