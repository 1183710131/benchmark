package com.alibaba.edas;

import com.alibaba.boot.hsf.annotation.HSFProvider;
import com.alibaba.edas.generator.*;

import javax.annotation.Resource;
import java.util.List;

@HSFProvider(serviceInterface = PrizeService.class, serviceVersion = "1.0.0")
public class PrizeServiceImpl implements PrizeService{

    @Resource
    private PrizeContentDao prizeContentDao;

    @Resource
    private PrizeDetailDao prizeDetailDao;

    @Override
    public boolean insertPrizeContent(PrizeContent prizeContent) {
        prizeContentDao.insert(prizeContent);
        return true;
    }

    @Override
    public boolean deletePrizeContent(long prizeId) {
        prizeContentDao.deleteByPrimaryKey(prizeId);
        return false;
    }

    @Override
    public boolean updatePrizeContent(PrizeContent prizeContent) {
        prizeContentDao.updateByPrimaryKey(prizeContent);
        return true;
    }

    @Override
    public PrizeContent selectPrizeContentById(long prizeId) {
        return prizeContentDao.selectByPrimaryKey(prizeId);
    }

    @Override
    public boolean insertPrizeDetail(PrizeDetail prizeDetail) {
        prizeDetailDao.insert(prizeDetail);
        return true;
    }

    @Override
    public List<PrizeDetail> selectPrizeDetailById(long userId) {
        PrizeDetailExample prizeDetailExample = new PrizeDetailExample();
        PrizeDetailExample.Criteria criteria = prizeDetailExample.createCriteria();
        criteria.andUserIdEqualTo(userId);
        return prizeDetailDao.selectByExample(prizeDetailExample);
    }

    @Override
    public PrizeContent luckDraw() {
        PrizeContentExample prizeContentExample = new PrizeContentExample();
        PrizeContentExample.Criteria criteria = prizeContentExample.createCriteria();
        List<PrizeContent> prizeContents = prizeContentDao.selectByExample(prizeContentExample);
        int prizeNum = prizeContents.size();
        int index = (int) Math.random()*prizeNum;
        return prizeContents.get(index);
    }
}
