import com.fnic.sysframe.utils.HttpClientUtil;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Value;

public class TestSendMessage {

    @Value("${sms_url}")
    private String url = "https://open.ucpaas.com/ol/sms";

    @Value("${sms_sid}")
    private String sid = "f854a4317ad2333e84398bc79eca3809";

    @Value("${sms_token}")
    private String token = "14925540edd22d0c23780d912afb0504";

    @Value("${sms_appid}")
    private String appid = "68e933587cef4138accd7adfbab83efb";

    public void sendMessage() throws Exception {

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("sid", sid);
        jsonObject.put("token", token);
        jsonObject.put("appid", appid);
        jsonObject.put("templateid", "299747");

        jsonObject.put("param", "test112233");
        jsonObject.put("mobile", "13813970080");
        jsonObject.put("uid", "");

        String body = jsonObject.toString();
        System.out.println("body = " + body);

        String result = HttpClientUtil.postJson(url + "/sendsms",body,null);

        JSONObject object = new JSONObject(result);

        System.out.println("result: " + result);
        System.out.println("code: " + object.get("code"));
    }

}
