import java.io.File;
import javax.sound.sampled.*;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws UnsupportedAudioFileException, IOException, LineUnavailableException {
        Scanner scanner=new Scanner(System.in);
        System.out.println("Введите 1 - секудндомер"+"\n"+"Введите 2-таймер"+"\n"+"Введите 3-будильник");
        int choice=scanner.nextInt();
        while (choice!=0){
            if(choice==1){
                second();
            }
            if(choice==2){
                timer();
            }
            if(choice==3){
                alarm();
            }
            System.out.println("Введите 1 - секудндомер"+"\n"+"Введите 2-таймер"+"\n"+"Введите 3-будильник");
            choice=scanner.nextInt();
        }
    }
    
    
    public static void second(){
        boolean pause=true;
        int sec=0;
        int min=0;
        int hour=0;
        while(pause=true){
            sec++;
            if(sec==60){
                sec=0;
                min++;
            }
            if(min==60){
                min=0;
                hour++;
            }
            String secund=Integer.toString(sec);
            String minut=Integer.toString(min);
            String chas=Integer.toString(hour);
            if(sec<=9){
                secund=0+secund;
            }
            if(min<=9){
                minut=0+minut;
            }
            if(hour<=9){
                chas=0+chas;
            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(chas+":"+minut+":"+secund);
            pause=new Scanner(System.in).nextBoolean();
        }
    }
    
    
    public static void timer(){
        System.out.println("Сколько засечь минут?");
        int interv_min=new Scanner(System.in).nextInt();
        System.out.println("Сколько засечь секунд?");
        int interv_sec=new Scanner(System.in).nextInt();
        int end_time=interv_min*60+interv_sec;
        for (int i =0; i <=end_time ; i++) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(i);
        }
    }



    public static void alarm() throws UnsupportedAudioFileException, IOException, LineUnavailableException {
        System.out.println("Введите время в формате hh:mm:ss");
        String time_awoke=new Scanner(System.in).nextLine();
        System.out.println("Сколько осилишь примеров?");
        int guess=new Scanner(System.in).nextInt();
        String curr_time=new SimpleDateFormat("HH:mm:ss").format(Calendar.getInstance().getTime());
        while(!(time_awoke.equals(curr_time))){
            curr_time=new SimpleDateFormat("HH:mm:ss").format(Calendar.getInstance().getTime());
        }

        while (true) {
            alarmbring();
            if (mathal(guess)) {
                break;
            }
        }
    }


    public static boolean mathal(int n) throws UnsupportedAudioFileException, IOException, LineUnavailableException {
        int counter=0;
        Scanner alarmscan=new Scanner(System.in);
        while(counter!=n) {
            int a = (int) (Math.random() * 1000);
            int b = (int) (Math.random() * 1000);
            System.out.print(a+"+"+b+"=");
            int check=alarmscan.nextInt();
            if(check==a+b){
                counter++;
            }
        }
        return true;
    }


    public static void alarmbring() throws IOException, UnsupportedAudioFileException, LineUnavailableException {
        Scanner un=new Scanner(System.in);
        File file=new File("shrek.wav");
        AudioInputStream audioStream= AudioSystem.getAudioInputStream(file);
        Clip clip=AudioSystem.getClip();
        clip.open(audioStream);
        clip.start();
        String per=un.next();
    }
}
