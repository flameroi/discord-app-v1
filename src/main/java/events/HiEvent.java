package events;

import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class HiEvent extends ListenerAdapter {

    public void onGuildMessageReceived(GuildMessageReceivedEvent event){
        if(event.getMember().getUser().isBot()){return;}
        String messageSent[] = event.getMessage().getContentRaw().split(" ");
        String helloMessage = getHelloMessage();
        String userName = event.getMember().getUser().getName();
        if(messageSent[0].equalsIgnoreCase("hi")){
            event.getChannel().sendMessage( helloMessage+ " " + userName).queue();
        }
    }

    private static String getHelloMessage(){

        String file = "src/main/resources/greetingOption.txt";
        ArrayList<String> greetingOptions = new ArrayList<String>();
        String result;
        try{
            FileReader fileReader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            String line;
            while ((line = bufferedReader.readLine()) != null){
                greetingOptions.add(line);
            }

            int number = (int) (Math.random() * (greetingOptions.size()-1));
            return greetingOptions.get(number);
        }
        catch (IOException ie){
            System.out.println(ie);
            return "Привет";
        }
    }
}
