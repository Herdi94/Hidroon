package id.com.hidroon.controller;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONObject;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;


@RestController
@RequestMapping
public class HidroonController {

    @GetMapping
    public String listData() {
        String data = "https://platform.antares.id:8443/~/antares-cse/antares-id/tugas-akhir-hidroponik/alat1/la";
        RestTemplate rest = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json;ty=4");
        headers.add("Accept", "application/json");
        headers.add("X-M2M-Origin", "9d21a57678517877:48a1bcc0fcef4da7");
        HttpEntity<String> entity = new HttpEntity<String>(headers);
        ResponseEntity<String> responses = rest.exchange(data, HttpMethod.GET, entity, String.class);

        JsonNode jsonNode = null;
        try {
            JSONObject jsonData = new JSONObject(responses.getBody());
            ObjectMapper mapper = new ObjectMapper();
            jsonNode = mapper.readTree(jsonData.toString());
        } catch (Exception e) {
            e.getMessage();
        }

        String con = jsonNode.findValue("con").toString().replaceAll("[^a-zA-Z\\d\\s:,()]","");
        String[] splitCont = con.split(",");
        //delete character n
        String subCon = splitCont[2].substring(1);
        //String str = "<html> <b>HIDROON</b> </html>: "+splitCont[0]+","+splitCont[1]+","+subCon;
        String str = "<!DOCTYPE html>\n" +
                "<html lang=\"en\">\n" +
                "<head>\n" +
                "    <meta charset=\"UTF-8\">\n" +
                "    <title>Hidroon</title>\n" +
                "</head>\n" +
                "<style>" +
                "  div.container {\n" +
                "    width:98%; \n" +
                "    margin:1%;\n" +
                "  }\n" +
                "  table#table1 {\n" +
                "    text-align:center; \n" +
                "    margin-left:auto; \n" +
                "    margin-right:auto; \n" +
                "    width:300px;\n" +
                "    border-collapse: collapse;" +
                "    background-color: #FFFFFF;" +
                "  }\n" +
                "  td,table {text-align:left; border: 1px solid black; }" +
                "tr:hover {background-color:#007bff; color :#FFFFFF;}" +
                "body#body1 {" +
                "background-color: #f2f2f2" +
                "}" +
                "th {\n" +
                "  background-color: #007bff;\n" +
                "  color: white;\n" +
                "}" +
                "</style>" +
                "<body id=\"body1\">\n" +
                "<div class=\"container\">" +
                "<table id=\"table1\">" +
                "<tr><th colspan=3 style=\"text-align: center;\"><h3>HIDROON</h3></th></tr>"+
                "<tr>" +
                "<td style=\"text-align: center;\"><b>No</b></td>" +
                "<td style=\"text-align: center;\"><b>Keterangan</b></td>" +
                "</tr>" +
                "<tr>" +
                "<td style=\"text-align: center;\">1</td>" +
                "<td>"+splitCont[0]+"</td>" +
                "</tr>" +
                "<tr>" +
                "<td style=\"text-align: center;\">2</td>" +
                "<td>"+splitCont[1]+"</td>" +
                "</tr>" +
                "<tr>" +
                "<td style=\"text-align: center;\">3</td>" +
                "<td>"+subCon+"</td>" +
                "</tr>" +
                "<tr>" +
                "<td colspan=2 style=\"text-align: center;\"><button onclick=\"goBack()\" type=\"button\" style=\"background-color:#007bff  \"><h4 style=\"color:white\">Sign Out</h4></button></td>" +
                "</tr>" +
                "</table>" +
                "</div>" +
                "</body>\n" +
                "</html>" +
                "<script>\n" +
                "function goBack() {\n" +
                "  window.history.back();\n" +
                "}\n" +
                "</script>" ;

        return str;
    }

}
