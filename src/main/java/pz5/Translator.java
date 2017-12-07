package pz5;

import jdk.nashorn.internal.parser.JSONParser;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.mortbay.util.ajax.JSON;
import sun.net.www.http.HttpClient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.ParseException;

import static pz5.Lang.EN_RU;

/**
 * Переводчик через API Яндекс
 *
 * Created by panov on 08.12.15.
 *
 */
public class Translator {

    private static final String API_KEY = "trnsl.1.1.20151208T113648Z.1933bf453c52497a.a4fab8d1095b0382866b046c15787a76ce737f36";
    private static final String PATH = "https://translate.yandex.net/api/v1.5/tr.json/translate";

    public Lang lang;
    private String source = "";
    private String target = "";

    public static void main( String[] args ) throws IOException, ParseException {

        Translator translator = new Translator();
        //String t =translator.translate("прекрасный","ru-en");
        //System.out.println(t);

        translator.lang = Lang.RU_EN;

    }

    public Translator( String s ){

    }
    public Translator( ){

    }

    public String translate( String word ) {
        return null;
    }


    private String translate(String text, String direction) throws IOException, ParseException {

        switch ( lang ){
            case EN_RU:
                source = "en";
                target = "ru";
                break;
            case RU_EN:
                source = "ru";
                target = "en";
                break;
        }

        String requestUrl = PATH + "?key=" + API_KEY + "&lang=" + direction + "&text=" + text;

        URL url = new URL( requestUrl );
        HttpURLConnection connection = ( HttpURLConnection ) url.openConnection();
        connection.connect();

        int code = connection.getResponseCode();

        if ( code == 200 ){
            String line = null;
            BufferedReader buffReader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            StringBuilder strBuilder = new StringBuilder();
            while ((line = buffReader.readLine()) != null) {
                strBuilder.append(line + '\n');
            }

            return convert( strBuilder.toString(), target );

        }
        return code + "";
    }

    public static String convert(String str, String target) throws ParseException {

        if(target.equals( "ru" ))
            str = str.replaceAll( "([\\{\\}\":,\\w\\d\\-.\\[ \\S]*)text\":\\[\"(?<tail>[а-яё]*)\"\\]([.\\}]*)", "${tail}" );

        if(target.equals( "en" ))
            str = str.replaceAll( "([\\{\\}\":,\\w\\d\\-.\\[ \\S]*)text\":\\[\"(?<tail>[a-z]*)\"\\]([.\\}]*)", "${tail}" );

        /*
        JSONParser parser = new JSONParser( str, null, false );
        JSONObject object = (JSONObject ) parser.parse( );
        JSONArray array = (JSONArray) object.get( "text" );

        for ( Object o : array ) {
            System.out.println(o);
        }
        */


        /*
        JSONParser parser = new JSONParser();
        JSONObject object = (JSONObject) parser.parse(str);
        StringBuilder sb = new StringBuilder();
        JSONArray array = (JSONArray) object.get("text");
        for (Object s : array) {
            sb.append(s.toString() + "\n");
        }
        return sb.toString();
        */


        return str;
    }


}
