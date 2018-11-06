package entities;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import enums.GroupMeEnum;
import org.json.JSONArray;
import org.json.JSONObject;
import utils.Log;

import java.util.ArrayList;

public class GroupMe {
    private static final Log log = new Log(GroupMe.class);

    private static final String POST_URL = "https://api.groupme.com/v3/bots/post";
    private static final String GROUP_URL = "https://api.groupme.com/v3/groups/";

    private static void createMessage(String message) {
        try {
            Thread.sleep(1000);
            if (message.endsWith("\\")) {
                message = message.substring(0, message.length() - 1);
            } else if (message.startsWith("n")) {
                message = message.substring(1);
            }
            System.out.println(message);
            final HttpResponse<JsonNode> response = Unirest.post(POST_URL)
                    .header("Content-Type", "application/json")
                    .body("{\"text\" : \"" + message + "\", \"bot_id\" : \"" + GroupMeEnum.BOT_ID.getValue() + "\"}")
                    .asJson();
            log.debug("Status Text: " + response.getStatusText() + " | Status: " + response.getStatus(), false);
        } catch (UnirestException | InterruptedException e) {
            log.error(e.getLocalizedMessage(), true);
        }
    }

    public static ArrayList<GroupMeUser> getAllUsersInGroup() {
        final ArrayList<GroupMeUser> users = new ArrayList<>();

        try {
            final HttpResponse<JsonNode> response = Unirest.get(GROUP_URL + GroupMeEnum.GROUP_ID.getValue())
                    .queryString("token", GroupMeEnum.ACCESS_TOKEN.getValue())
                    .asJson();
            log.debug("Status Text: " + response.getStatusText() + " | Status: " + response.getStatus(), false);
            final JSONArray usersList = response.getBody().getObject().getJSONObject("response").getJSONArray("members");

            for (Object o : usersList) {
                if (o instanceof JSONObject) {
                    users.add(new GroupMeUser(((JSONObject) o).getInt("id"), ((JSONObject) o).getString("nickname")));
                }
            }

            return users;
        } catch (UnirestException e) {
            log.error(e.getLocalizedMessage(), true);
            return null;
        }
    }

    public static void sendMessage(String data) {
        if (data.length() < 1000) {
            createMessage(data);
        } else {
            createMessage(data.substring(0, 1001));
            sendMessage(data.substring(1001));
        }
    }

}