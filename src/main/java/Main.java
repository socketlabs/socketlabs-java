import client.SendResponse;
import client.SocketLabsClient;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import models.Attachment;
import models.BasicMessage;
import models.CustomHeader;
import models.EmailAddress;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        SocketLabsClient.QuickSend(
                17067,
                "r4W7SaJi38FyLp6k5NKs",
                "ross.brazuk@socketlabs.com",
                "ross.brazuk@gekodesign.net",
                "Friday",
                "<p style='color:red'>It's Friday</p>",
                "Quicksend"
        );
    }
}