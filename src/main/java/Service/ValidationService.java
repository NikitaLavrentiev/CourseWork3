package Service;

import Model.Color;
import Model.Size;
import Model.SocksBatch;

public interface ValidationService {
    public boolean validate(SocksBatch socksBatch);

    boolean validate(Color color, Size size, int cottonMin, int cottonMax);
}
