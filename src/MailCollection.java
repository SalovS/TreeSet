import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MailCollection {
    public void Start(){
        readEmail();
    }
    private void readEmail(){
        Set<String> emailAddresses = new TreeSet<>();
        boolean continueWorking = true;
        Scanner scanner = new Scanner(System.in);
        printCommand();
        while(continueWorking){
            String userText = scanner.nextLine();
            switch (readCommand(userText)){
                case ("EXIT"): continueWorking = false; break;
                case ("ADD"): emailAddresses = addEmail(emailAddresses, userText); break;
                case ("LIST"): printEmaiAddresses(emailAddresses); break;
                default:
                    printCommand();
            }
        }
        scanner.close();
    }

    private Set<String> addEmail(Set<String> emailAddresses, String userText) {
        String email = deleteCommand(userText);
        Set <String> eMails = emailAddresses;
        if(isEmai(email)){
            if(eMails.contains(email)) {
                System.out.println("Этот адрес электроной почты уже внесён в базу");
            }
            else {
                eMails.add(email);
            }
        }
        return eMails;
    }

    private boolean isEmai(String email) {
        Pattern pattern = Pattern.compile("\\w+([\\.-]?\\w+)*@\\w+([\\.-]?\\w+)*\\.\\w{2,4}");
        Matcher matcher = pattern.matcher(email);
        if(matcher.find()) {
            return true;
        }
        System.out.println("Введён неверный электронный адресс");
        return false;
    }

    private void printEmaiAddresses(Set<String> emailAddresses) {
        for (String email: emailAddresses){
            System.out.println(email);
        }
    }

    private String readCommand(String userText) {
        Pattern pattern = Pattern.compile("^[A-Z]+");
        Matcher matcher = pattern.matcher(userText);
        if(matcher.find())
            return matcher.group();
        return userText;
    }

    private void printCommand(){
        System.out.println("LIST — выводит список электронных адресов");
        System.out.println("ADD — добавляет адрес электронной почты");
        System.out.println("EXIT — выход из программы");
    }

    private String deleteCommand(String text){
        String answer = text.replace(readCommand(text), "");
        return answer.trim();
    }
}
