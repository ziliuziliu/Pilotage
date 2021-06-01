package com.eis.broker.serviceimpl;

import com.eis.broker.dao.QuantityLogDao;
import com.eis.broker.entity.QuantityLog;
import com.eis.broker.entity.Weight;
import com.eis.broker.service.AnalysisService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class AnalysisServiceImpl implements AnalysisService {

    private Set<String> loggingProducts = new HashSet<>();
    private static final Logger logger = LoggerFactory.getLogger(AnalysisServiceImpl.class);

    private final StringRedisTemplate stringRedisTemplate;

    private final QuantityLogDao quantityLogDao;

    @Autowired
    public AnalysisServiceImpl(QuantityLogDao quantityLogDao, StringRedisTemplate stringRedisTemplate) {
        this.quantityLogDao = quantityLogDao;
        this.stringRedisTemplate = stringRedisTemplate;
    }

    @Override
    public void addLoggingProduct(String product) {
        loggingProducts.add(product);
    }

    @Override
    public void removeLoggingProduct(String product) {
        loggingProducts.remove(product);
    }

    @Override
    public Weight getWeight(String product, Integer hours) {
        List<QuantityLog> quantityLogs = quantityLogDao.getPriceLogs(product, hours);
        System.out.println(quantityLogs.size());
        Double[] weights = new Double[60];
        for (int i=59;i>=0;i--) {
            Double sum = 0.0;
            for (int j=0;j<hours;j++)
                sum += quantityLogs.get(i + j*60).getQuantity();
            weights[59-i] = sum / hours;
        }
        return new Weight(weights);
    }

    @Scheduled(fixedRate = 1000*30)
    public void logQuantity() {
        for (String product : loggingProducts) {
            String quantity = stringRedisTemplate.opsForValue().get("quantity-" + product);
            if (quantity == null) continue;
            logger.info("flushing quantity " + product + quantity);
            quantityLogDao.save(new QuantityLog(product, Integer.valueOf(quantity)));
            stringRedisTemplate.opsForValue().set("quantity-" + product, "0");
        }
    }
}
