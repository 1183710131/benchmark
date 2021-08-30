package com.alibaba.edas;

import com.alibaba.edas.generator.*;

import java.util.List;

public interface PrizeService {

    /**
     * 新增奖品条目
     * @param prizeContent
     * @return
     */
    boolean insertPrizeContent(PrizeContent prizeContent);

    /**
     * 删除奖品条目
     * @param prizeId
     * @return
     */
    boolean deletePrizeContent(long prizeId);

    /**
     * 修改奖品条目
     * @param prizeContent
     * @return
     */
    boolean updatePrizeContent(PrizeContent prizeContent);

    /**
     * 查找奖品条目 by Id
     * @param prizeId
     * @return
     */
    PrizeContent selectPrizeContentById(long prizeId);

    /**
     * 插入一条奖品流水
     * @param prizeDetail
     * @return
     */
    boolean insertPrizeDetail(PrizeDetail prizeDetail);

    /**
     * 查找用户奖品流水
     * @param userId
     * @return
     */
    List<PrizeDetail> selectPrizeDetailById(long userId);

    /**
     * 随机抽奖
     * @return
     */
    PrizeContent luckDraw();

}
