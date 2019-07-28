package com.chris.smartpark.busi.doorctrl.provider.impl;

import com.chris.base.common.exception.CommonException;
import com.chris.smartpark.busi.common.VisitorUtils;
import com.chris.smartpark.busi.doorctrl.provider.DoorCtrlProvider;
import org.springframework.stereotype.Service;

@Service("dasDoorCtrlProvider")
public class DasDoorCtrlProvider implements DoorCtrlProvider {
    @Override
    public String convertPhyCardId(String physicalCardId) {
        if (physicalCardId.length() < 15) {
            throw new CommonException("物理卡ID位数小于15位");
        }
        String firstChar = physicalCardId.substring(8, 10);
        String secondChar = physicalCardId.substring(10, 12);
        String thirdChar = physicalCardId.substring(12, 14);
        String cardId = thirdChar + secondChar + firstChar;
        int resultValue = VisitorUtils.hex2Int(cardId);
        return resultValue + "";
    }
}
