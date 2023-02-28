package Service;

import Model.Color;
import Model.Size;
import Model.SocksBatch;

public interface SocksService {
    void accept (SocksBatch socksBatch);

    int issuance (SocksBatch socksBatch);

    int reject(SocksBatch socksBatch);

    int getCount(Color color, Size size, int cottonMin, int cottonMax);
}
