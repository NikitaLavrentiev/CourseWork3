package Service.Impl;

import Model.Color;
import Model.Size;
import Model.Socks;
import Model.SocksBatch;
import Service.SocksService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import repository.SocksRepository;

import java.util.Map;

@Service
@AllArgsConstructor
public class SocksServiceImpl implements SocksService {
    private final SocksRepository socksRepository;
@Override
    public void accept(SocksBatch socksBatch) {
    socksRepository.save(socksBatch);
    }
@Override
    public int issuance(SocksBatch socksBatch) {
        return socksRepository.remove(socksBatch);
    }
@Override
public int reject(SocksBatch socksBatch) {
    return socksRepository.remove(socksBatch);
    }
    @Override
    public int getCount(Color color, Size size, int cottonMin, int cottonMax) {
        Map<Socks, Integer> socksMap = socksRepository.getAll();
        for (Map.Entry<Socks, Integer> socksItem : socksMap.entrySet()) {
            Socks socks = socksItem.getKey();

            if (socks.getColor().equals(color) && socks.getSize().equals(size) && socks.getCottonPart() >= cottonMin && socks.getCottonPart() <= cottonMax) {
                return socksItem.getValue();
            }
        }
        return 0;
    }




}
