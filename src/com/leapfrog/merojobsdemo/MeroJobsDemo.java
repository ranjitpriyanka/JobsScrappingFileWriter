package com.leapfrog.merojobsdemo;

//import com.leapfrog.merojobmain.grabber.Grabber;
//import com.leapfrog.merojobmain.scrapper.JobsNepalScrapper;
//import com.leapfrog.merojobmain.scrapper.MeroJobScrapper;
//import com.leapfrog.merojobmain.scrapper.Scrapper;
import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.application.Platform;

public class MeroJobsDemo {

    public static void main(String[] args) throws IOException {
//        String mlink = "http://www.merojob.com/search-new/index.php?search=&category=0";
//        String jlink = "http://www.jobsnepal.com";
        StringBuilder builder = new StringBuilder();

        try (FileWriter writer = new FileWriter("abcd.txt")) {
            String content = readURL("http://www.merojob.com/search-new/index.php?search=&category=0");
            String regex = "<a href='(.*?)' class=\"thumbnail spacebot\" target=\"_blank\">(.*?)<h4 class='title changefont'>(.*?)<img src=\"../images/SI/Top.png\" alt=\"TOP JOB\" title=\"TOP JOB\" /></h4>(.*?)<p class=\"text-success changefont\">(.*?)</p>(.*?)<p class=\"text-info changefont\">(.*?)</p>(.*?)<p class=\"text-warning changefont\">(.*?)<span class=\"text-error\">(.*?)</span></p>";
            Pattern pattern = Pattern.compile(regex);
            Matcher matcher = pattern.matcher(content);

            while (matcher.find()) {
                System.out.println(matcher.group(1));
                //builder.append(matcher.group(1));
                //builder.append("\r\n");
                //builder.append(m atcher.group(5));            
                writer.write("=======================================================================" + "\r\n");
                writer.write("job title: " + matcher.group(3) + "\r\n");
                writer.write("job url: " + matcher.group(1) + "\r\n");
                writer.write("Company: " + matcher.group(5).trim() + "\r\n");
                writer.write("Experience: " + matcher.group(7).trim() + "\r\n");
                writer.write(matcher.group(9).trim() + "\r\n");
                writer.write(matcher.group(10).trim() + "\r\n");
                writer.write("=======================================================================" + "\r\n");

                writer.flush();
            }
            writer.close();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
//        Grabber grabber = new Grabber(mlink);
    }

    public static String readURL(String link) throws MalformedURLException, IOException {
        URL url = new URL(link);
        BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream()));
        String line = "";
        StringBuilder builder = new StringBuilder();
        while ((line = reader.readLine()) != null) {
            builder.append(line);
        }
        reader.close();
        return builder.toString();
    }
}
