package examples.bulk;

import com.socketLabs.injectionApi.SendResponse;
import com.socketLabs.injectionApi.SocketLabsClient;
import com.socketLabs.injectionApi.message.BulkMessage;
import examples.Example;

public class BulkSendWithAsciiCharsetMergeData implements Example {
    @Override
    public SendResponse RunExample() throws Exception {

        SocketLabsClient client = new SocketLabsClient(19742, "Ng5x6HEc4f7CFk92Kpn3");

        BulkMessage message = new BulkMessage();

        return null;
    }
}
