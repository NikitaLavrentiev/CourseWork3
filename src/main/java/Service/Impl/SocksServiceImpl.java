package Service.Impl;

import Exeptions.ValidationException;
import Model.Color;
import Model.Size;
import Model.Socks;
import Model.SocksBatch;
import Service.SocksService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import Service.ValidationService;
import repository.SocksRepository;

import java.util.Map;

@Service
@AllArgsConstructor
public class SocksServiceImpl implements SocksService {
    private final SocksRepository socksRepository;
    private final ValidationService validationService;

    @Override
    public void accept(SocksBatch socksBatch) {
        checkStocksBatch(socksBatch);
        socksRepository.save(socksBatch);
    }

    @Override
    public int issuance(SocksBatch socksBatch) {
        checkStocksBatch(socksBatch);
        return socksRepository.remove(socksBatch);
    }

    @Override
    public int reject(SocksBatch socksBatch) {
        checkStocksBatch(socksBatch);
        return socksRepository.remove(socksBatch);
    }

    @Override
    public int getCount(Color color, Size size, int cottonMin, int cottonMax) {
        if (!validationService.validate(color, size, cottonMin, cottonMax)) {
            throw new ValidationException();
        }

        Map<Socks, Integer> socksMap = socksRepository.getAll();
        for (Map.Entry<Socks, Integer> socksItem : socksMap.entrySet()) {
            Socks socks = socksItem.getKey();

            if (socks.getColor().equals(color) &&
                    socks.getSize().equals(size) &&
                    socks.getCottonPart() >= cottonMin &&
                    socks.getCottonPart() <= cottonMax) {
                return socksItem.getValue();
            }
        }
        return 0;
    }

    private void checkStocksBatch(SocksBatch socksBatch) {
        if (!validationService.validate((socksBatch))) {
            throw new ValidationException();
        }
    }

}
